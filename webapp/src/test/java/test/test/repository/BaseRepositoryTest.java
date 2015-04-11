package test.test.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import test.test.DBConfig;
import test.test.RepositoryConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DBConfig.class, RepositoryConfig.class})
@Transactional
public abstract class BaseRepositoryTest {

	@Before
	public void setup() {
	}

}
