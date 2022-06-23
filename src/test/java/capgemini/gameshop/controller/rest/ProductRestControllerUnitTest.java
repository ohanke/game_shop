package capgemini.gameshop.controller.rest;

import capgemini.gameshop.config.DataInitializer;
import capgemini.gameshop.service.ProductService;
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

@WebMvcTest(ProductRestController.class)
class ProductRestControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DataInitializer dataInitializer;

    @MockBean
    ProductService productService;

    @Test
    @DisplayName("Test if List of Products on the url has 200 status and type Json")
    void get_JsonProductList_succes() throws Exception{
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(productService).findAll();
    }
}