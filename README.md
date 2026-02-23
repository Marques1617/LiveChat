# LiveChat
LiveChat is a real-time chat application built with Java Spring Boot and Spring Security for authentication. The frontend uses HTML templates with Bootstrap, and JavaScript handles real-time communication with the backend via WebSockets and STOMP.
User data is stored in a PostgreSQL database running inside a Docker container, ensuring easy setup and portability. LiveChat supports secure, authenticated messaging in real time between multiple users.

## ⚙ Technologies Used

| Technology        | Description                       |
|------------------|-----------------------------------|
| Java 21           | Programming language              |
| Spring Boot       | Application framework             |
| Spring Security   | Authentication & Authorization    |
| WebSocket               | Communication technology       |
| STOMP        | Text Orientated Messaging Protocol |
| Docker	    | Database - Postgres                          |
| Maven		    | Dependency management             |
|Postman	    | API testing                       |
| JUnit       | Tests                             |


## 🗄️ DataBase Configuration 

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

### Docker 
- Docker-compose.yml
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

## Running the docker file (detached mode)
```docker compose up -d ```

## ️ Access the Container
```docker exec -it <name_container> bash```
### Connect to the user
- psql -U <user_name>

### Connect with the database
- \c <name_database>

    
    
















