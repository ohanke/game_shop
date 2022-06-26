package capgemini.gameshop.dto;

import capgemini.gameshop.entity.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope= OrderDto.class)
public class OrderDto {

    private Long id;

    @JsonIgnore
    private UserDto user;

    private double totalValue;

    private OrderStatus orderStatus;

    private Set<ProductDto> products = new HashSet<>();

    private LocalDateTime createdAt;

    public OrderDto(String date, double totalValue, OrderStatus orderStatus, Set<ProductDto> products) {
        this.id = null;
        this.totalValue = totalValue;
        this.orderStatus = orderStatus;
        this.products = products;
    }
}
