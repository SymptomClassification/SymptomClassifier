FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

RUN apt-get update && apt-get install -y python3
RUN apt-get install -y python3-pip

COPY target ./target
COPY src/main/resources/scripts/keywordClassify.py ./src/main/resources/scripts/
COPY requirements.txt .

RUN pip3 install -r requirements.txt

CMD ["java", "-jar", "target/SymptomChapter-0.0.1-SNAPSHOT.jar"]