package com.scroll.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.scroll.DTO.MovieDTO;
import com.scroll.mapper.MovieDTOMapper;
import com.scroll.mapper.MovieUpdateMapper;
import com.scroll.pojo.Movie;
import com.scroll.pojo.MovieUpdateEvent;
import com.scroll.repository.MovieRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

	private final MovieRepository movieRepository;
	private final MovieDTOMapper movieDTOMapper;

	private final MovieUpdateMapper movieUpdateMapper;

	@Autowired
	private KafkaTemplate<String, MovieUpdateEvent> kafkaTemplate;

	public MovieServiceImpl(MovieRepository movieRepository, MovieDTOMapper movieDTOMapper,
			MovieUpdateMapper movieUpdateMapper) {
		super();
		this.movieRepository = movieRepository;
		this.movieDTOMapper = movieDTOMapper;
		this.movieUpdateMapper = movieUpdateMapper;
	}

	public Movie findById(String id) {
		Optional<Movie> result = movieRepository.findById(id);
		return result.orElseThrow();
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
	public PagedModel<MovieDTO> findByActor(String actor, Pageable pageable) {
		Page<Movie> pageContent = movieRepository.findByActorsContaining(actor, pageable);
		List<MovieDTO> resultList = pageContent.getContent().stream().map(movieDTOMapper).collect(Collectors.toList());
		return PagedModel.of(resultList, new PagedModel.PageMetadata(pageContent.getSize(), pageContent.getNumber(), pageContent.getTotalElements()));
	}

	@Override
	@Cacheable(value = "movies", key = "#director")
	public PagedModel<MovieDTO> findByDirector(String director, Pageable pageable) {
		Page<Movie> pageContent = movieRepository.findByDirector(director, pageable);
		List<MovieDTO> resultList = pageContent.getContent().stream().map(movieDTOMapper).collect(Collectors.toList());
		return PagedModel.of(resultList, new PagedModel.PageMetadata(pageContent.getSize(), pageContent.getNumber(), pageContent.getTotalElements()));
	}

	@Override
	@Cacheable(value = "movies", key = "#title")
	public Movie findByName(String title, Pageable pageable) {
		return movieRepository.findByTitle(title, pageable).getContent().get(0);
	}

	@Override
	@Cacheable(value = "movies", key = "#producer")
	public PagedModel<MovieDTO> findByProducer(String producer, Pageable pageable) {
		Page<Movie> pageContent = movieRepository.findByProducer(producer, pageable);
		List<MovieDTO> resultList = pageContent.getContent().stream().map(movieDTOMapper).collect(Collectors.toList());
		return PagedModel.of(resultList, new PagedModel.PageMetadata(pageContent.getSize(), pageContent.getNumber(), pageContent.getTotalElements()));
	}

	@Override
	@Cacheable(value = "movies", key = "#language")
	@CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "fallback")
	public PagedModel<MovieDTO> findByLanguage(String language, Pageable pageable) {
		log.info("Cache Miss - DB Call");
		Page<Movie> pageContent = movieRepository.findByLanguage(language, pageable);
		List<MovieDTO> resultList = pageContent.getContent().stream().map(movieDTOMapper).collect(Collectors.toList());
		return PagedModel.of(resultList, new PagedModel.PageMetadata(pageContent.getSize(), pageContent.getNumber(), pageContent.getTotalElements()));
	}

	@Override
	@Cacheable(value = "movies", key = "#genre")
	public PagedModel<MovieDTO> findByGenre(String genre, Pageable pageable) {
		log.info("Starting search by genre: {}", genre);
		Page<Movie> pageContent = movieRepository.findByGenreContaining(genre, pageable);
		List<MovieDTO> resultList = pageContent.getContent().stream().map(movieDTOMapper).collect(Collectors.toList());
		return PagedModel.of(resultList, new PagedModel.PageMetadata(pageContent.getSize(), pageContent.getNumber(), pageContent.getTotalElements()));
	}

	@Override
	@Cacheable(value = "movies", key = "#year")
	public PagedModel<MovieDTO> findByYear(String year, Pageable pageable) {
		log.info("Search By Year: {}", year);
		Page<Movie> pageContent = movieRepository.findByYear(year, pageable);
		List<MovieDTO> resultList = pageContent.getContent().stream().map(movieDTOMapper).collect(Collectors.toList());
		return PagedModel.of(resultList, new PagedModel.PageMetadata(pageContent.getSize(), pageContent.getNumber(), pageContent.getTotalElements()));
	}

	@Override
	@Cacheable(value = "movies", key = "#music")
	public PagedModel<MovieDTO> findByMusic(String music, Pageable pageable) {
		log.info("Service - Find By Music: {}", music);
		Page<Movie> pageContent = movieRepository.findByMusic(music, pageable);
		List<MovieDTO> resultList = pageContent.getContent().stream().map(movieDTOMapper).collect(Collectors.toList());
		return PagedModel.of(resultList, new PagedModel.PageMetadata(pageContent.getSize(), pageContent.getNumber(), pageContent.getTotalElements()));
	}

	@Override
	@Cacheable(value = "movies", key = "#picture_rating")
	public PagedModel<MovieDTO> findByMovieRating(String picture_rating, Pageable pageable) {
		log.info("Service - Find By Movie Rating: {}", picture_rating);
		Page<Movie> pageContent = movieRepository.findByRated(picture_rating, pageable);
		List<MovieDTO> resultList = pageContent.getContent().stream().map(movieDTOMapper).collect(Collectors.toList());
		return PagedModel.of(resultList, new PagedModel.PageMetadata(pageContent.getSize(), pageContent.getNumber(), pageContent.getTotalElements()));
	}

	@Override
	@Cacheable(value = "movies", key = "#imdb")
	public PagedModel<MovieDTO> findByImdbRatedOver(double imdb, Pageable pageable) {
		log.info("Service - Find By IMDb Rating: {} And Above", imdb);
		Page<Movie> pageContent = movieRepository.findByImdbGreaterThan(imdb, pageable);
		List<MovieDTO> resultList = pageContent.getContent().stream().map(movieDTOMapper).collect(Collectors.toList());
		return PagedModel.of(resultList, new PagedModel.PageMetadata(pageContent.getSize(), pageContent.getNumber(), pageContent.getTotalElements()));
	}

	@Override
	@Cacheable(value = "movies", key = "#rotten_tomatoes_rating")
	public PagedModel<MovieDTO> findByRottenTomatoesRatedOVer(String rotten_tomatoes_rating, Pageable pageable) {
		log.info("Service - Find By Rotten Tomatoes Rating: {} And Above", rotten_tomatoes_rating);
		Page<Movie> pageContent = movieRepository.findByRottenTomatoesGreaterThan(rotten_tomatoes_rating, pageable);
		List<MovieDTO> resultList = pageContent.getContent().stream().map(movieDTOMapper).collect(Collectors.toList());
		return PagedModel.of(resultList, new PagedModel.PageMetadata(pageContent.getSize(), pageContent.getNumber(), pageContent.getTotalElements()));
	}

	@Override
	@Cacheable(value = "movies", key = "#duration")
	public PagedModel<MovieDTO> findByDuration(int duration, Pageable pageable) {
		log.info("Service - Find Shorter Movies By Duration: {} And Below", duration);
		Page<Movie> pageContent = movieRepository.findByDurationLessThan(duration, pageable);
		List<MovieDTO> resultList = pageContent.getContent().stream().map(movieDTOMapper).collect(Collectors.toList());
		return PagedModel.of(resultList, new PagedModel.PageMetadata(pageContent.getSize(), pageContent.getNumber(), pageContent.getTotalElements()));
	}

	@Override
	@Cacheable(value = "movies", key = "T(java.util.Objects).hash(#title, #director, #producer, #music, #actor)")
	public PagedModel<MovieDTO> wildCardSearch(String title, String director, String producer, String music, String actor, Pageable pageable) {
		List<Movie> totalMovieList = new ArrayList<Movie>();

		if (StringUtils.isNotBlank(title)) {
			log.info("Wild Card Search: Look up by Title: {}", title);
			totalMovieList.addAll(movieRepository.findByTitle(title, pageable).getContent());
		}

		if (StringUtils.isNotBlank(director)) {
			log.info("Wild Card Search: Look up by Director: {}", director);
			totalMovieList.addAll(movieRepository.findByDirector(director, pageable).getContent());
		}
		if (StringUtils.isNotBlank(producer)) {
			log.info("Wild Card Search: Look up by Producer: {}", producer);
			totalMovieList.addAll(movieRepository.findByProducer(producer, pageable).getContent());

		}
		if (StringUtils.isNotBlank(music)) {
			log.info("Wild Card Search: Look up by Music: {}", music);
			totalMovieList.addAll(movieRepository.findByMusic(music, pageable).getContent());
		}

		if (StringUtils.isNotBlank(actor)) {
			log.info("Wild Card Search: Look up by Actor: {}", actor);
			totalMovieList.addAll(movieRepository.findByActorsContaining(actor, pageable).getContent());
		}

		List<MovieDTO> finalConvertedList = totalMovieList.stream()
				.collect(Collectors.toMap(Movie::getId, movie -> movie, (existing, replacement) -> existing)).values()
				.stream().map(movieDTOMapper).collect(Collectors.toList());
		
		return PagedModel.of(finalConvertedList, new PagedModel.PageMetadata(pageable.getPageSize(), pageable.getPageNumber(), finalConvertedList.size()));
	}

	@Override
	public MovieDTO update(String id, Movie movie) {

		Optional<Movie> result = movieRepository.findById(id);
		if (result.isPresent()) {
			Movie updateMovie = movieUpdateMapper.mapChangesFields(result.get(), movie);
			movieRepository.save(updateMovie);

			MovieUpdateEvent event = new MovieUpdateEvent(movie, updateMovie);
			kafkaTemplate.send("movie-update-events", event);

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

	public String fallback(Throwable t) {
		return "Fallback response due to: " + t.getMessage();
	}

}
