package capgemini.gameshop.users.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdressCreatedEvent implements IntegrationEvent {

    private Long adressId;
    private Long userId;

    //private LocalDateTime createdAt;
}
