FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

# Install Jython
RUN apt-get update && apt-get install -y jython

# Install Python library "requests"
RUN jython -m ensurepip
RUN jython -m pip install requests

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
