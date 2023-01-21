FROM maven:3.8.2-jdk-8
RUN mkdir /app
WORKDIR /app
COPY . /app
RUN mvn clean install
CMD "mvn" "exec:java"

