package test.test;

import java.util.ArrayList;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import test.model.jpa.Message;
import test.model.mongo.Post;
import test.model.neo4j.Movie;
import test.service.MessageService;
import test.service.MovieService;
import test.service.PostService;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"test.controller"})
public class MvcConfig {

	private static String[] messageSourceBasenames = { "applicationResources" };

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames(messageSourceBasenames);
		ms.setFallbackToSystemLocale(false);
		return ms;
	}

	@Bean
	public ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix("/WEB-INF/thymeleaf");
		resolver.setTemplateMode("HTML5");
		return resolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}

	@Bean
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setOrder(1);
		viewResolver.setViewNames(new String[] {"*.html"});
		return viewResolver;
	}

	// service mocks

	@Bean
	public MessageService messageService() {
		MessageService impl = Mockito.mock(MessageService.class);
		Mockito.when(impl.findImportantMessages()).thenReturn(new ArrayList<Message>(0));
		return impl;
	}
	
	@Bean
	public PostService postService() {
		PostService impl = Mockito.mock(PostService.class);
		Mockito.when(impl.findLatestPosts()).thenReturn(new ArrayList<Post>(0));
		return impl;
	}

	@Bean
	public MovieService movieService() {
		MovieService impl = Mockito.mock(MovieService.class);
		Mockito.when(impl.findTestMovies()).thenReturn(new ArrayList<Movie>(0));
		return impl;
	}

}
