package capgemini.gameshop.service;

import capgemini.gameshop.dto.CartDto;
import capgemini.gameshop.entity.Cart;
import capgemini.gameshop.mapper.CartToCartDtoMapper;
import capgemini.gameshop.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  Service for CartRepository.
 *
 *  Calls UserRepository to get required Entities of Cart.
 *  Calls the CartToCartDtoMapper to map the Entities into DTO type.
 *  Returns DTO's of Cart
 */
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public List<CartDto> getAllCarts(){
        return CartToCartDtoMapper.map(cartRepository.findAll());
    }
}
