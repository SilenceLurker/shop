package xyz.silencelurker.project.shop.easyshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

        configuration.addAllowedMethod(HttpMethod.GET);
        configuration.addAllowedMethod(HttpMethod.POST);
        configuration.addAllowedMethod(HttpMethod.DELETE);
        configuration.addAllowedMethod(HttpMethod.PUT);

        configuration.addAllowedOrigin("*");

        configuration.addAllowedOriginPattern("*");

        return configuration;
    }
}
