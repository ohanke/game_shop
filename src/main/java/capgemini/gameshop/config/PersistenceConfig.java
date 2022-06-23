package capgemini.gameshop.config;


import capgemini.gameshop.repository.AdressRepository;
import capgemini.gameshop.repository.OrderRepository;
import capgemini.gameshop.repository.ProductRepository;
import capgemini.gameshop.repository.UserRepository;
import capgemini.gameshop.service.UserService;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Configuration class
 */
@Configuration
@EnableJpaAuditing
public class PersistenceConfig {

    @Bean
    public DataInitializer dataInitializer(ProductRepository productRepository, OrderRepository orderRepository, AdressRepository adressRepository, UserRepository userRepository) {
        return new DataInitializer(productRepository, orderRepository, adressRepository, userRepository);
    }

    @Bean
    public Mapper mapper() {
        return DozerBeanMapperBuilder.buildDefault();
    }



}
