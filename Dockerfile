FROM maven:3-openjdk-18 as builder

ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME

COPY . $HOME

RUN mvn clean install

WORKDIR $HOME/eureka-server
RUN mvn clean install spring-boot:repackage

WORKDIR $HOME/config-server
RUN mvn clean install spring-boot:repackage

WORKDIR $HOME/game_shop-orders-client
RUN mvn clean install

WORKDIR $HOME/game_shop-users-client
RUN mvn clean install

WORKDIR $HOME/game_shop-notifications
RUN mvn clean install spring-boot:repackage

WORKDIR $HOME/game_shop-orders
RUN mvn clean install spring-boot:repackage

WORKDIR $HOME/game_shop-users
RUN mvn clean install spring-boot:repackage


FROM openjdk:18-jdk-alpine
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME

#LABEL Author="Michał Tworuszka, Oskar Hanke, Paweł Manowski"

COPY --from=builder $HOME/eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar

ENTRYPOINT ["java","-jar","/eureka-server.jar"]

COPY --from=builder $HOME/config-server/target/config-server-0.0.1-SNAPSHOT.jar config-server.jar

ENTRYPOINT ["java","-jar","/config-server.jar"]

COPY --from=builder $HOME/game_shop-notifications/target/game_shop-notifications-0.0.1-SNAPSHOT.jar notifications.jar

ENTRYPOINT ["java","-jar","notifications.jar"]

COPY --from=builder $HOME/game_shop-orders/target/game_shop-orders-0.0.1-SNAPSHOT.jar orders.jar

ENTRYPOINT ["java","-jar","orders.jar"]

COPY --from=builder $HOME/game_shop-users/target/game_shop-users-0.0.1-SNAPSHOT.jar users.jar

ENTRYPOINT ["java","-jar","users.jar"]

