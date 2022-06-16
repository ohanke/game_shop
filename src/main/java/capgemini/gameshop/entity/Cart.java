package capgemini.gameshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * This class is responsible for storing Products added by User
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    private long id; //delete maybe?
    private User user; //delete maybe?
    private List<Product> listProduct;

}
