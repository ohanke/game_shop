package capgemini.gameshop.microservice.user.repository;

import capgemini.gameshop.microservice.user.UserGameShopApp;
import capgemini.gameshop.microservice.user.exception.AdressNotFoundException;
import capgemini.gameshop.microservice.user.exception.UserNotFoundException;
import capgemini.gameshop.microservice.user.model.Adress;
import capgemini.gameshop.microservice.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = UserGameShopApp.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AdressRepositoryTest {


    @Autowired
    AdressRepository adressRepository;

    @Autowired
    UserRepository userRepository;


    @Test
    @DisplayName("Searching for adresses by zip, success")
    public void findByZip_zipExist_success() {
        Adress adress = new Adress();
        adress.setUser(userRepository.findById(2L).orElseThrow(() -> new UserNotFoundException(2L)));
        adress.setCountry("USA");
        adress.setCity("New York");
        adress.setStreet("Park Avenue");
        adress.setState("New York");
        adress.setZip("52621");

        adressRepository.save(adress);

        Adress adressOut = adressRepository.findByZip(adress.getZip()).orElseThrow(() -> new AdressNotFoundException(adress.getZip()));
        assertEquals(adressOut.getCountry(), adress.getCountry());
        assertEquals(adressOut.getCity(), adress.getCity());
        assertEquals(adressOut.getStreet(), adress.getStreet());
        assertEquals(adressOut.getState(), adress.getState());
        assertEquals(adressOut.getZip(), adress.getZip());
    }

}
