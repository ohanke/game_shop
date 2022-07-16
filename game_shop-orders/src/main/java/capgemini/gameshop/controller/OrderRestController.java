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

    private final String CIRCUIT_SERVICE = "orderService";
    private final OrderService orderService;
    private final CircuitBreakerFactory factory;


    /**
     * @return - list of all Orders converted to DTO
     */
    @GetMapping
    public List<OrderDto> getOrders(){
        return factory.create(CIRCUIT_SERVICE).run(orderService::findAll);
    }

    /**
     * @param id - Long type id of Order object
     * @return - OrderDTO with requested id
     */
    @GetMapping("{id}")
    public OrderDto getOrder(@PathVariable Long id) {
        return factory.create(CIRCUIT_SERVICE).run(() -> orderService.findById(id));
    }


    /**
     * @param orderDto - body for new Order object
     * @return - created OrderDTO
     */
    @PostMapping
    public OrderDto create(@Valid @RequestBody OrderDto orderDto) {
        return factory.create(CIRCUIT_SERVICE).run(() -> orderService.create(orderDto));
    }

    /**
     * @param id - Long type id of Order to be updated
     * @param orderDto - Body for OrderDTO update
     */
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto) {
        orderService.update(id, orderDto);
    }

    /**
     * @param id - Long type id of OrderDTO to be deleted
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        orderService.delete(id);
    }

    /**
     * @param orderId - Long type id of Order to be modified
     * @param productId - Long type id of Product be added into Order
     * @return - OrderDTO updated with specified Product
     */
    @GetMapping("{orderId}/add/{productId}")
    public OrderDto addProduct(@PathVariable Long orderId, @PathVariable Long productId){
        return factory.create(CIRCUIT_SERVICE).run(() -> orderService.addProduct(orderId, productId));
    }
}
