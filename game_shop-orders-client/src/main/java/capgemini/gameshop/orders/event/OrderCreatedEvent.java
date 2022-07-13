package capgemini.gameshop.orders.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent implements IntegrationOrderEvent {
    private Long orderId;

    private Long userId;

    //private LocalDateTime createdAt;
}
