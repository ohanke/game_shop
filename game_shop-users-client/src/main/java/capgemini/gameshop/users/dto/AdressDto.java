package capgemini.gameshop.users.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


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
@ToString
public class AdressDto {

    private Long id;

    @NotNull
    private Long userId;

    @NotEmpty
    @Size(max = 100, min = 3)
    private String country;

    @NotEmpty
    @Size(min = 3)
    private String street;

    private String state;

    @NotEmpty
    private String city;

    @Size(max = 10, min = 4)
    private String zip;

    private LocalDateTime createdAt;

    public AdressDto(String country, String address, String state, String city, String zip) {
        this.id = null;
        this.country = country;
        this.street = address;
        this.state = state;
        this.city = city;
        this.zip = zip;
    }
}
