package com.scroll.mapper;

import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.scroll.pojo.Movie;

@Service
public class MovieUpdateMapper implements Function<Movie, Movie> {

	@Override
	public Movie apply(Movie source) {
		Movie target = new Movie();
		target.setTitle(source.getTitle());
		target.setWriter(source.getWriter());
		target.setDirector(source.getDirector());
		target.setProducer(source.getProducer());
		target.setMusic(source.getMusic());
		target.setLanguage(source.getLanguage());
		target.setYear(source.getYear());
		target.setRated(source.getRated());
		target.setGenre(source.getGenre());
		target.setActors(source.getActors());
		target.setBudget(source.getBudget());
		target.setEarnings(source.getEarnings());
		target.setSynopsis(source.getSynopsis());
		target.setDuration(source.getDuration());
		target.setImdb(source.getImdb());
		target.setRottenTomatoes(source.getRottenTomatoes());

		return target;

	}

	public Movie mapChangesFields(Movie dbSource, Movie incomingTarget) {
		if (!StringUtils.equalsIgnoreCase(dbSource.getTitle(), incomingTarget.getTitle())) {
			dbSource.setTitle(incomingTarget.getTitle());
		}
		if (!StringUtils.equalsIgnoreCase(dbSource.getWriter(), incomingTarget.getWriter())) {
			dbSource.setWriter(incomingTarget.getWriter());
		}
		if (!StringUtils.equalsIgnoreCase(dbSource.getDirector(), incomingTarget.getDirector())) {
			dbSource.setDirector(incomingTarget.getDirector());
		}
		if (!StringUtils.equalsIgnoreCase(dbSource.getLanguage(), incomingTarget.getLanguage())) {
			dbSource.setLanguage(incomingTarget.getLanguage());
		}
		if (!StringUtils.equalsIgnoreCase(dbSource.getProducer(), incomingTarget.getProducer())) {
			dbSource.setProducer(incomingTarget.getProducer());
		}
		if (!StringUtils.equalsIgnoreCase(dbSource.getMusic(), incomingTarget.getMusic())) {
			dbSource.setMusic(incomingTarget.getMusic());
		}
		if (!StringUtils.equalsIgnoreCase(dbSource.getYear(), incomingTarget.getYear())) {
			dbSource.setYear(incomingTarget.getYear());
		}
		if (!StringUtils.equalsIgnoreCase(dbSource.getRated(), incomingTarget.getRated())) {
			dbSource.setRated(incomingTarget.getRated());
		}
		if (!dbSource.getActors().containsAll(incomingTarget.getActors())) {
			dbSource.getActors().addAll(incomingTarget.getActors());
		}
		if (!StringUtils.equalsIgnoreCase(dbSource.getBudget(), incomingTarget.getBudget())) {
			dbSource.setBudget(incomingTarget.getBudget());
		}
		if (!StringUtils.equalsIgnoreCase(dbSource.getEarnings(), incomingTarget.getEarnings())) {
			dbSource.setEarnings(incomingTarget.getEarnings());
		}
		if (!StringUtils.equalsIgnoreCase(dbSource.getSynopsis(), incomingTarget.getSynopsis())) {
			dbSource.setSynopsis(incomingTarget.getSynopsis());
		}
		if (!StringUtils.equalsIgnoreCase(dbSource.getRottenTomatoes(), incomingTarget.getRottenTomatoes())) {
			dbSource.setRottenTomatoes(incomingTarget.getRottenTomatoes());
		}
		if (dbSource.getImdb() != incomingTarget.getImdb()) {
			dbSource.setImdb(incomingTarget.getImdb());
		}
		if (dbSource.getDuration() != incomingTarget.getDuration()) {
			dbSource.setDuration(incomingTarget.getDuration());
		}
		return dbSource;
	}

}
