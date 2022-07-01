package capgemini.gameshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This Entity is responsible for storing information about the Product.
 * Information like: Products name, its Category, description and price
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product", indexes = {
        @Index(name = "product_name_index", columnList = "name"),
        @Index(name = "product_pr_index", columnList = "price")})
public class Product extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    //TODO add persis related add/remove methods for orders
    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private Set<Order> orders = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @ElementCollection(targetClass = Attribute.class)
    @CollectionTable(name = "product_attribute",
            joinColumns = @JoinColumn(name = "product_id"))
    @Enumerated(EnumType.STRING)
    private Set<Attribute> attributes = new HashSet<>();

    @Column(nullable = false, scale = 2)
    private Double price;
}
