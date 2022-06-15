FROM openjdk:18-jdk-alpine

LABEL Author="Michał Tworuszka, Oskar Hanke, Paweł Manowski"


ARG JAR_FILE=target/artifacts/game_shop_jar/*.jar
COPY ${JAR_FILE} app.jar
CMD ["java","-jar","/app.jar"]





