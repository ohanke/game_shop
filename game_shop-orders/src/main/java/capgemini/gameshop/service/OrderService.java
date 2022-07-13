package capgemini.gameshop.service;

import capgemini.gameshop.orders.dto.OrderDto;
import capgemini.gameshop.exception.DuplicateOrderingOfProductException;
import capgemini.gameshop.exception.OrderNotFoundException;
import capgemini.gameshop.exception.ProductNotFoundException;
import capgemini.gameshop.model.Order;
import capgemini.gameshop.model.OrderStatus;
import capgemini.gameshop.model.Product;
import capgemini.gameshop.orders.event.IntegrationOrderEvent;
import capgemini.gameshop.orders.event.OrderCreatedEvent;
import capgemini.gameshop.orders.event.OrderDeletedEvent;
import capgemini.gameshop.orders.event.OrderExtendedEvent;
import capgemini.gameshop.repository.OrderRepository;
import capgemini.gameshop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private final KafkaTemplate<Long, IntegrationOrderEvent> kafkaTemplate;

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
        kafkaTemplate.send("orders-create", savedOrder.getId(),
                new OrderCreatedEvent(savedOrder.getId(), savedOrder.getUserId()));
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
        if (order.getProducts().contains(product)){
            throw new DuplicateOrderingOfProductException(orderId ,productId);
        } else {
            order.getProducts().add(product);
            order.setOrderStatus(OrderStatus.PROCESSING);
            order.setTotalValue(order.getTotalValueOfProducts());
            kafkaTemplate.send("orders-extend", order.getId(),
                    new OrderExtendedEvent(orderId, order.getUserId(), productId));
            return convertToDTO(orderRepository.save(order));
        }
    }
    public void delete(Long id) {
        Order existingOrder = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        existingOrder.getProducts().forEach(product -> product.getOrders().remove(existingOrder));
        productRepository.saveAll(existingOrder.getProducts());
        kafkaTemplate.send("orders-delete", existingOrder.getId(),
                new OrderDeletedEvent(existingOrder.getUserId(), existingOrder.getId()));
        orderRepository.deleteById(id);
    }

    private OrderDto updateFields(OrderDto orderDto, Order order) {
        order.setUserId(orderDto.getUserId());
        order.setTotalValue(orderDto.getTotalValue());
        order.setOrderStatus(OrderStatus.valueOf(orderDto.getOrderStatus()));
        return convertToDTO(orderRepository.save(order));
    }
}