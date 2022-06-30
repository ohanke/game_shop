package capgemini.gameshop.dto;

import capgemini.gameshop.entity.Attribute;
import capgemini.gameshop.entity.Category;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
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
    @NotEmpty
    @Size(min = 3, max = 20)
    private String name;
    @NotNull
    private Category category;
    @NotNull
    private Set<Attribute> attributes;
    @Positive
    private Double priceNett;
    @Positive
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
