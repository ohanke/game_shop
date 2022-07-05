package capgemini.gameshop.microservice.order.config;



import org.modelmapper.ModelMapper;
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
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }



}
