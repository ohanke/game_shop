package capgemini.gameshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity that stores information about users adress information, and to add it to database.
 *
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "adress")
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

}
