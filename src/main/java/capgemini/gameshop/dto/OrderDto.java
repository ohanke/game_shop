package capgemini.gameshop.dto;

import capgemini.gameshop.entity.OrderStatus;
import capgemini.gameshop.entity.Product;
import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Mapping("id")
    private Long id;

    @Mapping("user_id")
    private Long user;

    @Mapping("total_value")
    private double totalValue;

    @Mapping("order_status")
    private OrderStatus orderStatus;

    @Mapping("products")
    private Set<Product> products = new HashSet<>();

    @Mapping("createdAt")
    private LocalDateTime createdAt;

    public OrderDto(String date, double totalValue, OrderStatus orderStatus, Set<Product> products) {
        this.id = null;
        this.totalValue = totalValue;
        this.orderStatus = orderStatus;
        this.products = products;
    }
}
