package capgemini.gameshop.config;

import capgemini.gameshop.dto.UserDto;
import capgemini.gameshop.event.ProductCreatedEvent;
import capgemini.gameshop.event.UserRegisteredEvent;
import capgemini.gameshop.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final EmailService emailService;
    private final RestTemplate restTemplate;

    @KafkaListener(topics = "products", groupId = "notification")
    public void listen(@Payload ProductCreatedEvent event){

    }

    @KafkaListener(topics = "users", groupId = "notification")
    public void listen(@Payload UserRegisteredEvent event){
        ResponseEntity<UserDto> response =
                restTemplate.getForEntity("localhost:8090/api/users/" + event.getUserId(), UserDto.class);
        if (response.getStatusCode() == HttpStatus.OK){
            UserDto userDto = response.getBody();
            emailService.send(userDto.getEmail(), "Server", "Registration", "Congratulations! ");
        }
    }
}
