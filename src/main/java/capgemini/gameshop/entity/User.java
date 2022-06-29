package capgemini.gameshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This Entity is responsible for storing informations about a User.
 * Informations like: first and last name, email, password , Adress.
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", indexes = {
        @Index(name = "fn_index", columnList = "firstName"),
        @Index(name = "fn_ln_index", columnList = "firstName, lastName"),
        @Index(name = "email_index", columnList = "email")})

public class User extends BaseEntity{

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<Adress> adresses = new ArrayList<>();


    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

}
