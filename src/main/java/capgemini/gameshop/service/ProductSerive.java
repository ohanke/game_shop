package capgemini.gameshop.service;

import capgemini.gameshop.dto.ProductDto;
import capgemini.gameshop.entity.Product;
import capgemini.gameshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  Service for ProductRepository.
 *
 *  Calls UserRepository to get required Entities of Product.
 *  Calls the ProductToProductDtoMapper to map the Entities into DTO type.
 *  Returns DTO's of Product
 */
@Service
@RequiredArgsConstructor
public class ProductSerive {
    private final ProductRepository productRepository;

    public List<ProductDto> getAllProducts(){
        return ProductToProductDtoMapper.map(productRepository.findAll());
    }
}