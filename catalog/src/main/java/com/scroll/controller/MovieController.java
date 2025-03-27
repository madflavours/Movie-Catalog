package com.scroll.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scroll.DTO.MovieDTO;
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
	public MovieDTO findById(@RequestParam String id) {
		return movieService.findById(id);
	}

	@GetMapping("/title")
	public MovieDTO findByMovieName(@RequestParam String title) {
		return movieService.findByName(title);
	}

	@GetMapping("/producer")
	public List<MovieDTO> findByProducer(@RequestParam String producer) {
		return movieService.findByProducer(producer);
	}

	@GetMapping("/language")
	public List<MovieDTO> findByLanguage(@RequestParam String language) {
		return movieService.findByLanguage(language);
	}

	@GetMapping("/director")
	public List<MovieDTO> findByDirector(@RequestParam String director) {
		return movieService.findByDirector(director);
	}

	@GetMapping("/genre")
	public List<MovieDTO> findByGenre(@RequestParam String genre) {
		log.info("Starting search by genre: {}", genre);
		return movieService.findByGenre(genre);
	}

	@GetMapping("/actors")
	public List<String> findActorsForMovie(@RequestParam String id) {
		return movieService.findActorsForMovie(id);
	}
	
	@GetMapping("/year")
	public List<MovieDTO> findByRatingRange(@RequestParam String year){
		return movieService.findByYear(year);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
