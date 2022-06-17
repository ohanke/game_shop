package capgemini.gameshop.dto;

import capgemini.gameshop.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Long id;
    private String name;
    private Category category;
    private String description;
//    private List<Attribute> attributes;
    private Double priceNett;
    private Double priceGross;

    public ProductDto(String name, Category category, String description, Double priceNett, Double priceGross) {
        this.id = null;
        this.name = name;
        this.category = category;
        this.description = description;
        this.priceNett = priceNett;
        this.priceGross = priceGross;
    }
}
