package capgemini.gameshop.controller;

import capgemini.gameshop.orders.dto.OrderDto;
import capgemini.gameshop.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;
    private final CircuitBreakerFactory factory;

    @GetMapping
    public List<OrderDto> getOrders(){
        return factory.create("orderService").run(orderService::findAll);
    }

    @GetMapping("{id}")
    public OrderDto getOrder(@PathVariable Long id) {
        return factory.create("orderService").run(() -> orderService.findById(id));
    }

    @PostMapping
    public OrderDto create(@Valid @RequestBody OrderDto orderDto) {
        return factory.create("orderService").run(() -> orderService.create(orderDto));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto) {
        orderService.update(id, orderDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        orderService.delete(id);
    }

    @GetMapping("{orderId}/add/{productId}")
    public OrderDto addProduct(@PathVariable Long orderId, @PathVariable Long productId){
        return factory.create("orderService").run(() -> orderService.addProduct(orderId, productId));
    }
}
