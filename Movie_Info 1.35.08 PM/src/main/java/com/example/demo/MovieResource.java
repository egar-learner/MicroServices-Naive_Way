package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource {

	@RequestMapping("/{movie}")
	public Movie getMovieInfo(@PathVariable("movie") String movieId) {
		
		return new Movie(movieId, "Test name");
	}
}
