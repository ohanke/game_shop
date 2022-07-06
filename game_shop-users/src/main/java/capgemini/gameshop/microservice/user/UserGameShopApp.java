package capgemini.gameshop.microservice.user;

import capgemini.gameshop.microservice.user.config.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class UserGameShopApp {

    public static void main(String[] args) {
        SpringApplication.run(UserGameShopApp.class, args);
    }
    @Component
    @RequiredArgsConstructor
    public class AppInitializer implements ApplicationRunner {

        private final DataInitializer dataInitializer;

        @Override
        public void run(ApplicationArguments args) throws Exception {
            dataInitializer.initialize();
        }
    }
}