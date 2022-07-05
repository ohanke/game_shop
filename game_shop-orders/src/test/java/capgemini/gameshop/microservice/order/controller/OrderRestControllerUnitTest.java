package capgemini.gameshop.microservice.order.controller;

import capgemini.gameshop.microservice.order.dto.OrderDto;
import capgemini.gameshop.microservice.order.model.OrderStatus;
import capgemini.gameshop.microservice.order.repository.ProductRepository;
import capgemini.gameshop.microservice.order.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ExtendWith(SpringExtension.class)
class OrderRestControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    JacksonTester<OrderDto> orderTester;

    @MockBean
    OrderService orderService;

    @MockBean
    ProductRepository productRepository;

    @Test
    @DisplayName("Test if List of Orders on the url has 200 status and type Json")
    void get_getAllAsJSON_success() throws Exception {
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(orderService).findAll();
    }

    @Test
    void get_validId_success() throws Exception {
        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk());

        verify(orderService).findById(anyLong());
    }

    @Test
    void create_validBody_success() throws Exception {
        OrderDto orderToSave = new OrderDto(1L, 50.50, OrderStatus.PROCESSING);

        when(orderService.create(any())).thenReturn(orderToSave);

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                                .content(orderTester.write(orderToSave).getJson()))
                        .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(orderService).create(any());
    }

    @Test
    void update_validBody_success() throws Exception {
        OrderDto orderToBeUpdated = new OrderDto();
        orderToBeUpdated.setId(1L);
        OrderDto updateBody = new OrderDto(1L, 50.50, OrderStatus.PROCESSING);

        when(orderService.findById(anyLong())).thenReturn(orderToBeUpdated);

        mockMvc.perform(put("/api/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(orderTester.write(updateBody).getJson()))
                        .andExpect(status().isNoContent());


        verify(orderService).update(anyLong(), any());
    }

    @Test
    void delete_validId_success() throws Exception {
        OrderDto orderToDelete = new OrderDto();
        orderToDelete.setId(1L);

        when(orderService.findById(anyLong())).thenReturn(orderToDelete);

        mockMvc.perform(delete("/api/orders/1"))
                .andExpect(status().isNoContent());

        verify(orderService).delete(anyLong());
    }

    @Test
    void addProduct_validParams_success() throws Exception {
        when(orderService.addProduct(anyLong(), anyLong())).thenReturn(new OrderDto());

        mockMvc.perform(get("/api/orders/1/add/2"))
                        .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(orderService).addProduct(anyLong(), anyLong());
    }
}