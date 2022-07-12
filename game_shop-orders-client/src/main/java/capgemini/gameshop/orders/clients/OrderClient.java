package capgemini.gameshop.orders.clients;

import capgemini.gameshop.orders.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "orders", path = "/api/orders")
public interface OrderClient {

    @GetMapping
    List<OrderDto> getOrders();

    @GetMapping("/{id}")
    OrderDto getOrder(@PathVariable Long id);

    @PostMapping
    OrderDto create(@Valid @RequestBody OrderDto orderDto);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto);


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id);

    @GetMapping("/{orderId}/add/{productId}")
    OrderDto addProduct(@PathVariable Long orderId, @PathVariable Long productId);
}
