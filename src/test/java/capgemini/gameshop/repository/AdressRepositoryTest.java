package capgemini.gameshop.repository;

import capgemini.gameshop.entity.Adress;
import capgemini.gameshop.exception.AdressNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdressRepositoryTest {


    @Autowired
    AdressRepository adressRepository;


    @Test
    @DisplayName("Searching for adresses by zip, success")
    public void findByZip_zipExist_success() {
        Adress adress = new Adress();
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
