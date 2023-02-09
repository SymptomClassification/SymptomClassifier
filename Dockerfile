FROM openkbs/jdk-mvn-py3

WORKDIR /app

COPY target ./target
COPY src/main/resources/scripts/keywordClassify.py ./src/main/resources/scripts/
COPY requirements.txt .

RUN pip3 install -r requirements.txt

CMD ["java", "-jar", "SymptomChapter-0.0.1-SNAPSHOT.jar"]
