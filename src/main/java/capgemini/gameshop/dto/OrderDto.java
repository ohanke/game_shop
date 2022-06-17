package capgemini.gameshop.dto;

import capgemini.gameshop.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * DTO class for entities of Order
 *
 * Getters, full and empty constructor provided by lombok, also one custom constructor
 * for objects with no id.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDto {
    private Long id;
    private Long userId;
    private String date; //change later for LocalDate or smth like that
    private double totalValue;
    private OrderStatus orderStatus;

    public OrderDto(Long userId, String date, double totalValue, OrderStatus orderStatus) {
        this.id = null;
        this.userId = userId;
        this.date = date;
        this.totalValue = totalValue;
        this.orderStatus = orderStatus;
    }
}
