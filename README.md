# TodoList API

## 📝 Descrição

API de Gerenciamento de Tarefas desenvolvida durante o curso "Java com Spring Boot" da Rocketseat. Esta aplicação permite aos usuários criar uma conta, gerenciar tarefas pessoais e organizar suas atividades de forma eficiente.

## 🚀 Funcionalidades

### Usuários

-  Registro de novos usuários
-  Autenticação usando Basic Auth
-  Validação de usuários duplicados
-  Criptografia de senha usando BCrypt

### Tarefas

-  Criar novas tarefas
-  Listar tarefas do usuário
-  Atualizar tarefas existentes
-  Validações:
   -  Título com máximo de 50 caracteres
   -  Descrição com máximo de 255 caracteres
   -  Data de início e término
   -  Prioridade da tarefa

## 🛠️ Tecnologias Utilizadas

-  Java 17
-  Spring Boot
-  Spring Data JPA
-  H2 Database
-  Lombok
-  BCrypt
-  Maven

## 🛠️ Ferramentas de Desenvolvimento

### Apidog

Para testar e documentar nossa API, utilizamos o [Apidog](https://apidog.com/) - uma plataforma completa para desenvolvimento de APIs que combina:

-  Design e documentação de API
-  Testes e debugging
-  Mock de APIs
-  Interface amigável similar ao Postman
-  Suporte a Swagger/OpenAPI
-  Ambiente colaborativo para equipes

#### Benefícios do Apidog no Projeto

-  Documentação automática dos endpoints
-  Testes de integração facilitados
-  Ambiente unificado para desenvolvimento e testes
-  Validação de requests e responses
-  Gerenciamento de diferentes ambientes (desenvolvimento, produção)

#### Como Testar a API com Apidog

1. Faça o download do Apidog em https://apidog.com/
2. Importe a coleção de endpoints
3. Configure as variáveis de ambiente
4. Use a autenticação Basic Auth para endpoints protegidos
5. Execute as requisições para testar a API

## 📚 Estrutura do Projeto

```
src/main/java/com/isaacggr/todolist/
├── user/               # Gerenciamento de usuários
├── task/               # Gerenciamento de tarefas
├── filter/             # Filtros de autenticação
├── errors/             # Tratamento de exceções
└── utils/              # Classes utilitárias
```

## 🔒 Segurança

-  Autenticação Basic Auth para todas as rotas de tarefas
-  Senhas criptografadas com BCrypt
-  Validação de propriedade das tarefas

## 🌐 Endpoints

### Usuários

-  `POST /users/create` - Criar novo usuário

### Tarefas

-  `POST /tasks/` - Criar nova tarefa
-  `GET /tasks/` - Listar tarefas do usuário
-  `PUT /tasks/{id}` - Atualizar tarefa existente

## 💻 Como Executar

1. Clone o repositório
2. Tenha o Java 17 instalado
3. Execute o comando: `mvn spring-boot:run`
4. A aplicação estará disponível em `http://localhost:8080`

## 🧪 Exemplos de Uso

### Criar Usuário

```http
POST /users/
{
    "name": "Seu Nome",
    "username": "seu_usuario",
    "password": "sua_senha"
}
```

### Criar Tarefa

```http
POST /tasks/
{
    "title": "Título da Tarefa",
    "description": "Descrição da tarefa",
    "priority": "ALTA",
    "startAt": "2025-04-16T12:00:00",
    "endAt": "2025-04-17T12:00:00"
}
```

## 📄 Licença

Este projeto está sob a licença MIT.

---

⌨️ Desenvolvido durante o curso da [Rocketseat](https://rocketseat.com.br) 🚀
