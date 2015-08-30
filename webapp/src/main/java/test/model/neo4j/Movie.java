package test.model.neo4j;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Movie {

	@GraphId
	private Long id;
	private String title;
	@RelatedTo(type = "ACTS_IN", direction = Direction.INCOMING)
	private Set<Actor> cast = new HashSet<Actor>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set<Actor> getCast() {
		return cast;
	}
	public void setCast(Set<Actor> cast) {
		this.cast = cast;
	}

}