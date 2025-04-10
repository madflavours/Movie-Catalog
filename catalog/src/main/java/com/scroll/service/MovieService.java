package com.scroll.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scroll.DTO.MovieDTO;
import com.scroll.pojo.Movie;

@Service
public interface MovieService {
	public Movie findById(String id);

	public List<MovieDTO> findAll();

	public List<String> findActorsForMovie(String id);

	public List<MovieDTO> findByActor(String actor);

	public List<MovieDTO> findByDirector(String director);

	public List<MovieDTO> findByGenre(String genre);

	public Movie findByName(String title);

	public List<MovieDTO> findByProducer(String producer);
	
	public List<MovieDTO> findByLanguage(String language);
	
	public List<MovieDTO> findByYear(String year);
	
	public List<MovieDTO> findByMusic(String music);
	
	public List<MovieDTO> findByMovieRating(String picture_ratingString);
	
	public List<MovieDTO> findByImdbRatedOver(double imdb);
	
	public List<MovieDTO> findByRottenTomatoesRatedOVer(String rotten_tomatoes_rating);
	
	public List<MovieDTO> findByDuration(int duration);
	
	public List<MovieDTO> wildCardSearch(String title, String director, String producer, String music, String actor);
	
	public MovieDTO update(String id, Movie movie);
	
	public MovieDTO add(Movie movie);

}
