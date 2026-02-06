# Bootcamp - Aplicação Spring Boot

Aplicação desenvolvida em Spring Boot para gerenciamento de produtos, com deploy no Microsoft Azure.

## Tecnologias Utilizadas

- Java 21
- Spring Boot 4.0.2
- Spring Data JPA
- SpringDoc OpenAPI (Swagger)
- H2 Database (em memória)
- Maven
- Lombok

## Pré-requisitos

- JDK 21 ou superior
- Maven 3.6+
- Git

## Configuração e Execução Local

### 1. Clonar o repositório
```bash
git clone https://github.com/warthurzin/springboot-bootcamp
cd springboot-bootcamp
```


### 2. Executar a aplicação
```bash
mvn spring-boot:run
```

A aplicação estará disponível em:
- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- H2 Console: http://localhost:8080/h2-console

### 3. Credenciais H2 Console

- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (deixar em branco)

### 4. Build do projeto
```bash
mvn clean package -DskipTests
```

A aplicação estará disponível em:
- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- H2 Console: http://localhost:8080/h2-console

O arquivo JAR será gerado em: `target/bootcamp-0.0.1-SNAPSHOT.jar`

## Estrutura de Configuração

### application.yaml
```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:
  h2:
    console:
      enabled: true
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    show-sql: true
    hibernate:
      ddl-auto: update

```

## Deploy no Azure

### Recursos Criados

1. **Resource Group**: `rg-bootcamp-azure`
2. **App Service**: `bootcamp-produtos-arthurmarques`
    - Runtime: Java 21
    - Sistema Operacional: Linux
    - Região: Brazil South
    - Plano: F1 (Free Tier)


### Processo de Deploy

O deploy é realizado automaticamente via GitHub Actions sempre que há um push para a branch principal.

Alternativamente, é possível fazer deploy manual via Azure CLI:
```bash
az login
az webapp deploy \
  --resource-group rg-bootcamp-azure \
  --name bootcamp-produtos-arthurmarques \
  --src-path target/bootcamp-0.0.1-SNAPSHOT.jar \
  --type jar
```

## URL da Aplicação em Produção

**URL Base**: https://bootcamp-produtos-arthurmarques-ethrh3hgakawajb7.brazilsouth-01.azurewebsites.net/

**Endpoints principais**:
- API: https://bootcamp-produtos-arthurmarques-ethrh3hgakawajb7.brazilsouth-01.azurewebsites.net/produtos
- Swagger UI: https://bootcamp-produtos-arthurmarques-ethrh3hgakawajb7.brazilsouth-01.azurewebsites.net/swagger-ui/index.html
- API Docs: https://bootcamp-produtos-arthurmarques-ethrh3hgakawajb7.brazilsouth-01.azurewebsites.net/v3/api-docs

## Endpoints da API

### Produtos

- `GET /produtos` - Listar todos os produtos
- `GET /produtos/{id}` - Buscar produto por ID
- `POST /produtos` - Criar novo produto
- `PUT /produtos/{id}` - Atualizar produto
- `DELETE /produtos/{id}` - Deletar produto

## Monitoramento

Para visualizar logs da aplicação no Azure:

1. Acessar o App Service no Portal Azure
2. Navegar até "Log stream" no menu lateral
3. Acompanhar logs em tempo real
