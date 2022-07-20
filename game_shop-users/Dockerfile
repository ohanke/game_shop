FROM openjdk

LABEL Author="Michał Tworuszka, Oskar Hanke, Paweł Manowski"

ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} users.jar

ENTRYPOINT ["java","-jar","users.jar"]





