FROM openkbs/jdk-mvn-py3

WORKDIR /app

COPY target ./target

CMD ["java", "-jar", "target/SymptomChapter-0.0.1-SNAPSHOT.jar"]
