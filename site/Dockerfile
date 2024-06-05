FROM openjdk:17-jdk-alpine
ARG JARFILE=target/*.jar
COPY ./target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
