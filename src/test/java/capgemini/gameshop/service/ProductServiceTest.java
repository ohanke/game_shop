package capgemini.gameshop.service;

import capgemini.gameshop.dto.ProductDto;
import capgemini.gameshop.entity.Attribute;
import capgemini.gameshop.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    void findAll() {
        //given
        ProductDto productDto = new ProductDto();
        productDto.setName("Flock");
        productDto.setCategory(Category.ACTION);
        productDto.setAttributes(Set.of(Attribute.TEEN));
        productDto.setPriceNett(10.0);
        productDto.setPriceGross(50.0);
        productService.save(productDto);

        //when
        List<ProductDto> products = productService.findAll();

        //then
        assertTrue(products.size() > 0);
    }

    @Test
    void findById_validId_succes() {
        //given
        Long id = 1L;

        //when
        ProductDto productFound = productService.findById(id);

        //then
        assertEquals(id, productFound.getId());
    }

    @Test
    void save_validProduct_success() {
        //given
        ProductDto productDto = new ProductDto();
        productDto.setName("Flock");
        productDto.setCategory(Category.ACTION);
        productDto.setAttributes(Set.of(Attribute.TEEN));
        productDto.setPriceNett(10.0);
        productDto.setPriceGross(50.0);

        //when
        ProductDto savedProduct = productService.save(productDto);
        assertNotNull(savedProduct.getId());
    }

    @Test
    void update_productWithNameOnly_success() {
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
    void delete() {
        //given
        Long id = 4L;

        ProductDto productDto = new ProductDto();
        productDto.setName("Flock");
        productDto.setCategory(Category.ACTION);
        productDto.setAttributes(Set.of(Attribute.TEEN));
        productDto.setPriceNett(10.0);
        productDto.setPriceGross(50.0);
        productService.save(productDto);
        int initialAmount = productService.findAll().size();

        //when
        productService.delete(id);
        int actualAmount = productService.findAll().size();

        //then
        assertTrue(initialAmount > actualAmount);
    }
}