package capgemini.gameshop.config;


import capgemini.gameshop.entity.*;
import capgemini.gameshop.exception.UserNotFoundException;
import capgemini.gameshop.repository.AdressRepository;
import capgemini.gameshop.repository.OrderRepository;
import capgemini.gameshop.repository.ProductRepository;
import capgemini.gameshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.*;

import static capgemini.gameshop.entity.Attribute.*;
import static capgemini.gameshop.entity.Category.*;

/**
 * This object initialize data for database for testing porpoise
 */
@RequiredArgsConstructor
public class DataInitializer {

    private final static double VAT = 1.23d; //nett to gross parameter (based on polish tax value of 23%)
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final AdressRepository adressRepository;
    private final UserRepository userRepository;

    /**
     * Initializing method.
     */
    public void initialize() {

        //creating products and saving them to repository
        Set<Attribute> attributes;

        attributes = Set.of(STRONG_LANGUAGE,VIOLENCE, ADULT_ONLY);
        createProduct("Dead Space", ACTION, 100d,attributes);
        attributes = Set.of(GUNS, STRONG_LANGUAGE, TEEN);
        createProduct("Mass Effect", SCIFI, 130d, attributes);
        attributes = Set.of(GUNS,SEX);
        createProduct("Red Dead Redemtion", STORY, 150, attributes);
        attributes = Set.of(TEEN);
        createProduct("Forza", RACES, 230, attributes);
        attributes = Set.of(GUNS,STRONG_LANGUAGE);
        createProduct("Warzone", ACTION, 50, attributes);


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

        //creating addresses and saving them to repository

        createOrder("marian@kowalski.com",OrderStatus.RECIEVED, 1L); //product_id i te max id tha will be added to products
        createOrder("oskar@hanke.com",OrderStatus.PROCESSING, 2L);
        createOrder("janina@nowak.com",OrderStatus.RECIEVED, 3L);
        createOrder("michal@tworuszka.com",OrderStatus.DELIVERED, 2L);
        createOrder("pawel@manowski.com",OrderStatus.RECIEVED, 4L);
        createOrder("kdudek@gmail.com",OrderStatus.CANCELLED, 1L);

    }

    /**
     * Method to create product object and save it in product repository
     * @param name - name of product
     * @param category - enum category of game (product)
     * @param priceNett - nett value of product (gross is calculated by multiplying nett * tax value (VAT)
     * @param attributes - set of enum values informing buyer what they can expect of the product
     */
    public void createProduct(String name, Category category, double priceNett, Set<Attribute> attributes) {
        double priceGross = priceNett * VAT;
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setPriceNett(priceNett);
        product.setAttributes(attributes);
        product.setPriceGross(priceGross);
        productRepository.save(product);
    }

    /**
     * Method to create order object and save it in order repository
     * @param email - this field is unique at user so this method searching user by it in repository
     * @param orderStatus - enum field that informs if order is completed
     * @param maxProductId - this param is upper limit for list od products to add to order
     */
    public void createOrder(String email,  OrderStatus orderStatus, long maxProductId){
        Order order = new Order();
        Set<Product> products = new HashSet<>(productRepository.findAllByIdLessThan(maxProductId));
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        order.setUser(user);
        double totalValue = products.stream()
                            .mapToDouble(Product::getPriceGross)
                            .sum();

        order.setTotalValue(totalValue);
        order.setOrderStatus(orderStatus);
        order.setProducts(products);
        orderRepository.save(order);

    }

    /**
     * Method to create user object and save it in user repository
     * @param firstName - first name of user
     * @param lastName - surname of user
     * @param email - unique user email adress
     * @param password - user's login password
     */
    public void createUser(String firstName, String lastName, String email, String password){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
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
        Adress adress = new Adress();
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        adress.setUser(user);
        adress.setCountry(country);
        adress.setStreet(street);
        adress.setState(state);
        adress.setCity(city);
        adress.setZip(zipCode);
        adressRepository.save(adress);
    }

}