package test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.model.neo4j.Actor;
import test.model.neo4j.Movie;
import test.repository.neo4j.ActorRepository;
import test.repository.neo4j.MovieRepository;
import test.service.MovieService;

@Service("movieService")
@Transactional
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private ActorRepository actorRepository;

	@Override
	public void createTestMovie() {
		List<Actor> actors = actorRepository.findByName("test");
		Actor a = null;
		if (actors == null || actors.isEmpty()) {
			a = new Actor();
			a.setName("test");
//			a.setId(Long.valueOf(1));
			a = actorRepository.save(a);
		} else {
			a = actors.get(0);
		}
		Movie m = new Movie();
//		m.setId(Long.valueOf(System.currentTimeMillis()));
		m.setTitle("Snuff Movie part " + System.currentTimeMillis());
		m.getCast().add(a);
		movieRepository.save(m);
	}

	@Override
	public Iterable<Movie> findTestMovies() {
		Iterable<Movie> movies = movieRepository.findMoviesByActor("test");
		// workaround for transactional boundaries and node traversal
		for (Movie m : movies) {
			for (Actor a : m.getCast()) {
				a.getName();
			}
		}
		return movies;
	}

}