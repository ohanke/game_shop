package capgemini.gameshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Setter
public class AdressDto {
    private Long id;

    private UserDto user;

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
