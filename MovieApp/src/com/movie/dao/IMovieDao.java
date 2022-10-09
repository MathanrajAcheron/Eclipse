package com.movie.dao;

import java.util.List;

import com.movie.exceptions.IdNotFoundException;
import com.movie.exceptions.MovieNotFoundException;
import com.movie.model.Movie;

public interface IMovieDao {
	List<Movie> findAll();

	List<Movie> findByLanguage(String language) throws MovieNotFoundException;

	List<Movie> findByGenre(String genre) throws MovieNotFoundException;

	Movie findById(int movieId) throws IdNotFoundException;

}
