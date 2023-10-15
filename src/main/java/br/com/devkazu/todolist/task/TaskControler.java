package br.com.devkazu.todolist.task;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devkazu.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/tasks")
public class TaskControler {
  @Autowired
  private ITaskRepository taskRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){
    var idUser = request.getAttribute("idUser");
    taskModel.setIdUser((UUID) idUser);

    var currentDate = LocalDateTime.now();
    if(currentDate.isAfter(taskModel.getStartAt())){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio deve ser maior que a data atual");
    }
    if(currentDate.isAfter(taskModel.getEndAt())){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de fim deve ser maior que a data atual");
    }
    if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio deve ser menor que a data de fim");
    }

    var task = this.taskRepository.save(taskModel);
    return ResponseEntity.status(HttpStatus.OK).body(task);
  }


  @GetMapping("/")
  public List<TaskModel> list(HttpServletRequest request){
    var idUser = request.getAttribute("idUser");
    var tasks = this.taskRepository.findByIdUser((UUID) idUser);
    return tasks;
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {
    var idUser = request.getAttribute("idUser");

    // Validação se o userId foi informado
    if(!Objects.equals(taskModel.getIdUser(), idUser)){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Você não tem permissão para alterar o userId desta tarefa");
    }

    var task = this.taskRepository.findById(id).orElse(null);
    
    // Validação se a tarefa existe
    if(task == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
    } 

    // Validação se a tarefa pertence ao usuário logado
    if(!task.getIdUser().equals(idUser)){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Você não tem permissão para alterar esta tarefa");
    }

    Utils.copyNonNullProperties(taskModel, task);

    return ResponseEntity.status(HttpStatus.OK).body(this.taskRepository.save(task));

  }
}

