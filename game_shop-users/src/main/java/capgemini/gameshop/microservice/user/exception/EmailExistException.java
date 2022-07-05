package capgemini.gameshop.microservice.user.exception;

public class EmailExistException extends RuntimeException {

    public EmailExistException(String email) {
        super("Email: " + email + " already in use");
    }
}
