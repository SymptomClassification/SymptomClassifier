FROM maven:3.8.2-jdk-8
WORKDIR /SymptomChapter
COPY target/SymptomChapter-0.0.1-SNAPSHOT.jar app.jar
RUN mvn clean package
CMD mvn spring-boot:run
