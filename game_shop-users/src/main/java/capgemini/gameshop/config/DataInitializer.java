package capgemini.gameshop.config;


import capgemini.gameshop.exception.UserNotFoundException;
import capgemini.gameshop.model.Adress;
import capgemini.gameshop.model.Roles;
import capgemini.gameshop.model.User;
import capgemini.gameshop.repository.AdressRepository;
import capgemini.gameshop.repository.UserRepository;
import capgemini.gameshop.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * This object initialize data for database for testing porpoise
 */
@Component
@AllArgsConstructor
@Transactional
public class DataInitializer {
    private AdressRepository adressRepository;
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    /**
     * Data Initializing method.
     */
    public void initialize() {


        //creating users and saving them to repository

        createUser("Marian", "marian@kowalski.com", "password", Roles.USER);
        createUser("Michał", "michal@tworuszka.com", "password1", Roles.ADMIN);
        createUser("Oskar", "oskar@hanke.com", "password2", Roles.ADMIN);
        createUser("Paweł", "pawel@manowski.com", "password3", Roles.ADMIN);
        createUser("Janina", "janina@nowak.com", "password4", Roles.USER);
        createUser("Aleksandra", "ola@pietruszka.com", "password5", Roles.USER);
        createUser("Krzysztof", "kdudek@gmail.com", "password6", Roles.USER);


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
     * @param username - username of the user
     * @param email - unique user email adress
     * @param password - user's login password
     */
    public void createUser(String username, String email, String password, Roles role){
        User user = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.bCryptPasswordEncoder().encode(password))
//                .password(password)
                .active(true)
                .role(role)
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
