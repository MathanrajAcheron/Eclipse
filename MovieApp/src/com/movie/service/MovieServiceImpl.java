package com.movie.service;


import java.util.List;
import java.util.stream.Collectors;

import com.movie.dao.IMovieDao;
import com.movie.dao.MovieDaoImpl;
import com.movie.exceptions.IdNotFoundException;
import com.movie.exceptions.MovieNotFoundException;
import com.movie.model.Movie;

public class MovieServiceImpl implements IMovieService {
	IMovieDao movieDao=new MovieDaoImpl();
	

	@Override
	public List<Movie> getAll() {
		
		return movieDao.findAll();
	}

	@Override
	public List<Movie> getByLanguage(String language) throws MovieNotFoundException{
		List<Movie> movies=movieDao.findByLanguage(language);
		if(movies.isEmpty()) {
			throw new MovieNotFoundException("Language is not Found");
		}
		movies.stream().sorted((movie1,movie2)->{
			return ((Integer)(movie1.getYear())).compareTo((Integer)(movie2.getYear()));
		}).collect(Collectors.toList());
		// TODO Auto-generated method stub
		return movies;
	}

	@Override
	public List<Movie> getByGenre(String genre) throws MovieNotFoundException{
		List<Movie> movies=movieDao.findByGenre(genre);
		if(movies.isEmpty()) {
			throw new MovieNotFoundException("Genre is not Found");
		}
		movies.stream().sorted((movie1,movie2)->{
			Integer m1=(Integer)movie1.getYear();
			Integer m2=(Integer)movie2.getYear();
			return m1.compareTo(m2);
			
		}).collect(Collectors.toList());
		// TODO Auto-generated method stub
		return movies;
	}

	@Override
	public Movie getById(int movieId) throws IdNotFoundException{
		Movie movies=movieDao.findById(movieId);
		if(movies==null) {
			throw new IdNotFoundException("Movie ID is not Found");
		}
		
		// TODO Auto-generated method stub
		return movies;
	}

}
