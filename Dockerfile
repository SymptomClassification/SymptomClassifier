FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

# Copy the target directory containing the Java Spring Boot application
COPY target ./target

# Copy the Python scripts and requirements.txt
COPY src/main/resources/scripts/spacyClassification ./target/spacyClassification

COPY requirements.txt ./target/requirements.txt

# Copy the keywordClassification Python scripts
COPY src/main/resources/scripts/keywordClassifyName.py ./target
COPY src/main/resources/scripts/keywordClassifyId.py ./target

# Install Python 3.9 and the required dependencies
RUN apt-get update && \
    apt-get install -y python3 python3-pip && \
    pip3 install -r ./target/requirements.txt && \
    pip3 install https://s3-us-west-2.amazonaws.com/ai2-s2-scispacy/releases/v0.5.1/en_core_sci_md-0.5.1.tar.gz

# Run the Python scripts
RUN python3 ./target/spacyClassification/extract_training_data.py
RUN python3 ./target/spacyClassification/trainModel.py
RUN python3 ./target/spacyClassification/machineLearningTesting.py

CMD ["java", "-jar", "target/SymptomChapter-0.0.1-SNAPSHOT.jar"]