package capgemini.gameshop.exception;

public class AdressNotFoundException extends RuntimeException{

    public AdressNotFoundException(String zip) {
        super("Adress with zip: " + zip + " not found");}

    public AdressNotFoundException(Long id) {
            super("Adress with id: " + id + " not found");}

}
