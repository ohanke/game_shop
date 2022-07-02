package capgemini.gameshop.service;

import capgemini.gameshop.dto.OrderDto;
import capgemini.gameshop.entity.OrderStatus;
import capgemini.gameshop.exception.OrderNotFoundException;
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

    @Test
    void findAll(){
        //given
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(1L);
        orderDto.setTotalValue(500);
        orderDto.setOrderStatus(OrderStatus.RECIEVED);
        orderService.save(orderDto);
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
        orderDto.setTotalValue(500);
        orderDto.setOrderStatus(OrderStatus.RECIEVED);
        orderService.save(orderDto);

        //when
        OrderDto savedOrder = orderService.save(orderDto);

        //then
        assertNotNull(savedOrder.getId());
    }

    @Test
    void update_allFieldsValid_success(){
        //given
        Long id = 1L;
        OrderDto updateBody = new OrderDto(1L, 50.50, OrderStatus.PROCESSING);

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
}