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
import capgemini.gameshop.orders.event.OrderAddProductEvent;
import capgemini.gameshop.repository.OrderRepository;
import capgemini.gameshop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
@Transactional
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final KafkaTemplate<Long, IntegrationOrderEvent> kafkaTemplate;

    private final ModelMapper mapper;

    /**
     * @param entity - Order object to be mapped
     * @return - OrderDTO
     */
    private OrderDto convertToDTO(Order entity) {
        return mapper.map(entity, OrderDto.class);
    }

    /**
     * @param dto - OrderDTO object to be mapped
     * @return - Order
     */
    private Order convertToEntity(OrderDto dto){
        return mapper.map(dto, Order.class);
    }

    /**
     * @return - List of all Orders converted to DTO
     */
    public List<OrderDto> findAll(){
        return orderRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param id - Long type id of requested Order
     * @return - OrderDTO with requested id
     */
    public OrderDto findById(Long id) {
        return orderRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    /**
     * @param orderDto - body for new Order object
     * @return - created OrderDTO
     */
    public OrderDto create(OrderDto orderDto) {
        orderDto.setTotalValue(0);
        orderDto.setOrderStatus("NEW");
        Order order = mapper.map(orderDto, Order.class);
        OrderDto savedOrder = convertToDTO(orderRepository.save(order));
        kafkaTemplate.send("orders-create", savedOrder.getId(),
                new OrderCreatedEvent(savedOrder.getId(), savedOrder.getUserId()));
        return savedOrder;
    }


    /**
     * @param orderId - Long type id of Order to be updated
     * @param orderDto - Body for OrderDTO update
     */
    public void update(Long orderId, OrderDto orderDto) {
        orderRepository.findById(orderId)
                .map(order -> updateFields(orderDto, order))
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    /**
     * @param orderId - Long type id of Order to be modified
     * @param productId - Long type id of Product be added into Order
     * @return - OrderDTO updated with specified Product
     */
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
                    new OrderAddProductEvent(orderId, order.getUserId(), productId));
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
