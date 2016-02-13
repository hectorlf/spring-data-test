package test.integration;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories(basePackages={"test.repository.neo4j"})
public class Neo4jConfig extends Neo4jConfiguration {

	@Override
	public SessionFactory getSessionFactory() {
		return new SessionFactory(getConfiguration(), "test.model.neo4j");
	}

	@Bean
	public org.neo4j.ogm.config.Configuration getConfiguration() {
		org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
		config.driverConfiguration().setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver");
		return config;
	}

}
