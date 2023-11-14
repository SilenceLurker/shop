package xyz.silencelurker.project.shop.productionapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Silence_Lurker
 */
@EnableAutoConfiguration
@EnableJpaRepositories
@SpringBootApplication
public class ProductionApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductionApiApplication.class, args);
	}

}
