package capgemini.gameshop.dto;

import capgemini.gameshop.entity.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jdk.jfr.Timestamp;
import lombok.*;

import java.time.LocalDateTime;
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

    private Long userId;

    private double totalValue;

    private OrderStatus orderStatus;

    //TODO display id of products
//    private Set<Long> productsId = new HashSet<>();

    public OrderDto(String date, Long userId, double totalValue, OrderStatus orderStatus) {
        this.id = null;
        this.userId = userId;
        this.totalValue = totalValue;
        this.orderStatus = orderStatus;
    }
}
