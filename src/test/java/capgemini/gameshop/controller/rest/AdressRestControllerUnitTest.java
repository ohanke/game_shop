package capgemini.gameshop.controller.rest;

import capgemini.gameshop.service.AdressService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdressRestController.class)
class AdressRestControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AdressService adressService;

    @Test
    @DisplayName("Test if List of Adresses on the url has 200 status and type Json")
    void get_JsonAdressesList_succes() throws Exception {
        mockMvc.perform(get("/api/adresses"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(adressService).getAllAdresses();
    }
}