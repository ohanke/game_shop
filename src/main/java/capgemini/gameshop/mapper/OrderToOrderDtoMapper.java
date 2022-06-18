package capgemini.gameshop.mapper;

import capgemini.gameshop.dto.OrderDto;
import capgemini.gameshop.entity.Order;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  Maps entities of Order to DTO type
 *
 *  First method takes in one entity and returns one DTO
 *
 *  Second method takes in a List of entities and returns a List of DTO's
 */
public class OrderToOrderDtoMapper {
    public static OrderDto map(Order entity){
        return new OrderDto(
                entity.getId(),
                entity.getDate(),
                entity.getTotalValue(),
                entity.getOrderStatus()
        );
    }

    public static List<OrderDto> map(List<Order> entities){
        return entities
                .stream()
                .map(OrderToOrderDtoMapper::map)
                .collect(Collectors.toList());
    }
}
