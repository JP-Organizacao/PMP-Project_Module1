# Projeto FATEC - CRUD de Pessoas

Sistema de gerenciamento de pessoas desenvolvido com Spring Boot e MongoDB, utilizando arquitetura modular com separação entre camadas de domínio e infraestrutura. O projeto implementa operações CRUD para o gerenciamento de pessoas, com integração ao Graylog para monitoramento e análise de logs.

## Tecnologias Utilizadas

### Backend
- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Data MongoDB**
- **Spring Validation** 

### Banco de Dados
- **MongoDB**

### Monitoramento
- **Graylog 7.0.0**
- **OpenSearch 2.4.0**

## Pré-requisitos

- Java 21 ou superior
- Maven
- Docker e Docker Compose
- Git

## Como Rodar o Projeto

### 1. Clone o Repositório

```bash
git clone <url-do-repositorio>
cd modulo1
```

### 2. Inicie os Serviços Docker

```bash
docker-compose up -d
```

Isso irá iniciar:
- **MongoDB** na porta `27017`
- Interface do **Graylog** na porta `9000`

### 3. Compile o Projeto

```bash
# Compile todos os módulos a partir da raiz
./mvnw clean install
```

### 4. Execute a Aplicação

```bash
# Rode o comando a partir da raiz do projeto
./mvnw spring-boot:run -pl spring
```

### 5. Acesse a Aplicação

- **API REST:** http://localhost:8080
- **Graylog:** http://localhost:9000
  - Usuário: `admin`
  - Senha: `root`

## Endpoints da API

A aplicação expõe endpoints REST para gerenciamento de pessoas:

- `GET /pessoas` - Lista todas as pessoas
- `GET /pessoas/{id}` - Busca uma pessoa por ID
- `POST /pessoas` - Cria uma nova pessoa
- `PUT /pessoas/{id}` - Atualiza uma pessoa existente
- `DELETE /pessoas/{id}` - Remove uma pessoa, alterando o campo `ativo` para `false`

