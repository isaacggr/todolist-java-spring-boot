# TodoList API (Backend)

Este é o backend da aplicação TodoList, um gerenciador de tarefas que permite cadastro de usuários e gerenciamento completo de tarefas.

## Estrutura do Projeto

Este repositório contém apenas o backend da aplicação. O frontend está disponível em um [repositório separado](https://github.com/isaacggr/todolist-frontend).

-  **Backend (este repositório)**: API REST com Spring Boot e MongoDB
-  **Frontend**: [Link para o repositório do frontend](https://github.com/isaacggr/todolist-frontend)

## Tecnologias Utilizadas

-  Java 17
-  Spring Boot 3.2.12
-  MongoDB
-  Spring Data MongoDB
-  BCrypt para criptografia de senhas
-  Lombok

## Funcionalidades

-  Autenticação de usuários (login/cadastro)
-  CRUD completo de tarefas
-  Filtragem de tarefas por usuário
-  Validação de dados
-  API RESTful com endpoints bem definidos

## Endpoints Principais

### Usuários

-  `POST /users/create`: Cria um novo usuário
-  `POST /users/login`: Autentica um usuário

### Tarefas

-  `POST /tasks/create`: Cria uma nova tarefa
-  `GET /tasks/user/{userId}`: Lista tarefas de um usuário
-  `PUT /tasks/{id}`: Atualiza uma tarefa existente
-  `DELETE /tasks/{id}`: Remove uma tarefa

## Configuração e Execução

### Requisitos

-  Java 17+
-  Maven
-  MongoDB local ou MongoDB Atlas

### Execução Local

1. Clone este repositório
2. Configure o MongoDB em `src/main/resources/application.properties`
3. Execute: `./mvnw spring-boot:run`

### Scripts de Implantação

Este projeto inclui scripts para facilitar a implantação:

#### Linux/Mac:

```bash
# Tornar o script executável
chmod +x deploy.sh

# Executar o script
./deploy.sh
```

#### Windows:

```powershell
# Executar o script
.\deploy.bat
```

Os scripts oferecem as seguintes opções:

1. Preparar backend para produção
2. Preparar frontend para produção
3. Executar backend localmente
4. Executar testes do backend

## Deploy no Render

### Passo a Passo para Deploy no Render

1. **Crie uma conta no Render**:

   -  Acesse [render.com](https://render.com)
   -  Registre-se usando sua conta GitHub ou e-mail

2. **Crie um novo Web Service**:

   -  No dashboard, clique em "New" e selecione "Web Service"
   -  Conecte sua conta GitHub e selecione este repositório

3. **Configure o serviço**:

   -  **Nome**: `todolist-api` (ou nome de sua preferência)
   -  **Ambiente**: `Java`
   -  **Build Command**: `./mvnw clean package -DskipTests`
   -  **Start Command**: `java -jar target/todolist-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod`
   -  **Plano**: Free (para testes) ou Individual (para produção)

4. **Configure as variáveis de ambiente**:

   -  Clique em "Advanced" > "Add Environment Variable" e adicione:
   -  `MONGODB_URI`: URL de conexão do MongoDB Atlas
   -  `ALLOWED_ORIGINS`: URL do frontend (ex: https://todolist-app.netlify.app)
   -  `SPRING_PROFILES_ACTIVE`: `prod`

5. **Clique em "Create Web Service"**

### MongoDB Atlas

Para configurar o MongoDB Atlas:

1. Crie uma conta em [mongodb.com/cloud/atlas](https://www.mongodb.com/cloud/atlas)
2. Crie um cluster (a opção gratuita é suficiente para testes)
3. Configure um usuário e senha para o banco de dados
4. Adicione seu IP atual à lista de IPs permitidos ou `0.0.0.0/0` para acesso de qualquer lugar
5. Obtenha a string de conexão e use-a como valor da variável `MONGODB_URI`

### Deploy em Produção

#### Heroku, Render, Railway ou similar

1. Configure as variáveis de ambiente:

   -  `MONGODB_URI`: URL de conexão do MongoDB Atlas
   -  `ALLOWED_ORIGINS`: Domínio do frontend (ex: https://seu-app.github.io)
   -  `SPRING_PROFILES_ACTIVE`: `prod`

2. Faça deploy do código:
   ```
   git push heroku main
   ```
   ou equivalente na plataforma escolhida.

#### Outras plataformas

-  Configure o perfil `prod` usando o arquivo `application-prod.properties`
-  Configure CORS para permitir acesso do domínio do frontend

## Implantação Separada

Este projeto foi separado em frontend e backend para permitir:

1. **Hospedagem Otimizada**: Frontend em CDNs/GitHub Pages e Backend em plataformas PaaS
2. **Escalabilidade Independente**: Escalar frontend e backend separadamente
3. **Tecnologias Específicas**: Usar as melhores plataformas para cada parte do sistema

Para o frontend, recomendamos:

-  GitHub Pages (gratuito e simples)
-  Netlify (gratuito com recursos avançados)
-  Vercel (gratuito com recursos avançados)

Para o backend, recomendamos:

-  Render (plano gratuito)
-  Railway (créditos gratuitos mensais)
-  Fly.io (plano gratuito)
-  Heroku (alguns recursos gratuitos)

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
-  Spring Data MongoDB
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

## Status da Separação Frontend/Backend

✅ **Status: Separação Concluída**

### O que foi feito:

1. **Estrutura Separada**:

   -  Backend (este repositório): Contém a API Spring Boot + MongoDB
   -  Frontend: Movido para um [repositório separado](https://github.com/isaacggr/todolist-frontend)

2. **Configuração de URLs**:

   -  Frontend: Configuração centralizada em `config.js`
   -  Backend: CORS configurado para aceitar origens específicas em produção

3. **Scripts de Implantação**:

   -  `deploy.sh` (Linux/Mac): Automatiza a preparação para produção
   -  `deploy.bat` (Windows): Versão Windows do script de implantação

4. **Configurações de Ambiente**:
   -  `application.properties`: Ambiente de desenvolvimento
   -  `application-prod.properties`: Ambiente de produção
   -  `Procfile`: Configuração para Heroku

### Próximos passos para implantação:

1. Fazer push do código backend para o GitHub
2. Implantar o backend no Render conforme instruções acima
3. Implantar o frontend no GitHub Pages ou Netlify
4. Atualizar a configuração do frontend com a URL do backend implantado
5. Configurar o backend para aceitar solicitações do domínio frontend
