package capgemini.gameshop.orders.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderExtendedEvent implements IntegrationOrderEvent{
    private Long orderId;
    private Long userId;
    private Long productId;
}
