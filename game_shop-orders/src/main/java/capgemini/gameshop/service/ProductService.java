package capgemini.gameshop.service;

import capgemini.gameshop.orders.dto.ProductDto;
import capgemini.gameshop.exception.ProductNotFoundException;
import capgemini.gameshop.model.Attribute;
import capgemini.gameshop.model.Category;
import capgemini.gameshop.model.Product;
import capgemini.gameshop.repository.OrderRepository;
import capgemini.gameshop.repository.ProductRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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
@Setter
@Getter
public class ProductService {

//    private final KafkaTemplate<Long, ProductCreatedEvent> kafkaTemplate;

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    private final ModelMapper mapper;

    /**
     * Converts Product into ProductDto
     *
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
                .orElseThrow(() -> new ProductNotFoundException(id));
    }


    public ProductDto save(ProductDto productDto) {
        Product productToSave = converToEntity(productDto);
        return convertToDTO(productRepository.save(productToSave));
    }

    public void update(Long id, ProductDto productDto) {
        productRepository.findById(id)
                .map(product -> updateFields(productDto, product))
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Transactional
    public void delete(Long id) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        existingProduct.getOrders().forEach(order -> order.getProducts().remove(existingProduct));
        orderRepository.saveAll(existingProduct.getOrders());
        productRepository.deleteById(id);
    }

    private ProductDto updateFields(ProductDto productDto, Product product) {
        product.setName(productDto.getName());
        product.setCategory(Category.valueOf(productDto.getCategory()));
        for (String attribute: productDto.getAttributes()) {
            product.getAttributes().add(Attribute.valueOf(attribute));

        }
        product.setPrice(productDto.getPrice());
        return convertToDTO(productRepository.save(product));
    }
}