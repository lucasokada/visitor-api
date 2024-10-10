package br.com.unesp.visitor_api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.unesp.visitor_api.core.application.ports.out.persistence.repositories")
@TestPropertySource(locations = "classpath:./application-test.properties")
@EnableTransactionManagement
public class H2JpaConfig {
}
