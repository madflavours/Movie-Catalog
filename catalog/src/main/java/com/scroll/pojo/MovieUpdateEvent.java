package com.scroll.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MovieUpdateEvent {

	private Movie before;
	private Movie after;

	public MovieUpdateEvent(Movie before, Movie after) {
		super();
		this.before = before;
		this.after = after;
	}
}
