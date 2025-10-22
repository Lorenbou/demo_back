# demo_back

Projeto backend em **Spring Boot** com **JPA/Hibernate** e **PostgreSQL**.  
Exemplo didático com **DTOs**, **validações (Bean Validation)**, **perfis (dev/prod/test)** e **Swagger/OpenAPI**.

---

## Stack

- Java 17+
- Spring Boot 3.x (Web, Validation, Data JPA, DevTools)
- PostgreSQL 14+ (H2 em memória nos testes)
- Gradle 8
- springdoc-openapi (Swagger UI)

---

## Requisitos

- **JDK 17+** configurado (`java -version` deve mostrar 17 ou superior)
- **PostgreSQL** em execução
- **Gradle Wrapper** (já incluso no projeto)

## Banco de Dados

Crie um database (ex.: `db_back`) no PostgreSQL e ajuste o `src/main/resources/application-*.properties`:

**application-dev.properties (exemplo)**
```properties
server.port=${port:8081}

spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/db_back
spring.datasource.username=postgres
spring.datasource.password=123456

spring.jpa.open-in-view=false
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
# Dialect costuma ser detectado automaticamente:
# spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

application.properties (perfil ativo)
spring.application.name=demo_back
spring.profiles.active=dev
springdoc.swagger-ui.path=/swagger-ui/index.html
Perfis disponíveis:

dev: PostgreSQL local (porta 8081 por padrão).

prod: PostgreSQL para produção.

test: H2 em memória (para testes).

Troque o perfil com:
spring.profiles.active=prod

Rodando o projeto
1) Via Gradle (recomendado)
# na raiz do projeto
./gradlew bootRun        # Linux/Mac
.\gradlew.bat bootRun    # Windows

2) Build do JAR
./gradlew clean build
java -jar build/libs/*.jar

Documentação da API (Swagger)
Swagger UI:
http://localhost:8081/swagger-ui/index.html (dev)
http://localhost:8080/swagger-ui/index.html (prod)
OpenAPI JSON: http://localhost:8081/v3/api-docs

Endpoints principais
GET /api/enums
Retorna listas de perfil, prioridade, status.

Técnicos
GET  /api/tecnicos
POST /api/tecnicos
Body (POST /api/tecnicos)
{
  "nome": "Ana Silva",
  "email": "ana@empresa.com",
  "perfil": "ADMIN",
  "especialidade": "Redes"
}

Clientes
GET  /api/clientes
POST /api/clientes
Body (POST /api/clientes)
{
  "nome": "João Souza",
  "email": "joao@email.com",
  "status": "ATIVO",
  "telefone": "48999990000"
}

Chamados
GET  /api/chamados
POST /api/chamados
Body (POST /api/chamados)
{
  "titulo": "Computador não liga",
  "descricao": "Ao apertar o botão nada acontece",
  "prioridade": "ALTA",
  "status": "ABERTO",
  "clienteId": 1,
  "tecnicoId": 1
}


Dicas:

Crie primeiro Técnico e Cliente para obter os IDs.

Use o /api/enums para ver os valores válidos dos enums.

DTOs & Validações
DTOs ficam em src/main/java/br/com/satc/demo/web/dto.

Validações com Jakarta Bean Validation: @NotBlank, @Email, @Size, etc.

Ative validações no controller com @Valid (já aplicado).

Estrutura do projeto
src/main/java/br/com/satc/demo
├─ domain
│  ├─ enums/            # Perfil, Prioridade, Status
│  ├─ model/            # Entidades JPA (Pessoa, Tecnico, Cliente, Chamado)
│  └─ repository/       # Repositórios Spring Data
├─ web
│  ├─ dto/              # DTOs (TecnicoDTO, ClienteDTO, ChamadoDTO, EnumsResponse)
│  └─ *.java            # Controllers REST
└─ resources
   ├─ application.properties
   ├─ application-dev.properties
   ├─ application-prod.properties
   └─ application-test.properties
   
   
Testes
./gradlew test


Erros comuns (Troubleshooting)
“Requires JVM 17”
Configure o JAVA_HOME/JAVA para JDK 17+ (veja seção Requisitos).

“Port already in use”
Altere server.port no application-*.properties ou encerre o processo que está usando a porta.

Conexão com PostgreSQL falhou
Verifique URL, username, password, database e se o serviço está ativo.