package capgemini.gameshop;

import capgemini.gameshop.config.DataInitializer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class GameShopApplication {


	public static void main(String[] args) {
		SpringApplication.run(GameShopApplication.class, args);
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
