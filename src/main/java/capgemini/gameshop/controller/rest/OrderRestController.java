package capgemini.gameshop.controller.rest;

import capgemini.gameshop.dto.OrderDto;
import capgemini.gameshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public List<OrderDto> getOrders(){
        return orderService.findAll();
    }
}
