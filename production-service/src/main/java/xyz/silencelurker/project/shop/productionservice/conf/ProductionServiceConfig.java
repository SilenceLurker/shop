package xyz.silencelurker.project.shop.productionservice.conf;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Silence_Lurker
 */
@EntityScan(basePackages = { "xyz.silencelurker.project.shop.*" })
@EnableDubbo
@Configuration
@EnableDubboConfig
@EnableJpaRepositories(basePackages = { "xyz.silencelurker.project.shop.*" })
public class ProductionServiceConfig {

}
