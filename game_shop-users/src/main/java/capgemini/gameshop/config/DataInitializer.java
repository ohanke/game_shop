package capgemini.gameshop.config;


import capgemini.gameshop.exception.UserNotFoundException;
import capgemini.gameshop.model.Adress;
import capgemini.gameshop.model.User;
import capgemini.gameshop.repository.AdressRepository;
import capgemini.gameshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * This object initialize data for database for testing porpoise
 */
@Component
@AllArgsConstructor
@Transactional
public class DataInitializer {
    private AdressRepository adressRepository;
    private UserRepository userRepository;

    /**
     * Data Initializing method.
     */
    public void initialize() {


        //creating users and saving them to repository

        createUser("Marian", "Kowalski", "marian@kowalski.com", "password");
        createUser("Michał", "Tworuszka", "michal@tworuszka.com", "password1");
        createUser("Oskar", "Hanke", "oskar@hanke.com", "password2");
        createUser("Paweł", "Manowski", "pawel@manowski.com", "password3");
        createUser("Janina", "Nowak", "janina@nowak.com", "password4");
        createUser("Aleksandra", "Pietruszka", "ola@pietruszka.com", "password5");
        createUser("Krzysztof", "Dudek", "kdudek@gmail.com", "password6");


        //creating addresses and saving them to repository

        createAdress("oskar@hanke.com", "Polska",  "Garncarska 17a",  "Pomorskie",  "Gdańsk", "12-345");
        createAdress("michal@tworuszka.com", "Polska" , "Lipowa 12", "Śląskie", "Gliwice", "44-100");
        createAdress("michal@tworuszka.com", "Polska",  "Zachodnia 2",  "Śląskie",  "Zabrze", "41-800");
        createAdress("janina@nowak.com", "Polska",  "Rynek 14",  "Śląskie",  "Katowice", "40-000");
        createAdress("kdudek@gmail.com", "Polska",  "'Królewska 11a",  "Małopolskie",  "Kraków", "31-923");
        createAdress("kdudek@gmail.com", "Polska",  "Wszystkich Świętych 3",  "Małopolskie",  "Kraków", "31-004");


    }

    /**
     * Method to create user object and save it in user repository
     * @param firstName - first name of user
     * @param lastName - surname of user
     * @param email - unique user email adress
     * @param password - user's login password
     */
    public void createUser(String firstName, String lastName, String email, String password){
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .active(true)
                .build();
        userRepository.save(user);
    }

    /**
     * Method to create adress object and save it in adress repository
     * @param email - this field is unique at user so this method searching user by it in repository
     * @param country
     * @param street
     * @param state
     * @param city
     * @param zipCode
     */
    public void createAdress(String email, String country, String street, String state, String city, String zipCode){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        Adress adress = Adress.builder()
                .user(user)
                .country(country)
                .street(street)
                .state(state)
                .city(city)
                .zip(zipCode)
                .build();
        adressRepository.save(adress);
    }

}
