package capgemini.gameshop.config;

import capgemini.gameshop.orders.event.OrderCreatedEvent;
import capgemini.gameshop.orders.event.OrderDeletedEvent;
import capgemini.gameshop.orders.event.OrderExtendedEvent;
import capgemini.gameshop.service.EmailService;
import capgemini.gameshop.users.clients.AdressClient;
import capgemini.gameshop.users.clients.UserClient;
import capgemini.gameshop.users.dto.AdressDto;
import capgemini.gameshop.users.dto.UserDto;
import capgemini.gameshop.users.event.AdressCreatedEvent;
import capgemini.gameshop.users.event.UserDeletedEvent;
import capgemini.gameshop.users.event.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


//TODO - make it work with interface IntegrationEvent - Error with Deserialization
@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final EmailService emailService;

    private final UserClient userClient;
//
//    private final OrderClient orderClient;
//
    private final AdressClient adressClient;


    @KafkaListener(topics = "orders-create", groupId = "order-creation")
    public void listen(@Payload OrderCreatedEvent event){
        emailService.send(
                "User with id: " + event.getUserId().toString(),
                "Order Creation Listener",
                "New Order",
                "New order with id: " + event.getOrderId() + " was created");
    }

    @KafkaListener(topics = "orders-extend", groupId = "order-addition")
    public void listen(@Payload OrderExtendedEvent event){
        emailService.send(
                "User with id: " + event.getUserId().toString(),
                "Order Addition Listener",
                "Added new Product",
                "Product with id: " + event.getProductId() + " was added to order with id: " + event.getOrderId());
    }

    @KafkaListener(topics = "orders-delete", groupId = "order-delete")
    public void listen(@Payload OrderDeletedEvent event){
        emailService.send("User with id: " + event.getUserId().toString(),
                "Order Delete Listener",
                "Order Deleted",
                "Order with id: " + event.getOrderId() + " was deleted from your account");
    }

    @KafkaListener(topics = "users-create", groupId = "user-creation")
    public void listen(@Payload UserRegisteredEvent event){
        UserDto user = userClient.getUserById(event.getUserId());
        emailService.send(
                "User with id: " + event.getUserId().toString(),
                "Users Creation Listener",
                "Registration",
                "Congratulations! Registration was successful ");
    }
    @KafkaListener(topics = "users-delete", groupId = "user-delete")
    public void listen(@Payload UserDeletedEvent event){
        emailService.send(
                "User with id: " + event.getUserId().toString(),
                "User Delete Listener",
                "Account Deleted",
                "User with id: " + event.getUserId() + " was deleted");
    }

    @KafkaListener(topics = "adresses-create", groupId = "adress-create")
    public void listen(@Payload AdressCreatedEvent event){
        emailService.send("User with id: " + event.getUserId().toString(),
                "Adress Created Listener",
                "Added Adress",
                "Added new adress with id: " + event.getAdressId() + " for user with id: " + event.getUserId());
    }
}
