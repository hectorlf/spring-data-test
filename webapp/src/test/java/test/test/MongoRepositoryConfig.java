package test.test;

import java.util.ArrayList;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import test.model.Post;
import test.repository.PostRepository;

@Configuration
public class MongoRepositoryConfig {

	@Bean
	public PostRepository postRepository() {
		PostRepository impl = Mockito.mock(PostRepository.class);
		Mockito.when(impl.count()).thenReturn(Long.valueOf(0));
		Mockito.when(impl.findByCreationDateLessThanEqual(0)).thenReturn(new ArrayList<Post>());
		return impl;
	}

}
