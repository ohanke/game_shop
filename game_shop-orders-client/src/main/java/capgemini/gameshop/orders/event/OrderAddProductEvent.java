package capgemini.gameshop.orders.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents the event of adding Product to Order. Takes in:
 * orderId - id of the Order in which the event took place,
 * userId - id of the User who owns the Order,
 * productId - id of the Product that has been added to the Order.
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddProductEvent implements IntegrationOrderEvent{
    private Long orderId;
    private Long userId;
    private Long productId;
}
