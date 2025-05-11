package com.scroll.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scroll.DTO.MovieDTO;
import com.scroll.pojo.Movie;
import com.scroll.service.MovieService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/movie")
@Slf4j
public class MovieController {
	private final MovieService movieService;
	
	BiFunction<Integer, Integer, Pageable> getPaginationSetting = (page,size) -> PageRequest.of(page, size);

	public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}

	@GetMapping("/id")
	public Movie findById(@RequestParam String id) {
		log.info("Controller - Search By ID {}", id);
		return movieService.findById(id);
	}

	@GetMapping("/title")
	public Movie findByMovieName(@RequestParam String title,
			@RequestParam(required = false) int page,
			@RequestParam(required = false) int size) {
		log.info("Controller - Search By title {}", title);
		return movieService.findByName(title, getPaginationSetting.apply(page, size));
	}

	@GetMapping("/producer")
	public PagedModel<MovieDTO> findByProducer(@RequestParam String producer,
			@RequestParam(required = false) int page,
			@RequestParam(required = false) int size) {
		log.info("Controller - Search By producer {}", producer);
		return movieService.findByProducer(producer, getPaginationSetting.apply(page, size));
	}

	@GetMapping("/language")
	public PagedModel<MovieDTO> findByLanguage(@RequestParam String language,
			@RequestParam(required = false) int page,
			@RequestParam(required = false) int size) {
		log.info("Controller - Search By language {}", language);
		return movieService.findByLanguage(language, getPaginationSetting.apply(page, size));
	}

	@GetMapping("/director")
	public List<MovieDTO> findByDirector(@RequestParam String director,
			@RequestParam(required = false) int page,
			@RequestParam(required = false) int size) {
		log.info("Controller - Search By director {}", director);
		PagedModel<MovieDTO> resultPage = movieService.findByDirector(director, getPaginationSetting.apply(page, size));
		return new ArrayList<MovieDTO>(resultPage.getContent());
	}

	@GetMapping("/genre")
	public PagedModel<MovieDTO> findByGenre(@RequestParam String genre,
			@RequestParam(required = false) int page,
			@RequestParam(required = false) int size) {
		log.info("Controller - Search By Genre {}", genre);
		return movieService.findByGenre(genre, getPaginationSetting.apply(page, size));
	}

	@GetMapping("/actors")
	public List<String> findActorsForMovie(@RequestParam String id) {
		log.info("Controller - Search By ID {}", id);
		return movieService.findActorsForMovie(id);
	}

	@GetMapping("/year")
	public PagedModel<MovieDTO> findByRatingRange(@RequestParam String year,
			@RequestParam(required = false) int page,
			@RequestParam(required = false) int size) {
		log.info("Controller - Search By year {}", year);
		return movieService.findByYear(year, getPaginationSetting.apply(page, size));
	}

	@GetMapping("/music")
	public PagedModel<MovieDTO> findByMusic(@RequestParam String music,
			@RequestParam(required = false) int page,
			@RequestParam(required = false) int size) {
		log.info("Controller - Search By Music {}", music);
		return movieService.findByMusic(music, getPaginationSetting.apply(page, size));
	}

	@GetMapping("/rated")
	public PagedModel<MovieDTO> findByMovieRating(@RequestParam String picture_rating,
			@RequestParam(required = false) int page,
			@RequestParam(required = false) int size) {
		log.info("Controller - Search By Movie Rating {}", picture_rating);
		return movieService.findByMovieRating(picture_rating, getPaginationSetting.apply(page, size));
	}

	@GetMapping("/imdb")
	public PagedModel<MovieDTO> findByImdbRating(@RequestParam double imdb,
			@RequestParam(required = false) int page,
			@RequestParam(required = false) int size) {
		log.info("Controller - Search By Imdb Rating {}", imdb);
		return movieService.findByImdbRatedOver(imdb, getPaginationSetting.apply(page, size));
	}

	@GetMapping("/rottenTomatoes")
	public PagedModel<MovieDTO> findByRottenTomatoesRating(@RequestParam String rotten_tomatoes_rating,
			@RequestParam(required = false) int page,
			@RequestParam(required = false) int size) {
		log.info("Controller - Search By Rotten Tomatoes Rating {}", rotten_tomatoes_rating);
		return movieService.findByRottenTomatoesRatedOVer(rotten_tomatoes_rating, getPaginationSetting.apply(page, size));
	}

	@GetMapping("/duration")
	public PagedModel<MovieDTO> findByDuration(@RequestParam int duration,
			@RequestParam(required = false) int page,
			@RequestParam(required = false) int size) {
		log.info("Controller - Search By Duration {}", duration);
		return movieService.findByDuration(duration, getPaginationSetting.apply(page, size));
	}

	@GetMapping("/find")
	public PagedModel<MovieDTO> wildCardSearch(@RequestParam(required = false) String title,
			@RequestParam(required = false) String actor, 
			@RequestParam(required = false) String director,
			@RequestParam(required = false) String producer, 
			@RequestParam(required = false) String music,
			@RequestParam(required = true) int page,
			@RequestParam(required = true) int size) {
		log.info("Wild Card Search - Find By Any Matching String. Title: {} "
				+ "Actor: {} Director: {} Producer: {} Music: {}", title, actor, director, producer, music);
		return movieService.wildCardSearch(title, director, producer, music, actor, getPaginationSetting.apply(page, size));
	}

}
