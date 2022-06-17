package capgemini.gameshop.mapper;

import capgemini.gameshop.dto.CartDto;
import capgemini.gameshop.entity.Cart;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  Maps entities of Cart to DTO type
 *
 *  First method takes in one entity and returns one DTO
 *
 *  Second method takes in a List of entities and returns a List of DTO's
 */
public class CartToCartDtoMapper {
    public static CartDto map(Cart entity){
        return new CartDto(
                entity.getId(),
                entity.getUser(),
                entity.getListProduct()
        );
    }

    public static List<CartDto> map(List<Cart> entities){
        return entities
                .stream()
                .map(CartToCartDtoMapper::map)
                .collect(Collectors.toList());
    }
}
