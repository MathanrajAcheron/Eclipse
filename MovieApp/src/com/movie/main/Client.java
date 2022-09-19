package com.movie.main;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.movie.service.IMovieService;
import com.movie.service.MovieServiceImpl;
import com.movie.servicecom.movie.exceptions.IdNotFoundException;
import com.movie.servicecom.movie.exceptions.MovieNotFoundException;
import com.movieapp.model.Movie;

public class Client {

	public static void main(String[] args) {
		IMovieService movieService=new MovieServiceImpl();
		System.out.println("Welcome to movie platform");
		int select;
//		try(Scanner sc=new Scanner(System.in);){
		Scanner sc=new Scanner(System.in);
		do {
		System.out.println("1.Show Movies \t 2.Search By Genre \t 3.Search By Language \t4.Search By ID");
		int option=sc.nextInt();
		switch(option) {
		case 1:
		List<Movie> showMovies=movieService.getAll();
		Iterator<Movie> i=showMovies.iterator();
		System.out.println("Showing lists....");
		while(i.hasNext()) {
			System.out.print(i.next());
			System.out.println();
		}break;
		case 2:
			System.out.println("Type the Input to Search Genre : ");
			String searchGenre=sc.next();
			try {
				List<Movie> showGenres=movieService.getByGenre(searchGenre);
				Iterator<Movie> it=showGenres.iterator();
				System.out.println("Showing lists....");
				while(it.hasNext()) {
					System.out.print(it.next());
					System.out.println();
				}
			} catch (MovieNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}break;
		case 3:
			System.out.println("Type the Input to Search Language : ");
			String searchLanguage=sc.next();
			try {
				List<Movie> showLanguage=movieService.getByLanguage(searchLanguage);
				Iterator<Movie> iter=showLanguage.iterator();
				System.out.println("Showing lists....");
				while(iter.hasNext()) {
					System.out.print(iter.next());
					System.out.println();
				}
			} catch (MovieNotFoundException e) {
				System.out.println(e.getMessage());
			}break;
		case 4:
			System.out.println("Type the Input to Search ID : ");
			int searchId=sc.nextInt();
			try {
				Movie movie1=movieService.getById(searchId);
				System.out.println(movie1);
			} catch (IdNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			
			break;
		default:
			System.out.println("Invalid Input");
			System.exit(0);
		}
		System.out.println();
		System.out.println("PRESS 1 TO CONTINUE...");
		select=sc.nextInt();
		}while(select==1);
		}

}
