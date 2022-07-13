package capgemini.gameshop.orders.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDeletedEvent implements IntegrationOrderEvent{
    private Long userId;
    private Long orderId;
}
