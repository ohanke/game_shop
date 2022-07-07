package capgemini.gameshop.repository;

import capgemini.gameshop.OrderGameShopApp;
import capgemini.gameshop.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = OrderGameShopApp.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("Searching products with ID lesser than 5, list size smaller then 5, success")
    void findAllByIdLessThan_success() {

        Long id = 5L;

        List<Product> products = productRepository.findAllByIdLessThan(id);

        //id - 1 becouse finding products with id smaller then ID
        assertEquals(id - 1, products.size());

    }
}