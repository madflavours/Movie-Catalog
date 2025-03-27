package com.scroll.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.scroll.DTO.MovieDTO;

@Repository
public interface MovieRepository extends MongoRepository<MovieDTO, String>{

	public List<MovieDTO> findByDirector(String director);

	public MovieDTO findByTitle(String title);

	public List<MovieDTO> findByProducer(String producer);
	
	public List<MovieDTO> findByLanguage(String language);
	
	public List<MovieDTO> findByGenreContaining(String genre);
	
	public List<MovieDTO> findByYear(String year);
}
