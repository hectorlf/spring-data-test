package test.test.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.model.Post;
import test.repository.PostRepository;
import test.test.MongoRepositoryConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MongoRepositoryConfig.class})
public class MongoRepositoryTests {

	@Autowired
	private PostRepository postRepository;

	@Test
	public void testCount() {
		Assert.assertTrue(postRepository.count() == 0);
	}

	public void testConstraintViolation1() {
		List<Post> results = postRepository.findByCreationDateLessThanEqual(0);
		Assert.assertNotNull(results);
		Assert.assertTrue(results.size() == 0);
	}

}
