package com.movieapp.model;

public class Movie {
private String name;
private String genre;
private String language;
private int year;
private int movieId;
public Movie() {
	super();
	// TODO Auto-generated constructor stub
}
public Movie(String name, String genre, String language, int year, int movieId) {
	super();
	this.name = name;
	this.genre = genre;
	this.language = language;
	this.year = year;
	this.movieId = movieId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getGenre() {
	return genre;
}
public void setGenre(String genre) {
	this.genre = genre;
}
public String getLanguage() {
	return language;
}
public void setLanguage(String language) {
	this.language = language;
}
public int getYear() {
	return year;
}
public void setYear(int year) {
	this.year = year;
}
public int getMovieId() {
	return movieId;
}
public void setMovieId(int movieId) {
	this.movieId = movieId;
}
@Override
public String toString() {
	return "Movie--> [name=" + name + ", genre=" + genre + ", language=" + language + ", year=" + year + ", movieId="
			+ movieId + "]";
}

}
