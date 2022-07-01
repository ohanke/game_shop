package capgemini.gameshop.controller;

import capgemini.gameshop.dto.UserDto;
import capgemini.gameshop.exception.UserNotFoundException;
import capgemini.gameshop.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserRestControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    UserService userService;


    @Test
    @DisplayName("GET /api/users Search all Users on the url has 200 status and type Json")
    public void get_JsonUserList_succes() throws Exception {
        mockMvc.perform(get("/api/users/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(userService).findAll();
    }

    @Test
    @DisplayName("GET /api/users/id Search user by id with correct id returns 200")
    public void get_findUserById_success() throws Exception {

        mockMvc.perform(get("/api/users/id/1"))
                .andExpect(status().isOk());

        verify(userService).findById(1L);
    }

    @Test
    @DisplayName("GET /api/users/email Search user by email with correct email returns 200")
    public void get_findUserByEmail_success() throws Exception {

        mockMvc.perform(get("/api/users/email/oskar@hanke.com"))
                .andExpect(status().isOk());

        verify(userService).findByEmail("oskar@hanke.com");

    }

    @Test
    @DisplayName("GET /api/users/email Search user by email with incorect email returns 404")
    public void get_findUserByEmail_error() throws Exception {

        when(userService.findByEmail("abcd")).thenThrow(UserNotFoundException.class);

        mockMvc.perform(get("/api/users/email/abcd"))
                .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("POST /api/users/create Creates user returns 201")
    public void post_createUser_success() throws Exception {
        UserDto saveUser = new UserDto("Scottie", "Pippen", "scot@pipe.com", "1234567");

        Mockito.when(userService.create(any())).thenReturn(saveUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(saveUser)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }


    @Test
    @DisplayName("DELETE /api/users/delete Deletes user - status 204 ")
    public void delete_success() throws Exception {

        UserDto userDto = new UserDto();
        Mockito.when(userService.findById(userDto.getId())).thenReturn(userDto);

        mockMvc.perform(delete("/api/users/delete/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }
}