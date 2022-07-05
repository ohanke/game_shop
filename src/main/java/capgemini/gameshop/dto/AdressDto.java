package capgemini.gameshop.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope= AdressDto.class)
public class AdressDto {

    private Long id;
    @JsonIgnore
    private UserDto user;

    @NotEmpty
    @Size(max = 100, min = 3)
    private String country;


    private String street;

    private String state;

    @NotEmpty
    private String city;


    @Size(max = 10, min = 4)
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
