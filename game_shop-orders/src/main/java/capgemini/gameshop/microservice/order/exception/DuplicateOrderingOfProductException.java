package capgemini.gameshop.microservice.order.exception;

public class DuplicateOrderingOfProductException extends RuntimeException{
    public DuplicateOrderingOfProductException(Long orderId ,Long productId) { super(
            "Order with id: " + orderId + " already contains Product with id: " + productId + "!");
    }
}
