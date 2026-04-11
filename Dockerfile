FROM maven:3.9.9-eclipse-temurin-8 AS builder
WORKDIR /app

COPY pom.xml .
COPY src ./src

CMD ["mvn", "clean", "test"]
