package capgemini.gameshop.service;

import capgemini.gameshop.dto.OrderDto;
import capgemini.gameshop.entity.Order;
import capgemini.gameshop.entity.OrderStatus;
import capgemini.gameshop.entity.Product;
import capgemini.gameshop.exception.OrderNotFoundException;
import capgemini.gameshop.exception.DuplicateOrderingOfProductException;
import capgemini.gameshop.exception.ProductNotFoundException;
import capgemini.gameshop.exception.UserNotFoundException;
import capgemini.gameshop.repository.OrderRepository;
import capgemini.gameshop.repository.ProductRepository;
import capgemini.gameshop.repository.UserRepository;
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

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

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
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public OrderDto create(OrderDto orderDto) {
        orderDto.setTotalValue(0);
        orderDto.setOrderStatus(OrderStatus.NEW);
        Order savedOrder = orderRepository.save(convertToEntity(orderDto));
        return convertToDTO(savedOrder);
    }

    public void update(Long id, OrderDto orderDto) {
        orderRepository.findById(id)
                .map(order -> updateFields(orderDto, order))
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public OrderDto addProduct(Long orderId, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        if (order.getProductsIdOfOrder().contains(productId)){
            throw new DuplicateOrderingOfProductException(productId);
        } else {
            order.getProducts().add(product);
            order.setOrderStatus(OrderStatus.PROCESSING);
            order.setTotalValue(order.getTotalValueOfProducts());
            return convertToDTO(orderRepository.save(order));
        }
    }
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDto updateFields(OrderDto orderDto, Order order) {
        order.setUser(userRepository.findById(orderDto.getUserId()).orElseThrow(
                () -> new UserNotFoundException("User with id: " + orderDto.getUserId() + " not found")));
        order.setTotalValue(orderDto.getTotalValue());
        order.setOrderStatus(orderDto.getOrderStatus());
        return convertToDTO(orderRepository.save(order));
    }
}
