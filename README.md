# ChallengeJava2025

Este é um projeto Spring Boot desenvolvido como parte de um desafio de programação. O projeto implementa uma API REST com funcionalidades de cache e persistência de dados.

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.4.4
- Spring Data JPA
- Spring Web
- Spring Validation
- Lombok
- H2 Database
- Maven

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven
## 🔧 Configuração do Ambiente

1. Clone o repositório:
```bash
git clone [URL_DO_REPOSITÓRIO]
```

2. Navegue até o diretório do projeto:
```bash
cd ChallengeJava2025
```

3. Compile o projeto:
```bash
mvn clean install
```

4. Execute o projeto:
```bash
mvn spring-boot:run
```

## 🛠️ Funcionalidades

- API REST com endpoints para gerenciamento de dados
- Cache configurado para otimização de performance
- Validação de dados
- Banco de dados H2 para desenvolvimento
- Integração com Spring Data JPA

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/example/ChallengeJava2025/
│   │       ├── config/         # Configurações do Spring
│   │       ├── controller/     # Controladores REST
│   │       ├── model/          # Entidades
│   │       ├── repository/     # Repositórios JPA
│   │       └── service/        # Lógica de negócio
│   └── resources/
│       └── application.properties
```

## 🔄 Cache

O projeto utiliza cache para otimizar o desempenho. A configuração do cache está implementada em `CacheConfig.java` usando `ConcurrentMapCacheManager`.

## 📝 Documentação da API

A documentação da API está disponível em:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI: `http://localhost:8080/v3/api-docs`

## 🧪 Testes

Para executar os testes:
```bash
mvn test
```

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## ✨ Autores

- RM557812 Matheus Munuera Ueti Ferraz 
- RM553907 Pedro Gomes 
- RM555197 Luiz Felipe Abreu
