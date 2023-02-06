FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

# Copy the Java project files
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src

# Install required Python packages
RUN apt-get update && apt-get install -y python3-pip
COPY requirements.txt .
RUN pip3 install -r requirements.txt

# Copy the Python script
COPY my_python_script.py .

CMD ["./mvnw", "spring-boot:run"]
