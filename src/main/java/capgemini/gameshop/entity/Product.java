package capgemini.gameshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
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
@Table(name = "product")
public class Product extends BaseEntity{

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "products")
    private Set<Order> orders  = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @ElementCollection(targetClass = Attribute.class)
    @CollectionTable(name = "product_attribute",
            joinColumns = @JoinColumn(name = "product_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "attributes")
    private Set<Attribute> attributes;

    @Column(name = "price_nett", nullable = false, scale = 2)
    private Double priceNett;

    @Column(name = "price_gross", nullable = false, scale = 2)
    private Double priceGross;


}
