package capgemini.gameshop.exception;

public class EmailExistException extends RuntimeException {


    public EmailExistException(String email) {
        super("Email: " + email + " already in use");
    }
}
