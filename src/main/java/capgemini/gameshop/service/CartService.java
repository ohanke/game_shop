package capgemini.gameshop.service;

import capgemini.gameshop.entity.Cart;
import capgemini.gameshop.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }
}
