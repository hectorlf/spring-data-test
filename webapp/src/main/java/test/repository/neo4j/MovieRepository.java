package test.repository.neo4j;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import test.model.neo4j.Movie;

@Repository
public interface MovieRepository extends GraphRepository<Movie> {

	List<Movie> findByTitle(String title);
	
	@Query("match (movie:Movie)<-[:ACTS_IN]-(actor:Actor) where actor.name = {0} return movie")
	Iterable<Movie> findMoviesByActor(String name);

}