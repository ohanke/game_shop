package capgemini.gameshop.service;

import capgemini.gameshop.dto.ProductDto;
import capgemini.gameshop.entity.Product;
import capgemini.gameshop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    private ModelMapper mapper;

    public ProductDto convertToProductDTO (Product product) {
        ProductDto productDto = new ProductDto();
        productDto = mapper.map(product, ProductDto.class);
        return productDto;
    }

    public List<ProductDto> findAll(){
        return productRepository.findAll().stream().map(this::convertToProductDTO).collect((Collectors.toList()));
    }
}