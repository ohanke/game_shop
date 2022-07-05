package capgemini.gameshop.microservice.order.entity;

import capgemini.gameshop.microservice.order.model.Order;
import capgemini.gameshop.microservice.order.model.OrderStatus;
import capgemini.gameshop.microservice.order.model.Product;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
class OrderTest {

    @Test
    void getProductsIdOfOrder_expectedAmount(){
        //given
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);
        Product product3 = new Product();
        product3.setId(3L);
        int expectedSize = 3;

        Order order = new Order();
        order.setUserId(2L);
        order.setOrderStatus(OrderStatus.NEW);
        order.setProducts(Set.of(product1, product2, product3));

        //when
        Set<Long> productsId = order.getProductsIdOfOrder();

        //then
        assertEquals(expectedSize, productsId.size());
    }

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