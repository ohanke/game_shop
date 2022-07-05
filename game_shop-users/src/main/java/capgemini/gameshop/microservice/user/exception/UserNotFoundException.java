package capgemini.gameshop.microservice.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String email) {
        super("User with email: " + email + " not found");
    }

    public UserNotFoundException(Long id) {
        super("User with id: " + id + " not found");
    }
}
