package capgemini.gameshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Long id;
    private String country;
    private String street;
    private String state;
    private String city;
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
