package capgemini.gameshop.dto;

import capgemini.gameshop.entity.Attribute;
import capgemini.gameshop.entity.Category;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * DTO class for entities of Product
 *
 * Getters, full and empty constructor provided by lombok, also one custom constructor
 * for objects with no id.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private Category category;
    private Set<Attribute> attributes;
    private Double priceNett;
    private Double priceGross;

    public ProductDto(String name, Category category, Set<Attribute> attributes, Double priceNett, Double priceGross) {
        this.id = null;
        this.name = name;
        this.category = category;
        this.attributes = attributes;
        this.priceNett = priceNett;
        this.priceGross = priceGross;
    }
}
