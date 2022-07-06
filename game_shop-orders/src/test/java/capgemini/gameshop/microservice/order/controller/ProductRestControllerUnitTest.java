package capgemini.gameshop.microservice.order.controller;

import capgemini.gameshop.microservice.order.dto.ProductDto;
import capgemini.gameshop.microservice.order.model.Attribute;
import capgemini.gameshop.microservice.order.model.Category;
import capgemini.gameshop.microservice.order.service.ProductService;
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

import java.util.Set;

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
class ProductRestControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    JacksonTester<ProductDto> productTester;

    @MockBean
    ProductService productService;

    @Test
    @DisplayName("Test if List of Products on the url has 200 status and type Json")
    public void get_listOfAllProducts() throws Exception{
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(productService).findAll();
    }

    @Test
    public void get_validId_singleProducts() throws Exception {
        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk());

        verify(productService).findById(anyLong());
    }

    @Test
    public void create_validBody_success() throws Exception{
        ProductDto productToSave = new ProductDto(
                "Flock", "ACTION", Set.of("TEEN"), 10.55);

        when(productService.save(any())).thenReturn(productToSave);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(productTester.write(productToSave).getJson()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(productService).save(any());
    }

    @Test
    public void update_validBody_success() throws Exception{
        ProductDto productToBeUpdated = new ProductDto();
        productToBeUpdated.setId(1L);
        ProductDto updateBody = new ProductDto(
                "Flock", "ACTION", Set.of("TEEN"), 10.55);

        when(productService.findById(anyLong())).thenReturn(productToBeUpdated);

        mockMvc.perform(put("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(productTester.write(updateBody).getJson()))
                .andExpect(status().isNoContent());

        verify(productService).update(anyLong(), any());
    }

    @Test
    public void delete_validId_success() throws Exception {
        ProductDto productToDelete = new ProductDto();
        productToDelete.setId(1L);

        when(productService.findById(anyLong())).thenReturn(productToDelete);

        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isNoContent());

        verify(productService).delete(anyLong());
    }
}