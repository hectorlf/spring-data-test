package test.integration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;

import org.neo4j.harness.internal.InProcessServerBuilder;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;

@Configuration
@EnableNeo4jRepositories(basePackages={"test.repository.neo4j"})
public class Neo4jConfig extends Neo4jConfiguration {

	@Override
	public SessionFactory getSessionFactory() {
		return new SessionFactory("test.model.neo4j");
	}

	@Override
	public Neo4jServer neo4jServer() {
		InProcessServerBuilder serverBuilder;
		try {
			serverBuilder = new InProcessServerBuilder(Files.createTempDirectory("spring-data-test", new FileAttribute[]{}).toFile());
			return new RemoteServer(serverBuilder.newServer().httpURI().toString());
		} catch (IOException e) {
			throw new RuntimeException("Failed creating temp directory: " + e.getMessage());
		}
	}

}
