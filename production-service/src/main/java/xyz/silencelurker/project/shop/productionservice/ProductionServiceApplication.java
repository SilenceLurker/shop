package xyz.silencelurker.project.shop.productionservice;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Silence_Lurker
 */
@EnableDubbo
@SpringBootApplication
public class ProductionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductionServiceApplication.class, args);
	}

}
