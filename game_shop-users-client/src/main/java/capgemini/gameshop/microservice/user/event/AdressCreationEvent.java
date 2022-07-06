package capgemini.gameshop.microservice.user.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdressCreationEvent {
    private Long id;

    private Long userId;

    private LocalDateTime createdAt;
}
