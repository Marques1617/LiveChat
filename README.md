# LiveChat
LiveChat is a real-time chat application built with Java Spring Boot and Spring Security for authentication. The frontend uses HTML templates with Bootstrap, and JavaScript handles real-time communication with the backend via WebSockets and STOMP.
User data is stored in a PostgreSQL database running inside a Docker container, ensuring easy setup and portability. LiveChat supports secure, authenticated messaging in real time between multiple users.

## Features 
- Real-time messaging between multiple users
- Secure authentication with Spring Security
- User-friendly interface with Bootstrap
- Messages broadcast in real-time using WebSockets and STOMP
- Messages are sanitized to prevent XSS attacks
- Persistent user data in PostgreSQL

## Table of Contents
- [Tech Stack](#tech-stack)
- [Installation](#installation)
- [Running the application](#running-the-application)
- [Project Structure](#project-structure)

## ⚙ Tech Stack

| Component        | Technology                       |
|------------------|-----------------------------------|
| Backend          | Java, SpringBoot, SpringSecurity               |
| Frontend         | Html, JavaScript (STOMP over Websocket, BootStrap) |
| Database        | PostgreSQL (Dockerized)|
| Build Tools         | Maven or Gradle |
| Containerization       | Docker             |

# Installation

## Clone the repository
   ```git clone https://github.com/Marques1617/LiveChat.git ```
   
   ```cd livechat```


## Configure application properties

### Application Properties 

```properties
spring.application.name=livechatms

spring.datasource.url=jdbc:postgresql://localhost:5331/<your_db>
spring.datasource.username=<your_user>
spring.datasource.password=<your_password>

spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update 
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.show-sql=true 
```

## Start PostgreSQL with Docker
   
### Docker-compose.yml

```
services:
  db:
    container_name: <container_name>
    image: postgres:latest
    environment:
      POSTGRES_USER: <your_user>
      POSTGRES_PASSWORD: <your_password>
      PGDATA: /data/postgres
    volumes:
      - db:/data/postgres
    ports:
      - "5331:5432"
    networks:
      - db
    restart: unless-stopped
  
networks:
  db:
    driver: bridge

volumes:
  db:

```

### Running the docker file (detached mode)
```docker compose up -d ```

### ️ Access the Container
```docker exec -it <name_container> bash```
### Connect to the user
- psql -U <user_name>

### Connect with the database
- \c <name_database>

### Build and run the application
   ```bash
   ./mvn clean install
   ./mvn spring-boot:run
   ```
## Running the application
- Open your browser and navigate to:
  ``` http://localhost:8080```
- Register a new user or log in with existing credentials.
- Start chatting in real time with other connected users.

## Architecture
![LiveChat Architecture](./assets/livechat-architecture.png)

## Project Structure
```
src/
 ├─ main/
 │   ├─ java/tech/buildrun/livechatms/
 │   │    ├─ controller/      # WebSocket and REST controllers
 │   │    ├─ domain/          # ChatInput, ChatOutput
 │   │    ├─ repository/      # UserRepo
 │   │    ├─ model/           # User entity & UserPrincipal
 │   │    ├─ service          # User Service & MyUserDetailesService
 │   │    └─ config/          # Spring Security & WebSocket configuration
 │   └─ resources/
 │        ├─ templates/       # HTML Bootstrap templates
 │        ├─ static/          # Javascript and Css
 │        └─ application.properties
```

## License
This project is licensed under the MIT License.
