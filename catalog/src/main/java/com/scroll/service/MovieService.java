package com.scroll.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scroll.DTO.MovieDTO;

@Service
public interface MovieService {
	public MovieDTO findById(String id);

	public List<MovieDTO> findAll();

	public List<String> findActorsForMovie(String id);

	public List<MovieDTO> findByActor(String actor);

	public List<MovieDTO> findByDirector(String director);

	public List<MovieDTO> findByGenre(String genre);

	public MovieDTO findByName(String title);

	public List<MovieDTO> findByProducer(String producer);
	
	public List<MovieDTO> findByLanguage(String language);
	
	public List<MovieDTO> findByYear(String year);

}
