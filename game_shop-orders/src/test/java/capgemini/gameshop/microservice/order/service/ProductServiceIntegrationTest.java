package capgemini.gameshop.microservice.order.service;

import capgemini.gameshop.microservice.order.dto.ProductDto;
import capgemini.gameshop.microservice.order.exception.ProductNotFoundException;
import capgemini.gameshop.microservice.order.model.Attribute;
import capgemini.gameshop.microservice.order.model.Category;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ProductServiceIntegrationTest {

    @Autowired
    ProductService productService;

    @Test
    void findAll() {
        //given
        ProductDto productDto = new ProductDto();
        productDto.setName("Flock");
        productDto.setCategory("ACTION");
        productDto.setAttributes(Set.of("TEEN"));
        productDto.setPrice(50.0);
        productService.save(productDto);

        //when
        List<ProductDto> products = productService.findAll();

        //then
        assertTrue(products.size() > 0);
    }

    @Test
    void findById_validId_success() {
        //given
        Long id = 1L;

        //when
        ProductDto productFound = productService.findById(id);

        //then
        assertEquals(id, productFound.getId());
    }

    @Test
    void findById_invalidId_throwsException(){
        //given
        Long id = -5L;

        //when
        //then
        assertThrows(ProductNotFoundException.class, () -> productService.findById(id));
    }

    @Test
    void save_validProduct_success() {
        //given
        ProductDto productDto = new ProductDto();
        productDto.setName("Flock");
        productDto.setCategory("ACTION");
        productDto.setAttributes(Set.of("TEEN"));
        productDto.setPrice(50.0);

        //when
        ProductDto savedProduct = productService.save(productDto);
        assertNotNull(savedProduct.getId());
    }

    @Test
    @Disabled
    void update_productWithNameOnly_throwsException() {
        //given
        Long id = 1L;
        ProductDto productDto = new ProductDto();
        productDto.setName("Flock");

        //when
        productService.update(id, productDto);

        //then
        ProductDto updatedProduct = productService.findById(id);
        assertEquals(productDto.getName(), updatedProduct.getName());
    }

    @Test
    @Disabled
    void update_allFieldsValid_success() {
        //given
        Long id = 1L;
        ProductDto productDto = new ProductDto(
                "Flock", "ACTION", Set.of("TEEN"), 50.0);

        //when
        productService.update(id, productDto);

        //then
        ProductDto updatedProduct = productService.findById(id);
        assertEquals(productDto.getName(), updatedProduct.getName());
    }

    @Test
    void delete_validId_success() {
        //given
        Long id = 4L;
        int elementsToDelete = 1;
        assertNotNull(productService.findById(id));
        int startingSize = productService.findAll().size();

        //when
        productService.delete(id);
        int afterDeleteSize = productService.findAll().size();

        //then
        assertEquals(afterDeleteSize, (startingSize - elementsToDelete));
    }
}