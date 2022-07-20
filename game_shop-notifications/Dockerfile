FROM openjdk

LABEL Author="Michał Tworuszka, Oskar Hanke, Paweł Manowski"

ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} notifications.jar

ENTRYPOINT ["java","-jar","notifications.jar"]





