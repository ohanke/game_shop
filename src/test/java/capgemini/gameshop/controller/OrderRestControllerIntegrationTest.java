package capgemini.gameshop.controller;

import capgemini.gameshop.dto.OrderDto;
import capgemini.gameshop.entity.OrderStatus;
import capgemini.gameshop.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class OrderRestControllerIntegrationTest {

    @Autowired
    JacksonTester<OrderDto> orderTester;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrderService orderService;

    @Test
    void get_listOfAllOrders() throws Exception {
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void get_validId_singleOrder() throws Exception{
        //given
        JsonContent<OrderDto> order = orderTester.write(orderService.findById(1L));

        //when
        //then
        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(order.getJson()));
    }

    @Test
    void create_validBody_success() throws Exception{
        //given
        OrderDto orderToSave = new OrderDto(1L, 50.50, OrderStatus.PROCESSING);

        //when
        //then
        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderTester.write(orderToSave).getJson()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_validBody_success() throws Exception{
        //given
        Long id = 1L;
        OrderDto updateBody = new OrderDto(1L, 50.50, OrderStatus.PROCESSING);

        //when
        mockMvc.perform(put("/api/orders/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(orderTester.write(updateBody).getJson()))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_validId_success() throws Exception{
        //given
        OrderDto orderToDelete = orderService.create(new OrderDto(1L, 50.50, OrderStatus.PROCESSING));

        mockMvc.perform(delete("/api/orders/" + orderToDelete.getId()))
                .andExpect(status().isNoContent());
    }
}
