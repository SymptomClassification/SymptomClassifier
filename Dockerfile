FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

# Copy the target directory containing the Java Spring Boot application
COPY target ./target

# Copy the Python script
COPY src/main/resources/scripts/keywordClassify.py ./target

# Install Python 2.7 and the required dependencies
RUN apt-get update && \
    apt-get install -y python2.7 curl && \
    curl https://bootstrap.pypa.io/pip/2.7/get-pip.py --output get-pip.py && \
    python2.7 get-pip.py

# Install the required Python packages
RUN pip2.7 install requests==2.7.0

CMD ["java", "-jar", "target/SymptomChapter-0.0.1-SNAPSHOT.jar"]