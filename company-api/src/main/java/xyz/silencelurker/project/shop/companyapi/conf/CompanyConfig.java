package xyz.silencelurker.project.shop.companyapi.conf;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Silence_Lurker
 */
@Configuration
@EntityScan("xyz.silencelurker.project.shop.companyapi.entity.*")
@EnableJpaRepositories("xyz.silencelurker.project.shop.*")
public class CompanyConfig {

}
