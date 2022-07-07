package capgemini.gameshop.event;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductCreatedEvent {
    private Long productId;
    private LocalDateTime createdAt;
}
