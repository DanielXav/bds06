package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private AuthService authService;
	
	public ReviewDTO insert(ReviewDTO dto) {
		
		User user = authService.authenticated();
		
		Review entity = new Review();
		entity.setUser(user);
		Movie movie = movieRepository.getOne(dto.getMovieId());
		entity.setMovie(movie);
		entity.setText(dto.getText());
		entity = repository.save(entity);
		return new ReviewDTO(entity);
	}
}
