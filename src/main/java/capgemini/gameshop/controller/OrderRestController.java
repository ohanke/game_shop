package capgemini.gameshop.controller;

import capgemini.gameshop.dto.OrderDto;
import capgemini.gameshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping("/all")
    public List<OrderDto> getOrders(){
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @PostMapping("/create")
    public OrderDto saveOrder(@Valid @RequestBody OrderDto orderDto) {
        return orderService.save(orderDto);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto) {
        orderService.update(id, orderDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        orderService.delete(id);
    }
}
