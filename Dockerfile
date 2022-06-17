FROM openjdk:18-jdk-alpine

LABEL Author="Michał Tworuszka, Oskar Hanke, Paweł Manowski"

EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
CMD ["java","-jar","/app.jar"]





