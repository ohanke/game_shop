package capgemini.gameshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * This entity is responsible for storing informations about a Order.
 * Informations like: id of User which owns the Order, date of Order creation,
 * total value of all the products in this Order, current Status of the Order
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO change to User user, relation user - order
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "order_date")
    private String date; //change later for LocalDate or smth like that

    @Column(name = "total_value")
    private double totalValue;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}
