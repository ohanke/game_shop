package capgemini.gameshop.exception;

public class DuplicateOrderingOfProductException extends RuntimeException{
    public DuplicateOrderingOfProductException(Long orderId ,Long productId) { super(
            "Order with id: " + orderId + " already contains Product with id: " + productId + "!");
    }
}
