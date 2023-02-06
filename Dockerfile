# Use a minimal base image as the first stage
FROM openjdk:11-jre-slim as build

WORKDIR /app

# Install Jython
RUN apt-get update && apt-get install -y jython

# Install Python library "requests"
RUN jython -m ensurepip
RUN jython -m pip install requests

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

# Start a new stage using the same base image
FROM openjdk:11-jre-slim

WORKDIR /app

# Copy only the compiled Java artifacts from the build stage
COPY --from=build /app/target/ .

CMD ["java", "-jar", "your-app.jar"]
