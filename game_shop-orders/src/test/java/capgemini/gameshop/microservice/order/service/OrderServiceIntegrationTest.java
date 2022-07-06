package capgemini.gameshop.microservice.order.service;

import capgemini.gameshop.microservice.order.dto.OrderDto;
import capgemini.gameshop.microservice.order.exception.DuplicateOrderingOfProductException;
import capgemini.gameshop.microservice.order.exception.OrderNotFoundException;
import capgemini.gameshop.microservice.order.exception.ProductNotFoundException;
import capgemini.gameshop.microservice.order.model.OrderStatus;
import capgemini.gameshop.microservice.order.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OrderServiceIntegrationTest {

    @Autowired
    OrderService orderService;
    @Autowired
    ProductRepository productRepository;

    @Test
    void findAll(){
        //given
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(1L);
        orderDto.setTotalValue(500);
        orderDto.setOrderStatus("NEW");
        orderService.create(orderDto);
        //when

        List<OrderDto> orders = orderService.findAll();
        //then
        assertTrue(orders.size() > 0);
    }

    @Test
    void findById_validId_success(){
        //given
        Long id = 1L;

        //when
        OrderDto orderFound = orderService.findById(id);

        //then
        assertEquals(id, orderFound.getId());
    }

    @Test
    void findById_invalidId_throwsException(){
        //given
        Long id = -5L;

        //when
        //then
        assertThrows(OrderNotFoundException.class, () -> orderService.findById(id));
    }

    @Test
    void save_validOrder_success(){
        //given
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(1L);
        orderService.create(orderDto);

        //when
        OrderDto savedOrder = orderService.create(orderDto);

        //then
        assertNotNull(savedOrder.getId());
    }

    @Test
    void update_allFieldsValid_success(){
        //given
        Long id = 1L;
        OrderDto updateBody = new OrderDto(1L, 50.50, "PROCESSING");

        //when
        orderService.update(id, updateBody);
        OrderDto updatedOrder = orderService.findById(id);

        //then
        assertEquals(updateBody.getUserId(), updatedOrder.getUserId());
        assertEquals(updateBody.getTotalValue(), updatedOrder.getTotalValue());
        assertEquals(updateBody.getOrderStatus(), updatedOrder.getOrderStatus());
    }

    @Test
    void delete_validId_success(){
        //given
        Long id = 1L;
        int elementsToDelete = 1;
        assertNotNull(orderService.findById(id));
        int startingSize = orderService.findAll().size();

        //when
        orderService.delete(id);
        int afterDeleteSize = orderService.findAll().size();

        //then
        assertEquals(afterDeleteSize, (startingSize - elementsToDelete));
    }

    @Test
    void addProduct_bothIdValid_success(){
        //given
        Long orderId = 1L;
        Long productId = 2L;
        int startingProductsAmount = orderService.findById(orderId).getProducts().size();

        //when
        OrderDto modifiedOrder = orderService.addProduct(orderId, productId);

        //then
        assertTrue(modifiedOrder.getProducts().size() > startingProductsAmount);
    }

    @Test
    void addProduct_productIdAlreadyInOrder_throwsException(){
        //given
        Long orderId = 1L;
        Long productId = 1L;

        //when
        //then
        assertThrows(DuplicateOrderingOfProductException.class, () -> orderService.addProduct(orderId, productId));
    }

    @Test
    void addProduct_invalidOrderId_throwsException(){
        //given
        Long orderId = -5L;
        Long productId = 1L;

        //when
        //then
        assertThrows(OrderNotFoundException.class, () -> orderService.addProduct(orderId, productId));
    }

    @Test
    void addProduct_invalidProductId_throwsException(){
        //given
        Long orderId = 1L;
        Long productId = -5L;

        //when
        //then
        assertThrows(ProductNotFoundException.class, () -> orderService.addProduct(orderId, productId));
    }
}