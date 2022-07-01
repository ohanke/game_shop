package capgemini.gameshop.service;

import capgemini.gameshop.dto.ProductDto;
import capgemini.gameshop.entity.Attribute;
import capgemini.gameshop.entity.Category;
import capgemini.gameshop.entity.Product;
import capgemini.gameshop.exception.ProductNotFoundException;
import capgemini.gameshop.repository.ProductRepository;
import org.junit.Ignore;
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

    @Disabled
    @Test
    void findAll(){
        //given
        List<ProductDto> productDtos = List.of(new ProductDto());
        List<Product> products = List.of(new Product());
        Product product = new Product();
        ProductDto productDto = new ProductDto();

        when(productRepository.findAll()).thenReturn(products);
        when(modelMapper.map(any(),any())).thenReturn(productDtos);


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
}
