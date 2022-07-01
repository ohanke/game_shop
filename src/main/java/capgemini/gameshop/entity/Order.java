package capgemini.gameshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This entity is responsible for storing information about an Order.
 * Information like: id of User which owns the Order, date of Order creation,
 * total value of all the products in this Order, current Status of the Order
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders", indexes = {
        @Index(name = "order_tot_val_index", columnList = "totalValue"),
        @Index(name = "order_stat_index", columnList = "orderStatus")})
public class Order extends BaseEntity{

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(nullable = false, scale = 2)
    private double totalValue;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //TODO add Cascade type persis - and related add/remove methods for product
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

}
