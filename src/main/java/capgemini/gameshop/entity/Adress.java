package capgemini.gameshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

/***
 * Entity that stores information about users adress information, and to added it to database.
 */
public class Adress {

    private long idd;
    private String country;
    private String address;
    private String state;
    private String city;
    private String zip;

}
