package com.scroll.service.reactive;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.scroll.DTO.MovieDTO;
import com.scroll.mapper.MovieDTOMapper;
import com.scroll.mapper.MovieUpdateMapper;
import com.scroll.pojo.Movie;
import com.scroll.repository.reactive.MovieReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ReactiveMovieServiceImpl implements ReactiveMovieService {

	private final MovieReactiveRepository movieReactiveRepository;
	private final MovieDTOMapper movieDTOMapper;
	private final MovieUpdateMapper movieUpdateMapper;

	public ReactiveMovieServiceImpl(MovieReactiveRepository movieReactiveRepository, MovieDTOMapper movieDTOMapper,
			MovieUpdateMapper movieUpdateMapper) {
		super();
		this.movieReactiveRepository = movieReactiveRepository;
		this.movieDTOMapper = movieDTOMapper;
		this.movieUpdateMapper = movieUpdateMapper;
	}

	public Mono<MovieDTO> findById(String id) {
		log.info("Reactive Result: {}", movieReactiveRepository.count().block());
		return movieReactiveRepository.findById(id).map(movieDTOMapper);
	}

	@Override
	public Flux<MovieDTO> findAll() {
		log.info("Finding all Movies");
		return movieReactiveRepository.findAll().map(movieDTOMapper);
	}

	@Override
	@Cacheable(value = "movies", key = "#id")
	public Mono<List<String>> findActorsForMovie(String id) {
		return this.findById(id).map(MovieDTO::actors);
	}

	@Override
	public Flux<MovieDTO> findByActor(String actor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Cacheable(value = "movies", key = "#director")
	public Flux<MovieDTO> findByDirector(String director) {
		return movieReactiveRepository.findByDirector(director).map(movieDTOMapper);
	}

	@Override
	@Cacheable(value = "movies", key = "#title")
	public Mono<MovieDTO> findByName(String title) {
		Mono<MovieDTO> result = movieReactiveRepository.findByTitle(title).map(movieDTOMapper);
		return result;
	}

	@Override
	@Cacheable(value = "movies", key = "#producer")
	public Flux<MovieDTO> findByProducer(String producer) {
		return movieReactiveRepository.findByProducer(producer).map(movieDTOMapper);
	}

	@Override
	@Cacheable(value = "movies", key = "#language")
	public Flux<MovieDTO> findByLanguage(String language) {
		log.info("Cache Miss - DB Call");
		return movieReactiveRepository.findByLanguage(language).map(movieDTOMapper);
	}

	@Override
	@Cacheable(value = "movies", key = "#genre")
	public Flux<MovieDTO> findByGenre(String genre) {
		log.info("Starting search by genre: {}", genre);
		return movieReactiveRepository.findByGenreContaining(genre).map(movieDTOMapper);
	}

	@Override
	@Cacheable(value = "movies", key = "#year")
	public Flux<MovieDTO> findByYear(String year) {
		log.info("Search By Year: {}", year);
		return movieReactiveRepository.findByYear(year).map(movieDTOMapper);
	}

	@Override
	@Cacheable(value = "movies", key = "#music")
	public Flux<MovieDTO> findByMusic(String music) {
		log.info("Service - Find By Music: {}", music);
		return movieReactiveRepository.findByMusic(music).map(movieDTOMapper);
	}

	@Override
	@Cacheable(value = "movies", key = "#picture_rating")
	public Flux<MovieDTO> findByMovieRating(String picture_rating) {
		log.info("Service - Find By Movie Rating: {}", picture_rating);
		return movieReactiveRepository.findByRated(picture_rating).map(movieDTOMapper);
	}

	@Override
	@Cacheable(value = "movies", key = "#imdb")
	public Flux<MovieDTO> findByImdbRatedOver(double imdb) {
		log.info("Service - Find By IMDb Rating: {} And Above", imdb);
		return movieReactiveRepository.findByImdbGreaterThan(imdb).map(movieDTOMapper);
	}

	@Override
	@Cacheable(value = "movies", key = "#rotten_tomatoes_rating")
	public Flux<MovieDTO> findByRottenTomatoesRatedOVer(String rotten_tomatoes_rating) {
		log.info("Service - Find By Rotten Tomatoes Rating: {} And Above", rotten_tomatoes_rating);
		return movieReactiveRepository.findByRottenTomatoesGreaterThan(rotten_tomatoes_rating).map(movieDTOMapper);
	}

	@Override
	@Cacheable(value = "movies", key = "#duration")
	public Flux<MovieDTO> findByDuration(int duration) {
		log.info("Service - Find Shorter Movies By Duration: {} And Below", duration);
		return movieReactiveRepository.findByDurationLessThan(duration).map(movieDTOMapper);
	}

	@Override
	public Flux<MovieDTO> wildCardSearch(String title, String director, String producer, String music, String actor) {
		List<Movie> totalMovieList = new ArrayList<Movie>();

		if (StringUtils.isNotBlank(title)) {
			log.info("Wild Card Search: Look up by Title: {}", title);
			totalMovieList.add(movieReactiveRepository.findByTitle(title).block());
		}

		if (StringUtils.isNotBlank(director)) {
			log.info("Wild Card Search: Look up by Director: {}", director);
			totalMovieList.addAll(movieReactiveRepository.findByDirector(director).collectList().block());
		}
		if (StringUtils.isNotBlank(producer)) {
			log.info("Wild Card Search: Look up by Producer: {}", producer);
			totalMovieList.addAll(movieReactiveRepository.findByProducer(producer).collectList().block());

		}
		if (StringUtils.isNotBlank(music)) {
			log.info("Wild Card Search: Look up by Music: {}", music);
			totalMovieList.addAll(movieReactiveRepository.findByMusic(music).collectList().block());
		}

		if (StringUtils.isNotBlank(actor)) {
			log.info("Wild Card Search: Look up by Actor: {}", actor);
			totalMovieList.addAll(movieReactiveRepository.findByActorsContaining(actor).collectList().block());
		}

		List<MovieDTO> filteredList = totalMovieList.stream()
				.collect(Collectors.toMap(Movie::getId, movie -> movie, (existing, replacement) -> existing)).values()
				.stream().map(movieDTOMapper).collect(Collectors.toList());

		return Flux.fromIterable(filteredList);
	}

	@Override
	public Mono<MovieDTO> update(String id, Movie movie) {
		log.info("Initiate Update For Existing Movie {}", id);
		movieReactiveRepository.findById(id)
		.doOnSuccess(dbSource -> {
			if (dbSource != null) {
				log.info("Found Movie From DB: {}", dbSource.getId());
				Movie updatedMovie = movieUpdateMapper.mapChangesFields(dbSource, movie);
				movieReactiveRepository.save(updatedMovie)
				.doOnSuccess(savedMovie -> {
					log.info("Successfully Updated Movie: {}", updatedMovie.getId());
				})
				.doOnError(error -> log.error("Error: {}", error))
				.subscribe();
			}
		})
		.doOnError(error -> log.info("Error: {]", error))
		.subscribe();

		return Mono.empty();

	}

	@Override
	public Mono<MovieDTO> add(Movie movie) {
		log.info("Adding New Movie Record With Title {}", movie.getTitle());
		movieReactiveRepository.save(movie);
		return Mono.just(movieDTOMapper.apply(movie));
	}
}
