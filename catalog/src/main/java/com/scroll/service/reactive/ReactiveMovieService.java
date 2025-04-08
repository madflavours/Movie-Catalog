package com.scroll.service.reactive;

import java.util.List;

import org.springframework.stereotype.Service;
import com.scroll.DTO.MovieDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ReactiveMovieService {

	public Mono<MovieDTO> findById(String id);

	public Flux<MovieDTO> findAll();

	public Mono<List<String>> findActorsForMovie(String id);

	public Flux<MovieDTO> findByActor(String actor);

	public Flux<MovieDTO> findByDirector(String director);

	public Flux<MovieDTO> findByGenre(String genre);

	public Mono<MovieDTO> findByName(String title);

	public Flux<MovieDTO> findByProducer(String producer);

	public Flux<MovieDTO> findByLanguage(String language);

	public Flux<MovieDTO> findByYear(String year);

	public Flux<MovieDTO> findByMusic(String music);

	public Flux<MovieDTO> findByMovieRating(String picture_ratingString);

	public Flux<MovieDTO> findByImdbRatedOver(double imdb);

	public Flux<MovieDTO> findByRottenTomatoesRatedOVer(String rotten_tomatoes_rating);

	public Flux<MovieDTO> findByDuration(int duration);

	public Flux<MovieDTO> wildCardSearch(String title, String director, String producer, String music, String actor);
}
