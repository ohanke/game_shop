package capgemini.gameshop.dto;

import capgemini.gameshop.entity.Attribute;
import capgemini.gameshop.entity.Category;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope= ProductDto.class)
public class ProductDto {

    private Long id;

    private String name;

    private Category category;

    private Set<Attribute> attributes;

    private Double priceNett;

    private Double priceGross;

    @JsonIgnore
    private Set<OrderDto> orders  = new HashSet<>();

    public ProductDto(String name, Category category, Set<Attribute> attributes, Double priceNett, Double priceGross) {
        this.id = null;
        this.name = name;
        this.category = category;
        this.attributes = attributes;
        this.priceNett = priceNett;
        this.priceGross = priceGross;
    }
}
