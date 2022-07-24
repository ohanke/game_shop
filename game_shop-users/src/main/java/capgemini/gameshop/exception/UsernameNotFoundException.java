package capgemini.gameshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UsernameNotFoundException extends RuntimeException{

    public UsernameNotFoundException(String username) {
        super("User with username " + username + " not found");
    }
}
