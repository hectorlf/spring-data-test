package test.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.github.fakemongo.Fongo;
import com.mongodb.DB;

@Configuration
@EnableMongoRepositories(basePackages={"test.repository"})
public class MongoRepositoryConfig {

	public @Bean MongoDbFactory mongoDbFactory() throws Exception {
		return new FongoDbFactory();
	}

	public @Bean MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}
	
	private static class FongoDbFactory implements MongoDbFactory {

		private Fongo fongo = new Fongo("localhost");

		@Override
		public DB getDb() throws DataAccessException {
			return fongo.getDB("db");
		}

		@Override
		public DB getDb(String database) throws DataAccessException {
			return fongo.getDB(database);
		}

		@Override
		public PersistenceExceptionTranslator getExceptionTranslator() {
			return null;
		}
		
	}

}
