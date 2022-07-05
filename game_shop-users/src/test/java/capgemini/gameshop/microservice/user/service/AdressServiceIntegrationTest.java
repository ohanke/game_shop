package capgemini.gameshop.microservice.user.service;

import capgemini.gameshop.microservice.user.dto.AdressDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Transactional
    @SpringBootTest
    public class AdressServiceIntegrationTest {


        @Autowired
        AdressService adressService;

        @Test
    @DisplayName("Finding adresses by ID - success")
    void findById_validId_success() {

        Long id = 2L;

        AdressDto adress = adressService.findById(id);

        assertEquals(id, adress.getId());
    }
        @Test
        @DisplayName("Finding adresses by zip, new zip added, success")
        void findByZip_validZip_success() {

            //given
            String zip = "44-100";

            //when
            AdressDto adress = adressService.findByZip(zip);

            //then
            assertEquals(zip, adress.getZip());
        }
    @Test
    @DisplayName("Saving valid adress do Database, success")
    void create_validAdress_success() {

        AdressDto adressDto = new AdressDto();
        adressDto.setUserId(2L);
        adressDto.setCountry("Poland");
        adressDto.setCity("Poznan");
        adressDto.setStreet("Naramowicka");
        adressDto.setState("---");
        adressDto.setZip("62605");

        //when
        AdressDto adress = adressService.create(adressDto);

        //then
        assertNotNull(adress.getId());

    }
}
