package capgemini.gameshop.service;

import capgemini.gameshop.dto.ProductDto;
import capgemini.gameshop.repository.ProductRepository;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  Service for ProductRepository.
 *
 *  Calls UserRepository to get required Entities of Product.
 *  Calls the ProductToProductDtoMapper to map the Entities into DTO type.
 *  Returns DTO's of Product
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private final Mapper mapper;

    public List<ProductDto> findAll(){
        return productRepository.findAll().stream().map(product -> mapper.map(product, ProductDto.class)).collect((Collectors.toList()));
    }
}