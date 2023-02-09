FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

RUN apt-get update && apt-get install -y python3
RUN apt-get install -y python3-pip

COPY target ./target
COPY src/main/resources/scripts/keywordClassify.py ./src/main/resources/scripts/
COPY requirements.txt .
COPY target/dependency/jython-standalone-2.7.3.jar /app/jython.jar

RUN pip3 install -r requirements.txt

CMD ["java", "-cp", "jython.jar", "-jar", "target/SymptomChapter-0.0.1-SNAPSHOT.jar"]