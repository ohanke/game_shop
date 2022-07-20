FROM openjdk

LABEL Author="Michał Tworuszka, Oskar Hanke, Paweł Manowski"

ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} config-server.jar

ENTRYPOINT ["java","-jar","config-server.jar"]





