package capgemini.gameshop.users.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO class for entities of User
 *
 * Getters, full and empty constructor provided by lombok, also one custom constructor
 * for objects with no id.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class UserDto {

    private Long id;

    @NotEmpty
    @Size(min = 2)
    private String username;

    @NotEmpty
    @Size(min = 5)
    private String email;

    @NotEmpty
    @Size(min = 8, max = 32)
    private String password;

    private boolean active;
    @NotNull
    private String role;

//    private LocalDateTime createdAt;


    public UserDto(String username, String email, String password, String role) {
        this.id = null;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
