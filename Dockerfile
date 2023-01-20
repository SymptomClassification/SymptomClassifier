FROM maven:3.8.2-jdk-8
RUN mvn clean package
COPY target/SymptomChapter-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
