package com.scroll.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.scroll.pojo.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

	public List<Movie> findByDirector(String director);

	public Movie findByTitle(String title);

	public List<Movie> findByProducer(String producer);

	public List<Movie> findByLanguage(String language);

	public List<Movie> findByGenreContaining(String genre);

	public List<Movie> findByYear(String year);

	public List<Movie> findByMusic(String music);

	public List<Movie> findByEarnings(String earnings);

	public List<Movie> findByDurationLessThan(int duration);
	
	public List<Movie> findByRottenTomatoesGreaterThan(String rottenTomatoes);
	
	public List<Movie> findByImdbGreaterThan(double imdb);
	
	public List<Movie> findByRated(String rated);
	
	public List<Movie> findByActorsContaining(String actor);
}
