package capgemini.gameshop.controller.rest;

import capgemini.gameshop.config.DataInitializer;
import capgemini.gameshop.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderRestController.class)
class OrderRestControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DataInitializer dataInitializer;

    @MockBean
    OrderService orderService;

    @Test
    @DisplayName("Test if List of Orders on the url has 200 status and type Json")
    void get_JsonOrderList_succes() throws Exception {
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(orderService).findAll();
    }
}