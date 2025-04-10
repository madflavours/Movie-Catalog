package com.scroll.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.scroll.DTO.MovieDTO;
import com.scroll.mapper.MovieDTOMapper;
import com.scroll.mapper.MovieUpdateMapper;
import com.scroll.pojo.Movie;
import com.scroll.repository.MovieRepository;
import com.scroll.repository.reactive.MovieReactiveRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

	private final MovieRepository movieRepository;
	private final MovieDTOMapper movieDTOMapper;

	private final MovieUpdateMapper movieUpdateMapper;

	@Autowired
	private final MovieReactiveRepository reactiveRepository;

	public MovieServiceImpl(MovieRepository movieRepository, MovieDTOMapper movieDTOMapper,
			MovieUpdateMapper movieUpdateMapper, MovieReactiveRepository reactiveRepository) {
		super();
		this.movieRepository = movieRepository;
		this.movieDTOMapper = movieDTOMapper;
		this.movieUpdateMapper = movieUpdateMapper;
		this.reactiveRepository = reactiveRepository;
	}

	public Movie findById(String id) {
		Optional<Movie> result = movieRepository.findById(id);
		log.info("Reactive Result: {}", reactiveRepository.count().block());
		return result.get();
	}

	@Override
	public List<MovieDTO> findAll() {
		log.info("Finding all Movies");
		return movieRepository.findAll().stream().map(movieDTOMapper).collect(Collectors.toList());
	}

	@Override
	@Cacheable(value = "movies", key = "#id")
	public List<String> findActorsForMovie(String id) {
		Movie movie = this.findById(id);
		return movie.getActors();
	}

	@Override
	public List<MovieDTO> findByActor(String actor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Cacheable(value = "movies", key = "#director")
	public List<MovieDTO> findByDirector(String director) {
		return movieRepository.findByDirector(director).stream().map(movieDTOMapper).collect(Collectors.toList());
	}

	@Override
	@Cacheable(value = "movies", key = "#title")
	public Movie findByName(String title) {
		return movieRepository.findByTitle(title);
	}

	@Override
	@Cacheable(value = "movies", key = "#producer")
	public List<MovieDTO> findByProducer(String producer) {
		return movieRepository.findByProducer(producer).stream().map(movieDTOMapper).collect(Collectors.toList());
	}

	@Override
	@Cacheable(value = "movies", key = "#language")
	public List<MovieDTO> findByLanguage(String language) {
		log.info("Cache Miss - DB Call");
		return movieRepository.findByLanguage(language).stream().map(movieDTOMapper).collect(Collectors.toList());
	}

	@Override
	@Cacheable(value = "movies", key = "#genre")
	public List<MovieDTO> findByGenre(String genre) {
		log.info("Starting search by genre: {}", genre);
		return movieRepository.findByGenreContaining(genre).stream().map(movieDTOMapper).collect(Collectors.toList());
	}

	@Override
	@Cacheable(value = "movies", key = "#year")
	public List<MovieDTO> findByYear(String year) {
		log.info("Search By Year: {}", year);
		return movieRepository.findByYear(year).stream().map(movieDTOMapper).collect(Collectors.toList());
	}

	@Override
	@Cacheable(value = "movies", key = "#music")
	public List<MovieDTO> findByMusic(String music) {
		log.info("Service - Find By Music: {}", music);
		return movieRepository.findByMusic(music).stream().map(movieDTOMapper).collect(Collectors.toList());
	}

	@Override
	@Cacheable(value = "movies", key = "#picture_rating")
	public List<MovieDTO> findByMovieRating(String picture_rating) {
		log.info("Service - Find By Movie Rating: {}", picture_rating);
		return movieRepository.findByRated(picture_rating).stream().map(movieDTOMapper).collect(Collectors.toList());
	}

	@Override
	@Cacheable(value = "movies", key = "#imdb")
	public List<MovieDTO> findByImdbRatedOver(double imdb) {
		log.info("Service - Find By IMDb Rating: {} And Above", imdb);
		return movieRepository.findByImdbGreaterThan(imdb).stream().map(movieDTOMapper).collect(Collectors.toList());
	}

	@Override
	@Cacheable(value = "movies", key = "#rotten_tomatoes_rating")
	public List<MovieDTO> findByRottenTomatoesRatedOVer(String rotten_tomatoes_rating) {
		log.info("Service - Find By Rotten Tomatoes Rating: {} And Above", rotten_tomatoes_rating);
		return movieRepository.findByRottenTomatoesGreaterThan(rotten_tomatoes_rating).stream().map(movieDTOMapper)
				.collect(Collectors.toList());
	}

	@Override
	@Cacheable(value = "movies", key = "#duration")
	public List<MovieDTO> findByDuration(int duration) {
		log.info("Service - Find Shorter Movies By Duration: {} And Below", duration);
		return movieRepository.findByDurationLessThan(duration).stream().map(movieDTOMapper)
				.collect(Collectors.toList());
	}

	@Override
	public List<MovieDTO> wildCardSearch(String title, String director, String producer, String music, String actor) {
		List<Movie> totalMovieList = new ArrayList<Movie>();

		if (StringUtils.isNotBlank(title)) {
			log.info("Wild Card Search: Look up by Title: {}", title);
			totalMovieList.add(movieRepository.findByTitle(title));
		}

		if (StringUtils.isNotBlank(director)) {
			log.info("Wild Card Search: Look up by Director: {}", director);
			totalMovieList.addAll(movieRepository.findByDirector(director));
		}
		if (StringUtils.isNotBlank(producer)) {
			log.info("Wild Card Search: Look up by Producer: {}", producer);
			totalMovieList.addAll(movieRepository.findByProducer(producer));

		}
		if (StringUtils.isNotBlank(music)) {
			log.info("Wild Card Search: Look up by Music: {}", music);
			totalMovieList.addAll(movieRepository.findByMusic(music));
		}

		if (StringUtils.isNotBlank(actor)) {
			log.info("Wild Card Search: Look up by Actor: {}", actor);
			totalMovieList.addAll(movieRepository.findByActorsContaining(actor));
		}

		return totalMovieList.stream()
				.collect(Collectors.toMap(Movie::getId, movie -> movie, (existing, replacement) -> existing)).values()
				.stream().map(movieDTOMapper).collect(Collectors.toList());
	}

	@Override
	public MovieDTO update(String id, Movie movie) {

		Optional<Movie> result = movieRepository.findById(id);
		if (result.isPresent()) {
			Movie updateMovie = movieUpdateMapper.mapChangesFields(result.get(), movie);
			movieRepository.save(updateMovie);
			return movieDTOMapper.apply(updateMovie);
		}
		return null;

	}

	@Override
	public MovieDTO add(Movie movie) {
		log.info("Initiating Service Work to Insert Movie {}", movie.getTitle());

		try {
			movieRepository.save(movie);
		} catch (Exception e) {
			log.error("Problem Storing New Movie {}: {}", movie.getTitle(), e.getLocalizedMessage());
		}
		movieRepository.save(movie);
		return movieDTOMapper.apply(movie);
	}

}
