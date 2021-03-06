package capgemini.gameshop.orders.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * This class represents the event of creating a new Order. Takes in:
 * orderId - id of the Order in which the event took place,
 * userId - id of the User who owns the Order,
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent implements IntegrationOrderEvent {
    private Long orderId;

    private Long userId;

    //private LocalDateTime createdAt;
}
