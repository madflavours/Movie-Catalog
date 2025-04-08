package com.scroll.controller;

import java.util.List;

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
	public Movie findByMovieName(@RequestParam String title) {
		log.info("Controller - Search By title {}", title);
		return movieService.findByName(title);
	}

	@GetMapping("/producer")
	public List<MovieDTO> findByProducer(@RequestParam String producer) {
		log.info("Controller - Search By producer {}", producer);
		return movieService.findByProducer(producer);
	}

	@GetMapping("/language")
	public List<MovieDTO> findByLanguage(@RequestParam String language) {
		log.info("Controller - Search By language {}", language);
		return movieService.findByLanguage(language);
	}

	@GetMapping("/director")
	public List<MovieDTO> findByDirector(@RequestParam String director) {
		log.info("Controller - Search By director {}", director);
		return movieService.findByDirector(director);
	}

	@GetMapping("/genre")
	public List<MovieDTO> findByGenre(@RequestParam String genre) {
		log.info("Controller - Search By Genre {}", genre);
		return movieService.findByGenre(genre);
	}

	@GetMapping("/actors")
	public List<String> findActorsForMovie(@RequestParam String id) {
		log.info("Controller - Search By ID {}", id);
		return movieService.findActorsForMovie(id);
	}

	@GetMapping("/year")
	public List<MovieDTO> findByRatingRange(@RequestParam String year) {
		log.info("Controller - Search By year {}", year);
		return movieService.findByYear(year);
	}

	@GetMapping("/music")
	public List<MovieDTO> findByMusic(@RequestParam String music) {
		log.info("Controller - Search By Music {}", music);
		return movieService.findByMusic(music);
	}

	@GetMapping("/rated")
	public List<MovieDTO> findByMovieRating(@RequestParam String picture_rating) {
		log.info("Controller - Search By Movie Rating {}", picture_rating);
		return movieService.findByMovieRating(picture_rating);
	}

	@GetMapping("/imdb")
	public List<MovieDTO> findByImdbRating(@RequestParam double imdb) {
		log.info("Controller - Search By Imdb Rating {}", imdb);
		return movieService.findByImdbRatedOver(imdb);
	}

	@GetMapping("/rottenTomatoes")
	public List<MovieDTO> findByRottenTomatoesRating(@RequestParam String rotten_tomatoes_rating) {
		log.info("Controller - Search By Rotten Tomatoes Rating {}", rotten_tomatoes_rating);
		return movieService.findByRottenTomatoesRatedOVer(rotten_tomatoes_rating);
	}

	@GetMapping("/duration")
	public List<MovieDTO> findByDuration(@RequestParam int duration) {
		log.info("Controller - Search By Duration {}", duration);
		return movieService.findByDuration(duration);
	}

	@GetMapping("/find")
	public List<MovieDTO> wildCardSearch(@RequestParam(required = false) String title,
			@RequestParam(required = false) String actor, @RequestParam(required = false) String director,
			@RequestParam(required = false) String producer, @RequestParam(required = false) String music) {
		log.info("Wild Card Search - Find By Any Matching String. Title: {} "
				+ "Actor: {} Director: {} Producer: {} Music: {}", title, actor, director, producer, music);
		return movieService.wildCardSearch(title, director, producer, music, actor);
	}

}
