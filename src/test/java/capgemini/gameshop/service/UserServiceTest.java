package capgemini.gameshop.service;

import capgemini.gameshop.dto.UserDto;
import capgemini.gameshop.exception.EmailExistException;
import capgemini.gameshop.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("Finding all users - success")
    void findAll_success() {
        //given
        UserDto userDto = new UserDto();
        userDto.setFirstName("Mike");
        userDto.setLastName("Tyson");
        userDto.setEmail("mike@tyson.pl");
        userDto.setPassword("mike1256");
        userService.save(userDto);

        userDto.setFirstName("Michael");
        userDto.setLastName("Jordan");
        userDto.setEmail("mj@nba.com");
        userDto.setPassword("mj");
        userService.save(userDto);

        //when
        List<UserDto> users = userService.findAll();

        //then
        assertTrue(users.size() > 0);

    }

    @Test
    @DisplayName("Finding users by ID - success")
    void findById_validId_success() {
        //given
        Long id = 3L;

        //when
        UserDto user = userService.findById(id);

        //then
        assertEquals(id, user.getId());
    }

    @Test
    @DisplayName("Finding users by ID, invalid data, throws exception")
    void findById_invalidId_throwsException() {
        //given
        Long id = 100L;

        //when
        UserNotFoundException thrown = assertThrows(UserNotFoundException.class, () -> userService.findById(id));

        //then
        assertEquals("User with id: " + id + " not found", thrown.getMessage());

    }

    @Test
    @DisplayName("Finding users by email, new email added, success")
    void findByEmail_validEmail_success() {

        //given
        String email = "michal@tworuszka.com";

        //when
        UserDto user = userService.findByEmail(email);

        //then
        assertEquals(email, user.getEmail());
    }

    @Test
    @DisplayName("Finding user by email, invalid email, throws Exception")
    void findByEmail_invalidEmail_throwsException() {
        //given
        String email = "michal@tworuszka.coooom";

        //when
        UserNotFoundException thrown = assertThrows(UserNotFoundException.class, () -> userService.findByEmail(email));

        //then
        assertEquals("User with email: " + email + " not found", thrown.getMessage());
    }

    @Test
    @DisplayName("Saving valid user do Database, success")
    void save_validUser_success() {
        //given
        UserDto userDto = new UserDto();
        userDto.setFirstName("Mike");
        userDto.setLastName("Tyson");
        userDto.setEmail("mike@tyson.pl");
        userDto.setPassword("mike1256");

        //when
        UserDto user = userService.save(userDto);

        //then
        assertNotNull(user.getId());

    }

    @Test
    @DisplayName("Updating users email, new email added, success")
    void update_userMailOnly_success() {
        //given
        Long id = 5L;
        UserDto user = new UserDto();
        user.setEmail("new@mail.com");

        //when
        userService.update(id, user);
        //then
        assertEquals(userService.findById(id).getEmail(), user.getEmail());

    }

    @Test
    @DisplayName("Updating users email, email already exists in database, throws Exception")
    void update_userMailOnly_thowsEmailExistException() {
        Long id = 3L;
        UserDto user = new UserDto();
        user.setEmail("oskar@hanke.com");

        //when
        EmailExistException thrown = assertThrows(EmailExistException.class, () -> userService.update(id, user));

        //then
        assertEquals("Email: " + user.getEmail() + " already in use", thrown.getMessage());
    }

    @Test
    @DisplayName("Deleting user from database, success")
    void delete_success() {
        //given
        Long id = 4L;

        UserDto userDto = new UserDto();
        userDto.setFirstName("Indiana");
        userDto.setLastName("Jones");
        userDto.setEmail("indi@jones.com");
        userDto.setPassword("rock123");
        userService.save(userDto);
        int initialSize = userService.findAll().size();

        //when
        userService.delete(id);
        int afterDeleteSize = userService.findAll().size();

        //then
        assertTrue(initialSize > afterDeleteSize);
    }
}