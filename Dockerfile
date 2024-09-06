FROM openjdk:17-jdk-slim AS build

RUN apt-get update && \
    apt-get install -y maven netcat && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src /app/src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

RUN apt-get update && \
    apt-get install -y netcat && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY --from=build /app/target/marketplace-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["/bin/sh", "-c", "until nc -z mysql 3306; do echo 'Waiting for MySQL...'; sleep 2; done; exec java -jar app.jar"]