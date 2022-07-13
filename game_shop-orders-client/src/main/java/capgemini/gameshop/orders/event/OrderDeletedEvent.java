package capgemini.gameshop.orders.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents the event of deleting a Order. Takes in:
 * orderId - id of the Order in which the event took place,
 * userId - id of the User who owns the Order,
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDeletedEvent implements IntegrationOrderEvent{
    private Long userId;
    private Long orderId;
}
