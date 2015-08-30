package test.repository.neo4j;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import test.model.neo4j.Actor;

@Repository
public interface ActorRepository extends GraphRepository<Actor> {

	List<Actor> findByName(String name);

}