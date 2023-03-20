# Pre-requisites
- Java JDK 17
- Docker
- Docker Compose
- Table Operations Service is running

```bash
./mvnw clean package
docker compose up
```


# Notes
- The docker compose up command takes more than 5 minutes to start the application. Please be patient.

# After the application is up and running
- You can access the Swagger UI of application at http://localhost:8090/swagger-ui/index.html
- To try from terminal, you can use the following curl command
```bash
curl -X 'GET' \
'http://localhost:8090/spacy/classifySymptom/head%20ache' \
-H 'accept: application/json'
```