package capgemini.gameshop.model;

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
@Table(name = "adress", indexes = {
        @Index(name = "adress_cnt_ct_index", columnList = "country, city"),
        @Index(name = "adress_zip_index", columnList = "zip")})
public class Adress{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;
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
