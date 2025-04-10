package com.scroll.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scroll.DTO.MovieDTO;
import com.scroll.pojo.Movie;
import com.scroll.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/movie")
@Slf4j
public class MoviePatchController {

	private final MovieService movieService;

	public MoviePatchController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}

	@PutMapping("/{id}")
	public MovieDTO updateMovie(@PathVariable String id, @RequestBody Movie movie) {
		log.info("Put Update - Update the Movie For ID: {}", id);
		return movieService.update(id, movie);
	}
	
	@PostMapping("/add")
	public MovieDTO postMethodName(@RequestBody Movie movie) {
		log.info("Start Insertion Of New Movie With Title {}", movie.getTitle());
		return movieService.add(movie);
	}
	
}
