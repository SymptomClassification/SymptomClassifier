# Build a Python image that includes the "requests" library
FROM python:3.9-slim
RUN pip install requests

# Build your Java application image
FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

# Install Jython
RUN apt-get update && apt-get install -y jython

COPY --from=0 /usr/local/lib/python3.9/site-packages /usr/local/lib/python3.9/site-packages

COPY target ./target

CMD ["java", "-jar", "target/SymptomChapter-0.0.1-SNAPSHOT.jar"]
