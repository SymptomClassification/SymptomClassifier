# Build a Python image that includes the "requests" library
FROM python:3.9-slim
RUN pip install requests

# Build your Java application image
FROM openjdk:11-jre-slim

WORKDIR /app

# Install Jython
RUN apt-get update && apt-get install -y jython

COPY --from=0 /usr/local/lib/python3.9/site-packages /usr/local/lib/python3.9/site-packages
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["java", "-jar", "your-app.jar"]
