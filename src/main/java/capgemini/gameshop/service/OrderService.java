package capgemini.gameshop.service;

import capgemini.gameshop.dto.OrderDto;
import capgemini.gameshop.dto.ProductDto;
import capgemini.gameshop.entity.Order;
import capgemini.gameshop.entity.Product;
import capgemini.gameshop.exception.OrderNotFoundException;
import capgemini.gameshop.exception.UserNotFoundException;
import capgemini.gameshop.repository.OrderRepository;
import capgemini.gameshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
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

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final ModelMapper mapper;

    private OrderDto convertToDTO(Order entity) {
        return mapper.map(entity, OrderDto.class);
    }

    private Order convertToEntity(OrderDto dto){
        return mapper.map(dto, Order.class);
    }
    public List<OrderDto> findAll(){
        return orderRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OrderDto findById(Long id) {
        return orderRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new OrderNotFoundException("Order with id: " + id + " not found"));
    }

    public OrderDto save(OrderDto orderDto) {
        Order savedOrder = orderRepository.save(convertToEntity(orderDto));
        return convertToDTO(savedOrder);
    }

    public void update(Long id, OrderDto orderDto) {
        orderRepository.findById(id)
                .map(order -> updateFields(orderDto, order))
                .orElseThrow(() -> new OrderNotFoundException("Order with id: " + id + " not found"));
    }


    private OrderDto updateFields(OrderDto orderDto, Order order) {
        order.setUser(userRepository.findById(orderDto.getUserId()).orElseThrow(
                () -> new UserNotFoundException("User with id: " + orderDto.getUserId() + " not found")));
        order.setTotalValue(orderDto.getTotalValue());
        order.setOrderStatus(orderDto.getOrderStatus());
        return convertToDTO(orderRepository.save(order));
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
