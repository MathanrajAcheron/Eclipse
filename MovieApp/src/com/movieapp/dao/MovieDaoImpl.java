package com.movieapp.dao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.movie.servicecom.movie.exceptions.IdNotFoundException;
import com.movie.servicecom.movie.exceptions.MovieNotFoundException;
import com.movieapp.model.Movie;

public class MovieDaoImpl implements IMovieDao{

	private List<Movie> showMovies(){
		List<Movie> movie=Arrays.asList(
				new Movie("Avatar","Action","English",2009,1001),
				new Movie("IronMan","Action","English",2012,1021),
				new Movie("SpiderMan","Adventure","Tamil",2012,1031),
				new Movie("Pokeman","Action","Tamil",2015,1004))
				;
		return movie;
	}
	@Override
	public List<Movie> findAll() {
		
		return showMovies();
	}

	@Override
	public List<Movie> findByLanguage(String language) throws MovieNotFoundException{
		List<Movie> movieByLang=showMovies().stream().filter((str)->{
			return str.getLanguage().equalsIgnoreCase(language);
		})
		.collect(Collectors.toList());
		return movieByLang;
	}

	@Override
	public List<Movie> findByGenre(String genre) throws MovieNotFoundException {
		List<Movie> movieBygenre=showMovies().stream().filter((str)->{
			return str.getGenre().equalsIgnoreCase(genre);
		})
				
		.collect(Collectors.toList());
		return movieBygenre;
	}

	@Override
	public Movie findById(int movieId) throws IdNotFoundException{
		Movie movieById=(Movie) showMovies().stream().filter((movie)->{
			return ((Integer)(movie.getMovieId())).equals(movieId);
		}).findFirst()
		.orElseThrow(()->new IdNotFoundException("ID is not Found"));
		return movieById;
	}

}
