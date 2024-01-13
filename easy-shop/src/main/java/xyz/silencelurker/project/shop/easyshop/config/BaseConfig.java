package xyz.silencelurker.project.shop.easyshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

/**
 * @author Silence_Lurker
 */
@Configuration
public class BaseConfig {

    @Bean
    public CorsConfiguration config() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(true);

        return configuration;
    }
}
