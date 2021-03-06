package capgemini.gameshop.orders.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
@Setter
@Getter
public class OrderDto {

    private Long id;
    @NotNull
    private Long userId;
    private double totalValue;
    private String orderStatus;

    private Set<ProductDto> products;

    private LocalDateTime createdAt;
    
    public OrderDto(Long userId, double totalValue, String orderStatus) {
        this.id = null;
        this.userId = userId;
        this.totalValue = totalValue;
        this.orderStatus = orderStatus;
    }
}
