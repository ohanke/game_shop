package capgemini.gameshop.controller;

import capgemini.gameshop.dto.ProductDto;
import capgemini.gameshop.entity.Attribute;
import capgemini.gameshop.entity.Category;
import capgemini.gameshop.entity.Product;
import capgemini.gameshop.service.ProductService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ProductRestControllerIntegrationTest {

    @Autowired
    JacksonTester<ProductDto> productTester;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProductService productService;

    @Test
    void get_listOfAllProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void get_validId_singleProduct() throws Exception {
        //given
        JsonContent<ProductDto> product = productTester.write(productService.findById(1L));

        //when
        //then
        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(product.getJson()));
    }

    @Test
    void create_validBody_success() throws Exception{
        ProductDto productToSave = new ProductDto(
                "Flock", Category.ACTION, Set.of(Attribute.TEEN), 10.55);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productTester.write(productToSave).getJson()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_validBody_success() throws Exception {
        ProductDto updateBody = new ProductDto(
                "Flock", Category.ACTION, Set.of(Attribute.TEEN), 10.55);

        mockMvc.perform(put("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(productTester.write(updateBody).getJson()))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_validId_success() throws Exception {
        //given
        ProductDto productToDelete = productService.save(new ProductDto(
                "Flock", Category.ACTION, Set.of(Attribute.TEEN), 10.55));

        //when
        //then
        mockMvc.perform(delete("/api/products/" + productToDelete.getId()))
                .andExpect(status().isNoContent());
    }
}
