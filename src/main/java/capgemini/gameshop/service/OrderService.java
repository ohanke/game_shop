package capgemini.gameshop.service;

import capgemini.gameshop.dto.OrderDto;
import capgemini.gameshop.entity.Order;
import capgemini.gameshop.mapper.OrderToOrderDtoMapper;
import capgemini.gameshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  Service for OrderRepository.
 *
 *  Calls UserRepository to get required Entities of Order.
 *  Calls the OrderToOrderDtoMapper to map the Entities into DTO type.
 *  Returns DTO's of Order
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderDto> getAllOrders(){
        return OrderToOrderDtoMapper.map(orderRepository.findAll());
    }
}
