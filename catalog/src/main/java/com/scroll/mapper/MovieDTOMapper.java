package com.scroll.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.scroll.DTO.MovieDTO;
import com.scroll.pojo.Movie;

@Service
public class MovieDTOMapper implements Function<Movie, MovieDTO> {

	@Override
	public MovieDTO apply(Movie movie) {
		// Map the fields from the Movie object to the MovieDTO record
		return new MovieDTO(
				movie.getTitle(),
				movie.getWriter(),
				movie.getDirector(),
				movie.getProducer(),
				movie.getMusic(),
				movie.getLanguage(),
				movie.getYear(),
				movie.getRated(),
				movie.getGenre(),
				movie.getActors(),
				movie.getBudget(),
				movie.getEarnings(),
				movie.getSynopsis(),
				movie.getDuration(),
				movie.getImdb(),
				movie.getRottenTomatoes()
		);
	}
}