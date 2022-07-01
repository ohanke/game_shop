package capgemini.gameshop.service;

import capgemini.gameshop.dto.ProductDto;
import capgemini.gameshop.entity.Product;
import capgemini.gameshop.exception.ProductNotFoundException;
import capgemini.gameshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper mapper;

    /**
     * Converts Product into ProductDto
     * @param entity
     * @return DTO
     */
    private ProductDto convertToDTO(Product entity) {
        return mapper.map(entity, ProductDto.class);
    }

    /**
     * Converts ProductDto into Product
     * @param dto
     * @return Product
     */
    private Product converToEntity(ProductDto dto) {
        return mapper.map(dto, Product.class);
    }


    public List<ProductDto> findAll(){
        return productRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect((Collectors.toList()));
    }

    public ProductDto findById(Long id) {
        return productRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " not found"));
    }


    public ProductDto save(ProductDto productDto) {
        Product productToSave = converToEntity(productDto);
        Product savedProduct = productRepository.save(productToSave);
        return convertToDTO(savedProduct);
    }

    public void update(Long id, ProductDto productDto) {
        productRepository.findById(id)
                .map(product -> updateFields(productDto, product))
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " not found"));
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDto updateFields(ProductDto productDto, Product product) {
        if (productDto.getName() != null)
            product.setName(productDto.getName());
        if (productDto.getCategory() != null)
            product.setCategory(productDto.getCategory());
        if (productDto.getAttributes() != null)
            product.setAttributes(productDto.getAttributes());
        if (productDto.getPriceNett() != null)
            product.setPriceNett(productDto.getPriceNett());
        if (productDto.getPriceGross() != null)
            product.setPriceGross(productDto.getPriceGross());
        return convertToDTO(productRepository.save(product));
    }
}