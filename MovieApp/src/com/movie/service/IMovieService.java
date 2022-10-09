package com.movie.service;

import java.util.List;

import com.movie.exceptions.IdNotFoundException;
import com.movie.exceptions.MovieNotFoundException;
import com.movie.model.Movie;

public interface IMovieService {
	List<Movie> getAll();

	List<Movie> getByLanguage(String language) throws MovieNotFoundException;

	List<Movie> getByGenre(String genre) throws MovieNotFoundException;

	Movie getById(int movieId) throws IdNotFoundException;

}
