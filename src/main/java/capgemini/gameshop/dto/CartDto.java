package capgemini.gameshop.dto;

import capgemini.gameshop.entity.Product;
import capgemini.gameshop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO class for entities of Cart
 *
 * Getters, full and empty constructor provided by lombok, also one custom constructor
 * for objects with no id.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartDto {
    private Long id;
    private User user; //change to Order maybe?
    private List<Product> listProduct;

    public CartDto(User user, List<Product> listProduct) {
        this.id = null;
        this.user = user;
        this.listProduct = listProduct;
    }
}
