FROM openjdk:18-alpine

LABEL Author="Michał Tworuszka, Oskar Hanke, Paweł Manowski"

RUN echo "Game Shop image - capgemini project"

ADD src/main/java/capgemini/gameshop/GameShopApplication.java /app/

WORKDIR /app/

RUN javac GameShopApplication.java

CMD java GameShopApplication





