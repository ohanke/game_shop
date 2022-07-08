package capgemini.gameshop.config;

import capgemini.gameshop.event.ProductCreatedEvent;
import capgemini.gameshop.event.UserRegisteredEvent;
import capgemini.gameshop.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final EmailService emailService;

    @KafkaListener(topics = "products", groupId = "notification")
    public void listen(@Payload ProductCreatedEvent event){

    }

    @KafkaListener(topics = "users", groupId = "notification")
    public void listen(@Payload UserRegisteredEvent event){

    }
}
