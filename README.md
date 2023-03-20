```bash
./mvnw test
./mvnw clean package
docker compose up
```

# Pre-requisites
- Java JDK 17
- Docker
- Docker Compose

# Notes
- The docker compose up command takes more than 5 minutes to start the application. Please be patient.

# After the application is up and running
- You can access the Swagger UI of application at http://localhost:8090/swagger-ui.html/index.html