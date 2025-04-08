package com.scroll.repository.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.scroll.pojo.Movie;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface MovieReactiveRepository extends ReactiveMongoRepository<Movie, String>{

	public Flux<Movie> findByDirector(String director);

	public Mono<Movie> findByTitle(String title);

	public Flux<Movie> findByProducer(String producer);

	public Flux<Movie> findByLanguage(String language);

	public Flux<Movie> findByGenreContaining(String genre);

	public Flux<Movie> findByYear(String year);

	public Flux<Movie> findByMusic(String music);

	public Flux<Movie> findByEarnings(String earnings);

	public Flux<Movie> findByDurationLessThan(int duration);
	
	public Flux<Movie> findByRottenTomatoesGreaterThan(String rottenTomatoes);
	
	public Flux<Movie> findByImdbGreaterThan(double imdb);
	
	public Flux<Movie> findByRated(String rated);
	
	public Flux<Movie> findByActorsContaining(String actor);
}
