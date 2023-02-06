FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

COPY target ./target

CMD ["java", "-jar", "target/SymptomChapter-0.0.1-SNAPSHOT.jar"]
