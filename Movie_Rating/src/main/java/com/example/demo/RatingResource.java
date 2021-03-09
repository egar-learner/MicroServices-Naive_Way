package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}

	//Why we have created an Object to hold just a single List Object, 
	//When we could have directly returned list??
	//To make it backward compatible or say compatible to make changes in future add or remove something
	//To this object we are returning
	@RequestMapping("users/{userId}")
	public UserRating getRatingForUserId(@PathVariable("userId") String movieId) {
		return new UserRating((List<Rating>) Arrays.asList(
				new Rating("1234", 4),
				new Rating("5678", 3) ));
	}
}
