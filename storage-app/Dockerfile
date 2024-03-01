FROM maven:3.9.6-eclipse-temurin-21-alpine as build

WORKDIR storage-app

COPY pom.xml .
COPY src src

RUN --mount=type=cache,target=/root/.m2 mvn package

FROM eclipse-temurin:21-jre-alpine

WORKDIR application

COPY --from=build storage-app/target/storage-app-0.0.1-SNAPSHOT.jar ./

ENTRYPOINT ["java", "-jar", "storage-app-0.0.1-SNAPSHOT.jar"]