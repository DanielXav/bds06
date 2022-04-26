package com.devsuperior.movieflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query("SELECT obj FROM Movie obj ORDER BY obj.title ASC ")
	Page<Movie> findByGenre(Pageable pageable);
	// ORDER BY//obj.title ASC WHERE obj.genre = ':genreId'

	// GENREID

//		@Query("SELECT obj FROM Movie obj WHERE :genreId IS NULL OR obj.genre.id = :genreId")
//		Page<Movie> findByGenre(Long genreId, Pageable pageable);
//		// ORDER BY obj.title ASC WHERE obj.genre = ':genreId'

	/*
	 * @Query("SELECT obj FROM Movie obj WHERE :genre IS NULL OR obj.genre = :genre"
	 * ) Page<Movie> findByGenre(Genre genre, Pageable pageable); // ORDER BY
	 * obj.title ASC WHERE obj.genre = ':genreId'
	 */

}
