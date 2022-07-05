package capgemini.gameshop.microservice.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
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
        @Index(name = "users_fn_ln_index", columnList = "firstName, lastName"),
        @Index(name = "users_email_index", columnList = "email")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

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

//
//    @OneToMany(mappedBy = "user",
//            cascade = CascadeType.ALL)
//    private List<Order> orders = new ArrayList<>();

}
