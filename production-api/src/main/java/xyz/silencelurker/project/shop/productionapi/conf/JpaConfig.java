package xyz.silencelurker.project.shop.productionapi.conf;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Silence_Lurker
 */
@Configuration
@EntityScan({ "xyz.silencelurker.project.shop.productionapi.*" })
@EnableJpaRepositories(basePackages = { "xyz.silencelurker.production.shop.repository.*" })
public class JpaConfig {

}
