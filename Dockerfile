FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

# Copy the target directory containing the Java Spring Boot application
COPY target ./target

# Copy the Python script
COPY src/main/resources/scripts/keywordClassifyName.py ./target
COPY src/main/resources/scripts/keywordClassifyId.py ./target

# Install Python 3.9 and the required dependencies
RUN apt-get update && \
    apt-get install -y python3 python3-pip

CMD ["java", "-jar", "target/SymptomChapter-0.0.1-SNAPSHOT.jar"]