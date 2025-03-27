package com.scroll.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scroll.DTO.MovieDTO;
import com.scroll.repository.MovieRepository;
import com.scroll.repository.reactive.MovieReactiveRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService{
	
	private final MovieRepository movieRepository;
	
	@Autowired
	private final MovieReactiveRepository reactiveRepository;
	
	public MovieServiceImpl(MovieRepository movieRepository, MovieReactiveRepository reactiveRepository) {
		super();
		this.movieRepository = movieRepository;
		this.reactiveRepository = reactiveRepository;
	}

	public MovieDTO findById(String id) {
		Optional<MovieDTO> result = movieRepository.findById(id);
		log.info("Reactive Result: {}", reactiveRepository.count().block());
		return result.get();
	}

	@Override
	public List<MovieDTO> findAll() {
		return movieRepository.findAll();
	}

	@Override
	public List<String> findActorsForMovie(String id) {
		MovieDTO movie = this.findById(id);
		return Arrays.asList(movie.getActors());
	}

	@Override
	public List<MovieDTO> findByActor(String actor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MovieDTO> findByDirector(String director) {
		return movieRepository.findByDirector(director);
	}

	@Override
	public MovieDTO findByName(String title) {
		return movieRepository.findByTitle(title);
	}

	@Override
	public List<MovieDTO> findByProducer(String producer) {
		return movieRepository.findByProducer(producer);
	}

	@Override
	public List<MovieDTO> findByLanguage(String language) {
		return movieRepository.findByLanguage(language);
	}

	@Override
	public List<MovieDTO> findByGenre(String genre) {
		log.info("Starting search by genre: {}", genre);
		return movieRepository.findByGenreContaining(genre);
	}

	@Override
	public List<MovieDTO> findByYear(String year) {
		log.info("Search By Year: {}", year);
		return movieRepository.findByYear(year);
	}
}
