package capgemini.gameshop.users.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdressDeletedEvent implements IntegrationEvent {

    private Long adressId;
    private Long userId;

    //private LocalDateTime createdAt;
}
