package test.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;

@Configuration
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages={"test.repository.jpa"})
@EnableMongoRepositories(basePackages={"test.repository.mongo"})
@EnableNeo4jRepositories(basePackages={"test.repository.neo4j"})
public class AppConfiguration {

	private static String[] messageSourceBasenames = { "applicationResources" };

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames(messageSourceBasenames);
		ms.setFallbackToSystemLocale(false);
		return ms;
	}

}