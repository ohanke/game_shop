package capgemini.gameshop.config;

import capgemini.gameshop.users.clients.AdressClient;
import capgemini.gameshop.orders.clients.OrderClient;
import capgemini.gameshop.users.clients.UserClient;
import capgemini.gameshop.users.dto.AdressDto;
import capgemini.gameshop.users.dto.UserDto;
import capgemini.gameshop.users.event.AdressCreatedEvent;
import capgemini.gameshop.orders.event.OrderCreatedEvent;
import capgemini.gameshop.users.event.UserDeletedEvent;
import capgemini.gameshop.users.event.UserRegisteredEvent;
import capgemini.gameshop.service.EmailService;
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

    private final OrderClient orderClient;

    private final AdressClient adressClient;


    @KafkaListener(topics = "orders", groupId = "notification")
    public void listen(@Payload OrderCreatedEvent event){

        System.out.println(event.getCreatedAt());

    }

    @KafkaListener(topics = "users", groupId = "notification")
    public void listen(@Payload UserRegisteredEvent event){

        UserDto user = userClient.getUserById(event.getUserId());
        emailService.send(user.getEmail(), "Server", "Registration", "Congratulations! ");
    }

    @KafkaListener(topics = "users", groupId = "notification")
    public void listen(@Payload UserDeletedEvent event){

        UserDto user = userClient.getUserById(event.getUserId());
        emailService.send(user.getEmail(), "Server", "Deletion of User", "User with mail: " + user.getEmail() + " is now deleted");
    }

    @KafkaListener(topics = "adresses", groupId = "notification")
    public void listen(@Payload AdressCreatedEvent event){

        AdressDto adress = adressClient.getAdress(event.getId());
        UserDto user = userClient.getUserById(event.getUserId());
        String message = "For user: " + user.getEmail() + " address has been added: " + adress.toString();

        emailService.send(user.getEmail(), "Server", "New address", message);
    }

}
