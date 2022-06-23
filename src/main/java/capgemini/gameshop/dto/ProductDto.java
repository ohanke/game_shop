package capgemini.gameshop.dto;

import capgemini.gameshop.entity.Attribute;
import capgemini.gameshop.entity.Category;
import capgemini.gameshop.entity.Order;
import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO class for entities of Product
 *
 * Getters, full and empty constructor provided by lombok, also one custom constructor
 * for objects with no id.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductDto {
    @Mapping("id")
    private Long id;

    @Mapping("name")
    private String name;

    @Mapping("category")
    private Category category;

    @Mapping("attributes")
    private Set<Attribute> attributes;

    @Mapping("price_nett")
    private Double priceNett;

    @Mapping("price_gross")
    private Double priceGross;

    @Mapping("orders")
    private Set<Order> orders  = new HashSet<>();

    public ProductDto(String name, Category category, Set<Attribute> attributes, Double priceNett, Double priceGross) {
        this.id = null;
        this.name = name;
        this.category = category;
        this.attributes = attributes;
        this.priceNett = priceNett;
        this.priceGross = priceGross;
    }
}
