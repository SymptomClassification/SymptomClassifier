FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

# Copy the target directory containing the Java Spring Boot application
COPY target ./target

# Install Python and the required dependencies
RUN apt-get update && apt-get install -y python python-pip

# Copy the requirements.txt file to the container
COPY requirements.txt .

# Install the required Python packages
RUN pip install -r requirements.txt

CMD ["java", "-jar", "target/SymptomChapter-0.0.1-SNAPSHOT.jar"]