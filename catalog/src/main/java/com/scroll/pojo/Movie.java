package com.scroll.pojo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "movies")
public class Movie {
	
	@Id
	private String id;
	private AcademyAward academyAwards;
	private List<String> actors;
	private String budget;
	private String director;
	private int duration;
	private List<String> genre;
	private String earnings;
	private double imdb;
	private String language;
	private String music;
	private String rated;
	private String producer;
	private String rottenTomatoes;
	private String synopsis;
	private String title;
	private String writer;
	private String year;
}
