package test.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
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