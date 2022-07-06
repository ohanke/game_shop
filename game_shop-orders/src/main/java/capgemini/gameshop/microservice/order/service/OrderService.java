package capgemini.gameshop.microservice.order.service;

import capgemini.gameshop.microservice.order.dto.OrderDto;
import capgemini.gameshop.microservice.order.exception.DuplicateOrderingOfProductException;
import capgemini.gameshop.microservice.order.exception.OrderNotFoundException;
import capgemini.gameshop.microservice.order.exception.ProductNotFoundException;
import capgemini.gameshop.microservice.order.model.Order;
import capgemini.gameshop.microservice.order.model.OrderStatus;
import capgemini.gameshop.microservice.order.model.Product;
import capgemini.gameshop.microservice.order.repository.OrderRepository;
import capgemini.gameshop.microservice.order.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
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
        orderDto.setOrderStatus("NEW");
        Order savedOrder = orderRepository.save(convertToEntity(orderDto));
        return convertToDTO(savedOrder);
    }

    public void update(Long orderId, OrderDto orderDto) {
        orderRepository.findById(orderId)
                .map(order -> updateFields(orderDto, order))
                .orElseThrow(() -> new OrderNotFoundException(orderId));
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
        Order existingOrder = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        existingOrder.getProducts().forEach(product -> product.getOrders().remove(existingOrder));
        productRepository.saveAll(existingOrder.getProducts());
        orderRepository.deleteById(id);
    }

    private OrderDto updateFields(OrderDto orderDto, Order order) {
        order.setUserId(orderDto.getUserId());
        order.setTotalValue(orderDto.getTotalValue());
        order.setOrderStatus(OrderStatus.valueOf(orderDto.getOrderStatus()));
        return convertToDTO(orderRepository.save(order));
    }
}
