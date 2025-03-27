package com.scroll.DTO;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "movies")
public class MovieDTO {
	
	@Id
	private String id;
	private AcademyAward academyAward;
	private String[] actors;
	private String budget;
	private String director;
	private int duration;
	private String[] genre;
	private Long earnings;
	private double imdb_rating;
	private String language;
	private String music;
	private String picture_rating;
	private String producer;
	private String rotten_tomatoes_rating;
	private String synopsis;
	private String title;
	private String writer;
	private String year;
}
