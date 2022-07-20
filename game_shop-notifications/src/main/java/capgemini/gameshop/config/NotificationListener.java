package capgemini.gameshop.config;

import capgemini.gameshop.orders.event.OrderCreatedEvent;
import capgemini.gameshop.orders.event.OrderDeletedEvent;
import capgemini.gameshop.orders.event.OrderAddProductEvent;
import capgemini.gameshop.service.EmailService;
import capgemini.gameshop.users.clients.AdressClient;
import capgemini.gameshop.users.clients.UserClient;
import capgemini.gameshop.users.dto.AdressDto;
import capgemini.gameshop.users.event.AdressCreatedEvent;
import capgemini.gameshop.users.event.AdressDeletedEvent;
import capgemini.gameshop.users.event.UserDeletedEvent;
import capgemini.gameshop.users.event.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final EmailService emailService;
    private final UserClient userClient;
    private final AdressClient adressClient;


    @KafkaListener(topics = "orders-create", groupId = "order-creation")
    public void listen(@Payload OrderCreatedEvent event){
        String email = userClient.getUserById(event.getUserId()).getEmail();
        emailService.send(email,
                "Order Creation Listener",
                "New Order",
                "New order with id: " + event.getOrderId() + " was created");
    }

    @KafkaListener(topics = "orders-extend", groupId = "order-addition")
    public void listen(@Payload OrderAddProductEvent event){
        String email = userClient.getUserById(event.getUserId()).getEmail();
        emailService.send(email,
                "Order Addition Listener",
                "Added new Product",
                "Product with id: " + event.getProductId() + " was added to order with id: " + event.getOrderId());
    }

    @KafkaListener(topics = "orders-delete", groupId = "order-delete")
    public void listen(@Payload OrderDeletedEvent event){
        String email = userClient.getUserById(event.getUserId()).getEmail();
        emailService.send(email,
                "Order Delete Listener",
                "Order Deleted",
                "Order with id: " + event.getOrderId() + " was deleted from your account");
    }

    @KafkaListener(topics = "users-create", groupId = "user-creation")
    public void listen(@Payload UserRegisteredEvent event){
        String email = userClient.getUserById(event.getUserId()).getEmail();
        emailService.send(email,
                "Users Creation Listener",
                "Registration",
                "Congratulations! Registration was successful ");
    }

    //makes request on id that was deleted
    //sounds problematic
    //keep/modify or remove
    @KafkaListener(topics = "users-delete", groupId = "user-delete")
    public void listen(@Payload UserDeletedEvent event){
        String email = userClient.getUserById(event.getUserId()).getEmail();
        emailService.send(email,
                "User Delete Listener",
                "Account Deleted",
                "User with id: " + event.getUserId() + " was deleted");
    }

    @KafkaListener(topics = "adresses-create", groupId = "adress-create")
    public void listen(@Payload AdressCreatedEvent event){
        String email = userClient.getUserById(event.getUserId()).getEmail();
        AdressDto adress = adressClient.getAdress(event.getAdressId());
        var message = "For user with id: " + event.getUserId() + " added new adress: " + adress.toString();
        emailService.send(email,
                "Adress Created Listener",
                "Added Adress",
                message);
    }

    @KafkaListener(topics = "adresses-delete", groupId = "adress-delete")
    public void listen(@Payload AdressDeletedEvent event){
        String email = userClient.getUserById(event.getUserId()).getEmail();
        emailService.send(email,
                "Adress Deleted Listener",
                "Deleted Adress",
                "Deleted adress with id: " + event.getAdressId() + " for user with id: " + event.getUserId());
    }
}
