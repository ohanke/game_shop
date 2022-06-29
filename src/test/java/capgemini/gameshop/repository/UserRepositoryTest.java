package capgemini.gameshop.repository;

import capgemini.gameshop.GameShopApplication;
import capgemini.gameshop.entity.User;
import capgemini.gameshop.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = GameShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Searching for users by eamil, success")
    public void findByEmail_emailExists_success() {
        User user = new User();
        user.setFirstName("Ola");
        user.setLastName("Plus");
        user.setEmail("po@op.pl");
        user.setPassword("asffsw");
        userRepository.save(user);

        User userOut = userRepository.findByEmail(user.getEmail()).orElseThrow(() -> new UserNotFoundException(user.getEmail()));
        assertEquals(userOut.getFirstName(), user.getFirstName());
        assertEquals(userOut.getLastName(), user.getLastName());
        assertEquals(userOut.getEmail(), user.getEmail());
    }


}