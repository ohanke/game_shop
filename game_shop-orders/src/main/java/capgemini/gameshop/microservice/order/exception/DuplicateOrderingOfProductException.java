package capgemini.gameshop.microservice.order.exception;

public class DuplicateOrderingOfProductException extends RuntimeException{
    public DuplicateOrderingOfProductException(Long productId) { super("Product with id: " + productId + " already selected.");
    }
}
