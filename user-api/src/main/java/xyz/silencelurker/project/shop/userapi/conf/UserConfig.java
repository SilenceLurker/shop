package xyz.silencelurker.project.shop.userapi.conf;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Silence_Lurker
 */
@Configuration
@EntityScan("xyz.silencelurker.project.shop.*")
@EnableJpaRepositories
public class UserConfig {

}
