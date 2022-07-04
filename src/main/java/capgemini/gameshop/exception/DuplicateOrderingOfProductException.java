package capgemini.gameshop.exception;

public class DuplicateOrderingOfProductException extends RuntimeException{
    public DuplicateOrderingOfProductException(Long productId) { super("Product with id: " + productId + " already selected.");
    }
}
