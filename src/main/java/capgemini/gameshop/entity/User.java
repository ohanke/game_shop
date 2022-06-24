package capgemini.gameshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
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
@Table(name = "users")

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
            cascade = CascadeType.PERSIST)
    private List<Adress> adresses;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.PERSIST)
    private List<Order> orders;

}
