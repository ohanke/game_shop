package capgemini.gameshop.dto;

import capgemini.gameshop.entity.OrderStatus;
import capgemini.gameshop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    private double totalValue;
    private OrderStatus orderStatus;
    private Set<Product> products = new HashSet<>();
    private LocalDateTime createdAt;




    public OrderDto(String date, double totalValue, OrderStatus orderStatus, Set<Product> products) {
        this.id = null;
        this.totalValue = totalValue;
        this.orderStatus = orderStatus;
        this.products = products;
    }
}
