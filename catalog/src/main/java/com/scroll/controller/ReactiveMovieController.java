package com.scroll.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scroll.DTO.MovieDTO;
import com.scroll.pojo.Movie;
import com.scroll.service.reactive.ReactiveMovieService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive/api/movie")
@Slf4j
public class ReactiveMovieController {

	private final ReactiveMovieService reactiveMovieService;

	public ReactiveMovieController(ReactiveMovieService reactiveMovieService) {
		super();
		this.reactiveMovieService = reactiveMovieService;
	}

	@GetMapping("/id")
	public Mono<MovieDTO> findById(@RequestParam String id) {
		log.info("Controller - Search By ID {}", id);
		return reactiveMovieService.findById(id);
	}

	@GetMapping("/title")
	public Mono<MovieDTO> findByMovieName(@RequestParam String title) {
		log.info("Controller - Search By title {}", title);
		return reactiveMovieService.findByName(title);
	}

	@GetMapping("/producer")
	public Flux<MovieDTO> findByProducer(@RequestParam String producer) {
		log.info("Controller - Search By producer {}", producer);
		return reactiveMovieService.findByProducer(producer);
	}

	@GetMapping("/language")
	public Flux<MovieDTO> findByLanguage(@RequestParam String language) {
		log.info("Controller - Search By language {}", language);
		return reactiveMovieService.findByLanguage(language);
	}

	@GetMapping("/director")
	public Flux<MovieDTO> findByDirector(@RequestParam String director) {
		log.info("Controller - Search By director {}", director);
		return reactiveMovieService.findByDirector(director);
	}

	@GetMapping("/genre")
	public Flux<MovieDTO> findByGenre(@RequestParam String genre) {
		log.info("Controller - Search By Genre {}", genre);
		return reactiveMovieService.findByGenre(genre);
	}

	@GetMapping("/actors")
	public Mono<List<String>> findActorsForMovie(@RequestParam String id) {
		log.info("Controller - Search By ID {}", id);
		return reactiveMovieService.findActorsForMovie(id);
	}

	@GetMapping("/year")
	public Flux<MovieDTO> findByRatingRange(@RequestParam String year) {
		log.info("Controller - Search By year {}", year);
		return reactiveMovieService.findByYear(year);
	}

	@GetMapping("/music")
	public Flux<MovieDTO> findByMusic(@RequestParam String music) {
		log.info("Controller - Search By Music {}", music);
		return reactiveMovieService.findByMusic(music);
	}

	@GetMapping("/rated")
	public Flux<MovieDTO> findByMovieRating(@RequestParam String picture_rating) {
		log.info("Controller - Search By Movie Rating {}", picture_rating);
		return reactiveMovieService.findByMovieRating(picture_rating);
	}

	@GetMapping("/imdb")
	public Flux<MovieDTO> findByImdbRating(@RequestParam double imdb) {
		log.info("Controller - Search By Imdb Rating {}", imdb);
		return reactiveMovieService.findByImdbRatedOver(imdb);
	}

	@GetMapping("/rottenTomatoes")
	public Flux<MovieDTO> findByRottenTomatoesRating(@RequestParam String rotten_tomatoes_rating) {
		log.info("Controller - Search By Rotten Tomatoes Rating {}", rotten_tomatoes_rating);
		return reactiveMovieService.findByRottenTomatoesRatedOVer(rotten_tomatoes_rating);
	}

	@GetMapping("/duration")
	public Flux<MovieDTO> findByDuration(@RequestParam int duration) {
		log.info("Controller - Search By Duration {}", duration);
		return reactiveMovieService.findByDuration(duration);
	}

	@GetMapping("/find")
	public Flux<MovieDTO> wildCardSearch(@RequestParam(required = false) String title,
			@RequestParam(required = false) String actor, @RequestParam(required = false) String director,
			@RequestParam(required = false) String producer, @RequestParam(required = false) String music) {
		log.info("Wild Card Search - Find By Any Matching String. Title: {} "
				+ "Actor: {} Director: {} Producer: {} Music: {}", title, actor, director, producer, music);
		return reactiveMovieService.wildCardSearch(title, director, producer, music, actor);
	}
	
	@PutMapping("/{id}")
	public Mono<MovieDTO> updateMovie(@PathVariable String id, @RequestBody Movie movie) {
		log.info("Put Update - Update the Movie For ID: {}", id);
		return reactiveMovieService.update(id, movie);
	}
	
	@PostMapping("/add")
	public Mono<MovieDTO> add(@RequestBody Movie movie) {
		log.info("Start Insertion Of New Movie With Title {}", movie.getTitle());
		return reactiveMovieService.add(movie);
	}
}
