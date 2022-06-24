package capgemini.gameshop.dto;

import capgemini.gameshop.entity.Adress;
import capgemini.gameshop.entity.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.dozermapper.core.Mapping;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
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
    @Mapping("id")
    private Long id;
    @Mapping("firstName")
    private String firstName;
    @Mapping("lastName")
    private String lastName;
    @Mapping("email")
    private String email;
    @Mapping("password")
    private String password;

    @Mapping("adresses")
    private List<AdressDto> adresses = new ArrayList<>();

    @Mapping("orders")
    private List<OrderDto> orders = new ArrayList<>();

    public UserDto(String firstName, String lastName, String email, String password) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
