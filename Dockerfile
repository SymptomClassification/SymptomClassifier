FROM openjdk:11-jre-slim

WORKDIR /app

# Install Jython
RUN apt-get update && apt-get install -y jython

# Install Python library "requests"
RUN jyson install requests

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["java", "-jar", "your-app.jar"]
