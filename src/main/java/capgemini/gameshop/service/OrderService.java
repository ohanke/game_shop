package capgemini.gameshop.service;

import capgemini.gameshop.dto.OrderDto;
import capgemini.gameshop.entity.Order;
import capgemini.gameshop.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  Service for OrderRepository.
 *
 *  Calls UserRepository to get required Entities of Order.
 *  Calls the OrderToOrderDtoMapper to map the Entities into DTO type.
 *  Returns DTO's of Order
 */
@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private ModelMapper mapper;

    public OrderDto convertToOrderDTO (Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto = mapper.map(order, OrderDto.class);
        return orderDto;
    }
    public List<OrderDto> findAll(){
        return orderRepository.findAll().stream().map(this::convertToOrderDTO).collect(Collectors.toList());
    }
}
