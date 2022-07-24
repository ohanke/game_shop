package capgemini.gameshop.repository;

import capgemini.gameshop.UserGameShopApp;
import capgemini.gameshop.exception.UserNotFoundException;
import capgemini.gameshop.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = UserGameShopApp.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Searching for users by eamil, success")
    public void findByEmail_emailExists_success() {
        User user = new User();
        user.setUsername("Ola");
        user.setEmail("po@op.pl");
        user.setPassword("asffsw");
        userRepository.save(user);

        User userOut = userRepository.findByEmail(user.getEmail()).orElseThrow(() -> new UserNotFoundException(user.getEmail()));
        assertEquals(userOut.getUsername(), user.getUsername());
        assertEquals(userOut.getEmail(), user.getEmail());
    }


}