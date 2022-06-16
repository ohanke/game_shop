package capgemini.gameshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity that defines Attributes a Product may have.
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Attribute {// refactor to ENUM maybe?

    private Long id;
    private String name;

}
