package com.scroll.DTO;

import java.util.List;

public record MovieDTO(
		String title, 
		String writer,
		String director,
		String producer,
		String music,
		String language,
		String year,
		String picture_rating,
		List<String> genre,
		List<String> actors,
		String budget,
		String earnings,
		String synopsis,
		int duration,
		double IMDB_rating,
		String rottenTomatoes
		)

{}
