package capgemini.gameshop.service;

import capgemini.gameshop.dto.ProductDto;
import capgemini.gameshop.dto.UserDto;
import capgemini.gameshop.entity.Product;
import capgemini.gameshop.repository.ProductRepository;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProductSerive {
    private final ProductRepository productRepository;

    @Autowired
    private Mapper mapper;

    public List<ProductDto> getAllProducts(){
        return productRepository.findAll().stream().map(product -> mapper.map(product, ProductDto.class)).collect((Collectors.toList()));
    }
}