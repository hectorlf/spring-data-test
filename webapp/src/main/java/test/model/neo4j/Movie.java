package test.model.neo4j;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Movie {

	@GraphId
	private Long id;
	private String title;
	@Relationship(type = "ACTS_IN", direction = Relationship.INCOMING)
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