package capgemini.gameshop.dto;

import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * DTO class for entities of Adress
 *
 * Getters, full and empty constructor provided by lombok, also one custom constructor
 * for objects with no id.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AdressDto {
    @Mapping("id")
    private Long id;

    @Mapping("user")
    private UserDto user;
    @Mapping("country")
    private String country;

    @Mapping("street")
    private String street;

    @Mapping("state")
    private String state;

    @Mapping("city")
    private String city;

    @Mapping("zip_code")
    private String zip;

    public AdressDto(String country, String address, String state, String city, String zip) {
        this.id = null;
        this.country = country;
        this.street = address;
        this.state = state;
        this.city = city;
        this.zip = zip;
    }
}
