package capgemini.gameshop.microservice.order.dto;

import capgemini.gameshop.microservice.order.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * DTO class for entities of Order
 *
 * Getters, full and empty constructor provided by lombok, also one custom constructor
 * for objects with no id.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    @NotNull
    private Long userId;
//    @Positive
    private double totalValue;
//    @NotNull
    private OrderStatus orderStatus;

    private Set<ProductDto> products;
    
    public OrderDto(Long userId, double totalValue, OrderStatus orderStatus) {
        this.id = null;
        this.userId = userId;
        this.totalValue = totalValue;
        this.orderStatus = orderStatus;
    }
}
