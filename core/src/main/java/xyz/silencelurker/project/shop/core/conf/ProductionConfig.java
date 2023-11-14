package xyz.silencelurker.project.shop.core.conf;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import xyz.silencelurker.project.shop.productionapi.service.IBrandService;
import xyz.silencelurker.project.shop.productionapi.service.IProductionService;

/**
 * @author Silence_Lurker
 */
@EnableDubbo
@Configuration
public class ProductionConfig {
    @DubboReference(version = "0.0.1-SNAPSHOT")
    IProductionService productionService;

    @DubboReference(version = "0.0.1-SNAPSHOT")
    IBrandService brandService;

    @Bean
    IProductionService productionService() {
        return productionService;
    }

    @Bean
    IBrandService brandService() {
        return brandService;
    }

}
