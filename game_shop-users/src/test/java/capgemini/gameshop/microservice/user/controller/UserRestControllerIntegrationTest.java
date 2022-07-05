package capgemini.gameshop.microservice.user.controller;

import capgemini.gameshop.microservice.user.dto.UserDto;
import capgemini.gameshop.microservice.user.service.UserService;
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
public class UserRestControllerIntegrationTest {

    @Autowired
    JacksonTester<UserDto> userTester;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserService userService;

    @Test
    void get_listOfAllUser() throws Exception {
        mockMvc.perform(get("/api/users/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void get_validId_singleUser() throws Exception {
        //given
        JsonContent<UserDto> user = userTester.write(userService.findById(1L));

        //when
        //then
        mockMvc.perform(get("/api/users/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(user.getJson()));
    }

    @Test
    void create_validBody_success() throws Exception {
        UserDto userToSave = new UserDto("Scottie", "Pippen", "scot@pipe.com", "123456789");

        mockMvc.perform(post("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userTester.write(userToSave).getJson()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void delete_validId_success() throws Exception {
        //given
        UserDto userToDelete = userService.create(new UserDto("Scottie", "Men", "scot@pipe.com", "123456789"));

        //when
        //then
        mockMvc.perform(delete("/api/users/delete/" + userToDelete.getId()))
                .andExpect(status().isNoContent());
    }
}
