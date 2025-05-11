package com.scroll.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.scroll.pojo.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

	public Page<Movie> findByDirector(String director, Pageable pageable);

	public Page<Movie> findByTitle(String title, Pageable pageable);

	public Page<Movie> findByProducer(String producer, Pageable pageable);

	public Page<Movie> findByLanguage(String language, Pageable pageable);

	public Page<Movie> findByGenreContaining(String genre, Pageable pageable);

	public Page<Movie> findByYear(String year, Pageable pageable);

	public Page<Movie> findByMusic(String music, Pageable pageable);

	public Page<Movie> findByEarnings(String earnings, Pageable pageable);

	public Page<Movie> findByDurationLessThan(int duration, Pageable pageable);
	
	public Page<Movie> findByRottenTomatoesGreaterThan(String rottenTomatoes, Pageable pageable);
	
	public Page<Movie> findByImdbGreaterThan(double imdb, Pageable pageable);
	
	public Page<Movie> findByRated(String rated, Pageable pageable);
	
	public Page<Movie> findByActorsContaining(String actor, Pageable pageable);
}
