package capgemini.gameshop.config;

import capgemini.gameshop.event.ProductCreatedEvent;
import capgemini.gameshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final ProductService productService;

    @KafkaListener(topics = "products", groupId = "notification")
    public void listen(@Payload ProductCreatedEvent event){

    }
}
