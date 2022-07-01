package capgemini.gameshop.service;

import capgemini.gameshop.dto.ProductDto;
import capgemini.gameshop.entity.Attribute;
import capgemini.gameshop.entity.Category;
import capgemini.gameshop.entity.Product;
import capgemini.gameshop.exception.ProductNotFoundException;
import capgemini.gameshop.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceUnitTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository, modelMapper);
    }

    @Test
    void findAll_listOfOneProduct_success(){
        List<Product> products = new ArrayList<>();
        products.add(new Product());

        when(productRepository.findAll()).thenReturn(products);
        when(modelMapper.map(any(),any())).thenReturn(new ProductDto());

        //when
        List<ProductDto> productsReturned = productService.findAll();

        //then
        Assertions.assertNotNull(productsReturned);
        verify(productRepository).findAll();
    }

    @Test
    void findById_validId_success(){
        //given
        Long id = 1L;
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        Product product = new Product();
        product.setId(id);
        Optional<Product> productOptional = Optional.of(product);

        when(productRepository.findById(anyLong())).thenReturn(productOptional);
        when(modelMapper.map(any(),any())).thenReturn(productDto);

        //when
        ProductDto dtoReturned = productService.findById(id);

        //then
        Assertions.assertEquals(id, dtoReturned.getId());
        verify(productRepository).findById(anyLong());
    }

    @Test
    void findById_notExistingId_throwsException(){
        //given
        Long id = -5L;
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        Product product = new Product();
        product.setId(id);
        Optional<Product> productOptional = Optional.of(product);

        when(productRepository.findById(anyLong())).thenThrow(ProductNotFoundException.class);

        //when
        //then
        Assertions.assertThrows(ProductNotFoundException.class, () -> productService.findById(id));
        verify(productRepository).findById(anyLong());
    }

    @Test
    @Disabled
    void save_validProduct_success(){
        //given
        Long id = 501L;
        ProductDto dto = new ProductDto();
        dto.setName("Flock");
        dto.setCategory(Category.ACTION);
        dto.setAttributes(Set.of(Attribute.TEEN));
        dto.setPrice(50.0);

        Product productToSave = new Product();
        productToSave.setName("Flock");
        productToSave.setCategory(Category.ACTION);
        productToSave.setAttributes(Set.of(Attribute.TEEN));
        productToSave.setPrice(50.0);

        Product returnedProduct = new Product();
        returnedProduct.setId(501L);
        returnedProduct.setName("Flock");
        returnedProduct.setCategory(Category.ACTION);
        returnedProduct.setAttributes(Set.of(Attribute.TEEN));
        returnedProduct.setPrice(50.0);

        when(productRepository.save(any())).thenReturn(returnedProduct);
        when(modelMapper.map(ProductDto.class, Product.class)).thenReturn(new Product());
        when(modelMapper.map(Product.class, ProductDto.class)).thenReturn(new ProductDto());

        //when
        ProductDto savedProduct = productService.save(dto);

        //then
        System.out.println(savedProduct.getName());
        verify(productRepository).save(any());
    }

    @Test
    @Disabled
    void update_validProduct_success(){

    }
    @Test
    void delete_validId_success(){
        //given
        Long id = 4L;

        //when
        productService.delete(id);

        //then
        verify(productRepository).deleteById(anyLong());
    }
}
