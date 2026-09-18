# Fit Tracker 🏋️

**REST API para gerenciamento de treinos de musculação**, desenvolvida com Java 21 e Spring Boot 3.

O Fit Tracker é um projeto de backend criado para organizar treinos, exercícios e séries de musculação, permitindo que usuários autenticados gerenciem seus próprios dados por meio de uma API REST.

O projeto também foi desenvolvido como parte do meu aprendizado em desenvolvimento backend, aplicando conceitos de orientação a objetos, persistência de dados, autenticação e boas práticas de desenvolvimento.

## Tecnologias

* **Java**
* **Spring Boot**
* **Spring Data JPA**
* **Hibernate**
* **PostgreSQL**
* **Spring Security**
* **JWT** — autenticação baseada em tokens
* **Docker e Docker Compose**
* **Swagger / OpenAPI** — documentação da API

## Funcionalidades

* Cadastro e autenticação de usuários.
* Autenticação utilizando JWT.
* Gerenciamento de informações do próprio usuário.
* Criação e gerenciamento de treinos.
* Associação de exercícios aos treinos.
* Organização dos exercícios por ordem.
* Registro de séries, repetições, carga e RIR (*Reps in Reserve*).
* Validação de dados recebidos pela API.
* Tratamento centralizado de exceções.
* Restrição de acesso aos dados de acordo com o usuário autenticado.

## Modelo de domínio

A aplicação utiliza as seguintes entidades principais:

| Entidade          | Descrição                                                                           |
| ----------------- | ----------------------------------------------------------------------------------- |
| `User`            | Representa o usuário da aplicação.                                                  |
| `Exercise`        | Representa um exercício disponível.                                                 |
| `Workout`         | Representa um treino criado pelo usuário.                                           |
| `WorkoutExercise` | Relaciona um exercício a um treino, incluindo informações como ordem e observações. |
| `WorkoutSet`      | Representa uma série de um exercício, armazenando repetições, carga e RIR.          |

## Segurança

A API utiliza Spring Security e JWT para autenticação.

As operações protegidas utilizam a identidade do usuário autenticado para controlar o acesso aos recursos. Dessa forma, um usuário não deve conseguir consultar ou modificar treinos pertencentes a outro usuário.

## Executando o projeto

### Pré-requisitos

* Java — versão compatível com a configuração do projeto.
* Maven ou Maven Wrapper.
* Docker e Docker Compose, caso utilize o ambiente de banco de dados disponibilizado pelo projeto.
* PostgreSQL, caso opte por executar o banco localmente.

### 1. Clone o repositório

```bash
git clone https://github.com/gomesnw/fit-tracker.git
cd fit-tracker
```

### 2. Configure as variáveis de ambiente

Configure as credenciais e demais propriedades necessárias para a conexão com o banco de dados e para a autenticação JWT.

Exemplo de variáveis — ajuste os nomes conforme a configuração real da aplicação:

```env
POSTGRES_DB=jdbc:postgresql://localhost:5432/fit_tracker
POSTGRES_USER=seu_usuario
POSTGRES_PASSWOORD=sua_senha
JWT_KEY=sua_chave_secreta
```

**Não compartilhe credenciais ou chaves secretas reais no repositório.**

### 3. Inicie o banco de dados

Se estiver utilizando Docker Compose:

```bash
docker compose up -d
```

Confira se o serviço do banco de dados está em execução antes de iniciar a aplicação.

### 4. Execute a aplicação

Com o Maven Wrapper:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

Ajuste os comandos caso o projeto utilize outra configuração de execução.

## Documentação da API

A documentação interativa pode ser acessada pelo Swagger UI, com a aplicação em execução:

```text
http://localhost:8080/swagger-ui/index.html
```

A partir dela, é possível consultar os endpoints, visualizar os modelos de requisição e resposta e realizar chamadas à API.

> O endereço pode variar conforme a configuração da aplicação.

## Objetivos de aprendizado

Este projeto foi desenvolvido para consolidar conhecimentos em:

* Desenvolvimento de APIs REST com Spring Boot.
* Programação Orientada a Objetos com Java.
* Mapeamento objeto-relacional com JPA/Hibernate.
* Modelagem de relacionamentos entre entidades.
* Autenticação e autorização com Spring Security e JWT.
* Validação de dados e tratamento de exceções.
* Persistência de dados com PostgreSQL.
* Organização e manutenção de um projeto backend.

## Status

Projeto desenvolvido como parte do meu portfólio de estudos em desenvolvimento backend Java.

---

**Desenvolvido por Isaque Geovani**

[GitHub](https://github.com/gomesnw) · [LinkedIn](https://www.linkedin.com/in/gomesnw)
