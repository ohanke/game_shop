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
class UserServiceIntegrationTest {

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
        userService.create(userDto);

        userDto.setFirstName("Michael");
        userDto.setLastName("Jordan");
        userDto.setEmail("mj@nba.com");
        userDto.setPassword("mj");
        userService.create(userDto);

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
    void create_validUser_success() {
        //given
        UserDto userDto = new UserDto();
        userDto.setFirstName("Mike");
        userDto.setLastName("Tyson");
        userDto.setEmail("mike@tyson.pl");
        userDto.setPassword("mike1256");

        //when
        UserDto user = userService.create(userDto);

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
    @DisplayName("Updating existing user with no data change to Database, success")
    void update_existingUserNoDataChange_success() {
        //given
        UserDto userDto = new UserDto();
        userDto.setFirstName("Mike");
        userDto.setLastName("Tyson");
        userDto.setEmail("mike@tyson.pl");
        userDto.setPassword("mike1256");

        //when
        UserDto user = userService.create(userDto);

        UserDto user2 = userService.update(user.getId(), user);

        //then
        assertEquals(user.getId(), user2.getId());
        assertEquals(user.getFirstName(), user2.getFirstName());
        assertEquals(user.getLastName(), user2.getLastName());
        assertEquals(user.getEmail(), user2.getEmail());
    }

    @Test
    @DisplayName("Updating existing user with mail change - new mail already exists in database, throws Exception")
    void update_mailChange_mailExistsInOtherUser_throwsException() {
        //given
        UserDto user1 = new UserDto();
        user1.setFirstName("Mike");
        user1.setLastName("Tyson");
        user1.setEmail("mike@tyson.pl");
        user1.setPassword("mike1256");

        UserDto user2 = new UserDto();
        user2.setFirstName("Morgan");
        user2.setLastName("Freeman");
        user2.setEmail("morg@free.com");
        user2.setPassword("mfgod");

        //when
        UserDto user1Creted = userService.create(user1);
        userService.create(user2);

        EmailExistException thrown = assertThrows(EmailExistException.class, () -> userService.update(user1Creted.getId(), user2));

        //then
        assertEquals("Email: " + user2.getEmail() + " already in use", thrown.getMessage());
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
        userService.create(userDto);
        int initialSize = userService.findAll().size();

        //when
        userService.delete(id);
        int afterDeleteSize = userService.findAll().size();

        //then
        assertTrue(initialSize > afterDeleteSize);
    }
}