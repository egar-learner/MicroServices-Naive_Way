package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	WebClient.Builder builder;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		//1. Get all rated Movie Ids
		//2. For each movie ID, call movie info service and get details
		//3. Put them all together
		
		/*List<Rating> ratings = Arrays.asList(
				new Rating("1234", 4),
				new Rating("5678", 3) );*/
		
		UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/"+userId, UserRating.class);
		
		//Get me a resource and unmarshal it into a resource
		
		return ratings.getRatings().stream().map(rating-> {
			
			//Rest Template way of doing things
			Movie movieObj = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
			
			/*Movie movieObj = builder.build()
								.get().uri("http://localhost:8082/movies/"+rating.getMovieId())
								.retrieve()
								.bodyToMono(Movie.class)
								.block();*/
			
			return new CatalogItem(movieObj.getName(), "Test", rating.getRating());
		}
		).collect(Collectors.toList());
		
	}
}
