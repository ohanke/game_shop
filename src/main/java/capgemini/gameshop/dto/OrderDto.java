package capgemini.gameshop.dto;

import capgemini.gameshop.entity.OrderStatus;
import capgemini.gameshop.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Setter
public class OrderDto {

    private Long id;

    private UserDto user;

    private double totalValue;

    private OrderStatus orderStatus;

    @JsonIgnore
    private Set<Product> products = new HashSet<>();

    private LocalDateTime createdAt;

    public OrderDto(String date, double totalValue, OrderStatus orderStatus, Set<Product> products) {
        this.id = null;
        this.totalValue = totalValue;
        this.orderStatus = orderStatus;
        this.products = products;
    }
}
