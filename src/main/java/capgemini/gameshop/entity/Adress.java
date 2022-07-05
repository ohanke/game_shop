package capgemini.gameshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Entity that stores information about users adress information, and to add it to database.
 *
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "adress", indexes = {
        @Index(name = "adress_cnt_ct_index", columnList = "country, city"),
        @Index(name = "adress_zip_index", columnList = "zip")})
public class Adress extends BaseEntity{

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    private String country;

    @Column(nullable = false)
    private String street;

    private String state;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String zip;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "adress_user",
            joinColumns = @JoinColumn(name = "adress_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();


}
