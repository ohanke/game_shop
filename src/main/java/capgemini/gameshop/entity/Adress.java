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

    @Column(name =" country")
    private String country;

    @Column(name =" street", nullable = false)
    private String street;

    @Column(name =" state")
    private String state;

    @Column(name =" city", nullable = false)
    private String city;

    @Column(name =" zip_code", nullable = false)
    private String zip;

}
