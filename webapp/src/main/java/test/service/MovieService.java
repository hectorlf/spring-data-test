package test.service;

import test.model.neo4j.Movie;

public interface MovieService {

	void createTestMovie();

	Iterable<Movie> findTestMovies();

}
