package com.devsuperior.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;

@Service
public class GenreService {
	
	@Autowired
	private GenreRepository repository;
	
	@Autowired
	private AuthService authService;

	@Transactional(readOnly = true)
	public List<GenreDTO> findAll() {
		User user = authService.authenticated();
		if (user == null) {
			throw new UnauthorizedException("User not found");
		}
		List<Genre> genres = repository.findAll();
		return genres.stream().map(x -> new GenreDTO(x)).toList();
	}
}
