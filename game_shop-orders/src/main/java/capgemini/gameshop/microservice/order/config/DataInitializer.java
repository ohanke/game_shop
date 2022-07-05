package capgemini.gameshop.microservice.order.config;


import capgemini.gameshop.microservice.order.model.*;
import capgemini.gameshop.microservice.order.repository.OrderRepository;
import capgemini.gameshop.microservice.order.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static capgemini.gameshop.microservice.order.model.Attribute.*;
import static capgemini.gameshop.microservice.order.model.Category.*;


/**
 * This object initialize data for database for testing porpoise
 */
@Component
@AllArgsConstructor
@Transactional
public class DataInitializer {

    private final static double VAT = 1.23d; //nett to gross parameter (based on polish tax value of 23%)
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    /**
     * Data Initializing method.
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


        //creating orders and saving them to repository

        createOrder("marian@kowalski.com", OrderStatus.NEW, 2L, 1L); //product_id i te max id tha will be added to products
        createOrder("oskar@hanke.com",OrderStatus.PROCESSING, 2L, 2L);
        createOrder("janina@nowak.com",OrderStatus.NEW, 3L, 2L);
        createOrder("michal@tworuszka.com",OrderStatus.COMPLETED, 2L, 4L);
        createOrder("pawel@manowski.com",OrderStatus.NEW, 4L, 3L);
        createOrder("kdudek@gmail.com",OrderStatus.CANCELLED, 2L, 5L);

    }

    /**
     * Method to create product object and save it in product repository
     * @param name - name of product
     * @param category - enum category of game (product)
     * @param price - value of product
     * @param attributes - set of enum values informing buyer what they can expect of the product
     */
    public void createProduct(String name, Category category, double price, Set<Attribute> attributes) {
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setAttributes(attributes);
        productRepository.save(product);
    }

    /**
     * Method to create order object and save it in order repository
     * @param email - this field is unique at user so this method searching user by it in repository
     * @param orderStatus - enum field that informs if order is completed
     * @param maxProductId - this param is upper limit for list od products to add to order
     */
    public void createOrder(String email,  OrderStatus orderStatus, Long maxProductId, Long userId){
        Order order = new Order();
        Set<Product> products = new HashSet<>(productRepository.findAllByIdLessThan(maxProductId));
        order.setUserId(userId);
        double totalValue = products.stream()
                            .mapToDouble(Product::getPrice)
                            .sum();

        order.setTotalValue(totalValue);
        order.setOrderStatus(orderStatus);
        order.setProducts(products);
        orderRepository.save(order);

    }

}
