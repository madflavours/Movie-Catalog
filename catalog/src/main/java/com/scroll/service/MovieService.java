package com.scroll.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.scroll.DTO.MovieDTO;
import com.scroll.pojo.Movie;

@Service
public interface MovieService {
	public Movie findById(String id);

	public List<MovieDTO> findAll();

	public List<String> findActorsForMovie(String id);

	public List<MovieDTO> findByActor(String actor, Pageable pageable);

	public PagedModel<MovieDTO> findByDirector(String director, Pageable pageable);

	public PagedModel<MovieDTO> findByGenre(String genre, Pageable pageable);

	public Movie findByName(String title, Pageable pageable);

	public PagedModel<MovieDTO> findByProducer(String producer, Pageable pageable);

	public PagedModel<MovieDTO> findByLanguage(String language, Pageable pageable);

	public PagedModel<MovieDTO> findByYear(String year, Pageable pageable);

	public PagedModel<MovieDTO> findByMusic(String music, Pageable pageable);

	public PagedModel<MovieDTO> findByMovieRating(String picture_ratingString, Pageable pageable);

	public PagedModel<MovieDTO> findByImdbRatedOver(double imdb, Pageable pageable);

	public PagedModel<MovieDTO> findByRottenTomatoesRatedOVer(String rotten_tomatoes_rating, Pageable pageable);

	public PagedModel<MovieDTO> findByDuration(int duration, Pageable pageable);

	public PagedModel<MovieDTO> wildCardSearch(String title, String director, String producer, String music, String actor, Pageable pageable);

	public MovieDTO update(String id, Movie movie);

	public MovieDTO add(Movie movie);

}
