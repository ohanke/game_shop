package capgemini.gameshop.microservice.order.model;

import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
class OrderTest {
    @Test
    void getTotalValueOfProducts(){
        //given
        double expectedValue = 175.5;
        Product product1 = new Product();
        product1.setPrice(100.0);
        Product product2 = new Product();
        product2.setPrice(75.50);
        Order order = new Order();
        order.setUserId(2L);
        order.setOrderStatus(OrderStatus.NEW);
        order.setProducts(Set.of(product1, product2));

        //when
        double totalValue = order.getTotalValueOfProducts();

        //then
        assertEquals(expectedValue, totalValue);
    }

}