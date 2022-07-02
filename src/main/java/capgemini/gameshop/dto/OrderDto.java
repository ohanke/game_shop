package capgemini.gameshop.dto;

import capgemini.gameshop.entity.OrderStatus;
import capgemini.gameshop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
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
    @Positive
    private double totalValue;
    @NotNull
    private OrderStatus orderStatus;

    private Set<ProductDto> products;
    
    public OrderDto(Long userId, double totalValue, OrderStatus orderStatus) {
        this.id = null;
        this.userId = userId;
        this.totalValue = totalValue;
        this.orderStatus = orderStatus;
    }
}
