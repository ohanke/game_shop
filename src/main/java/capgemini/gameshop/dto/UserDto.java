package capgemini.gameshop.dto;

import capgemini.gameshop.entity.Adress;
import capgemini.gameshop.entity.Order;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * DTO class for entities of User
 *
 * Getters, full and empty constructor provided by lombok, also one custom constructor
 * for objects with no id.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private List<Adress> adresses;

    private List<Order> orders;

    public UserDto(String firstName, String lastName, String email, String password) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
