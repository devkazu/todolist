# TodoList API - Rocketseat JAVA

## Descrição do Projeto

Este projeto foi desenvolvido como parte do evento da Rocketseat, onde foi abordada a criação de uma API em Spring Boot para gerenciar uma lista de tarefas (TodoList).

A API oferece endpoints para a criação, leitura, atualização e exclusão de tarefas, permitindo aos usuários organizar suas atividades de forma eficiente.

O projeto também inclui uma versão hospedada da API, acessível através do seguinte link: [TodoList API on Render](https://todolist-rocketseat-e00c.onrender.com).

## Tecnologias Utilizadas

- Java
- Spring Boot
- Banco de Dados H2
- Maven (para gerenciamento de dependências)
- Render (para hospedagem da API)

## Endpoints

### 1. Criar Tarefa

- **Endpoint**: `POST /tasks`
- **Corpo da Requisição (JSON)**:

```json
{

	"description": "tarefa teste",
	"title": "teste",
	"priority": "ALTA",
	"startAt": "2023-10-16T06:30:00",
	"endAt": "2023-10-16T06:30:00",
}
```

### 2. Obter Lista de Tarefas

- **Endpoint**: `GET /tasks`

### 3. Atualizar Tarefa por ID

- **Endpoint**: `PUT /tasks/{taskId}`
- **Corpo da Requisição (JSON)**:

```json
{
  "title": "Nova Tarefa",
  "description": "Nova Descrição"
}
```

### 5. Excluir Tarefa por ID

- **Endpoint**: `DELETE /api/tasks/{taskId}`

## Como Executar Localmente

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/todolist-api.git
```

2. Navegue até o diretório do projeto:

```bash
cd todolist-api
```

3. Execute o aplicativo Spring Boot:

```bash
./mvn spring-boot:run
```

4. Acesse a API localmente em [http://localhost:8080](http://localhost:8080).

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir problemas (issues) e enviar pull requests para melhorar o projeto.

## Autor

Este projeto foi desenvolvido por Leo Kazuyuki Nagatani.

## Licença

Este projeto está licenciado sob a Licença MIT - consulte o arquivo [LICENSE](LICENSE) para obter detalhes.

---

**Aproveite a organização com sua TodoList API!** 🚀
