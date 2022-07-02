package capgemini.gameshop.controller;

import capgemini.gameshop.config.DataInitializer;
import capgemini.gameshop.dto.OrderDto;
import capgemini.gameshop.entity.OrderStatus;
import capgemini.gameshop.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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
@ExtendWith(SpringExtension.class)
class OrderRestControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    OrderService orderService;

    @Test
    @DisplayName("Test if List of Orders on the url has 200 status and type Json")
    public void get_getAllAsJSON_success() throws Exception {
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(orderService).findAll();
    }

    @Test
    public void get_validId_success() throws Exception {
        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk());

        verify(orderService).findById(anyLong());
    }

    @Test
    public void create_validBody_success() throws Exception {
        OrderDto orderToSave = new OrderDto(1L, 50.50, OrderStatus.PROCESSING);

        when(orderService.save(any())).thenReturn(orderToSave);

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(orderToSave)))
                        .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(orderService).save(any());
    }

    @Test
    public void update_validBody_success() throws Exception {
        OrderDto orderToBeUpdated = new OrderDto();
        orderToBeUpdated.setId(1L);
        OrderDto updateBody = new OrderDto(1L, 50.50, OrderStatus.PROCESSING);

        when(orderService.findById(anyLong())).thenReturn(orderToBeUpdated);

        mockMvc.perform(put("/api/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updateBody)))
                        .andExpect(status().isNoContent());


        verify(orderService).update(anyLong(), any());
    }

    @Test
    public void delete_validId_success() throws Exception {
        OrderDto orderToDelete = new OrderDto();
        orderToDelete.setId(1L);

        when(orderService.findById(anyLong())).thenReturn(orderToDelete);

        mockMvc.perform(delete("/api/orders/1"))
                .andExpect(status().isNoContent());

        verify(orderService).delete(anyLong());
    }
}