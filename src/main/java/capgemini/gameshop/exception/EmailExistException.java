package capgemini.gameshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class EmailExistException extends RuntimeException {


    public EmailExistException(String email) {
        super("Email: " + email + " already in use");
    }
}
