package com.devsuperior.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieByIdDTO;
import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private AuthService authService;

	@Transactional(readOnly = true)
	public Page<MovieDTO> findAll(Long genreId, Pageable pageable) {
//		Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
		User user = authService.authenticated();
		if (user == null) {
			throw new UnauthorizedException("User not found");
		}
		Page<Movie> genres = repository.findByGenre(pageable);
		return genres.map(x -> new MovieDTO(x));
	}
	
	@Transactional(readOnly = true)
	public MovieByIdDTO findById(Long id) {
		User user = authService.authenticated();
		if (user == null) {
			throw new UnauthorizedException("User not found");
		}
		Movie movie = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new MovieByIdDTO(movie);
	}
	
	public List<ReviewDTO> findReviews(Long id){
		
		List<Review> reviews = reviewRepository.findByMovieId(id);
		
		return reviews.stream().map(x -> new ReviewDTO(x)).toList();
	}
}
