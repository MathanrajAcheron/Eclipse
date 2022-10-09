package com.bookapp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.*;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;
import com.bookapp.util.ModelDAO;

public class BookService implements IBookService {
	List<Book> booklist = new ArrayList<Book>();
	
	

	@Override
	public void addBook(Book book) {
		Connection connection = ModelDAO.openConnection();
		PreparedStatement preparedStatement= null;
		String insert = "insert into Books(?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(insert);
			preparedStatement.setInt(1, book.getBookid());
			preparedStatement.setString(2, book.getTitle());
			preparedStatement.setString(3, book.getAuthor());
			preparedStatement.setString(4, book.getCategory());
			preparedStatement.setInt(5, book.getPrice());
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//
//	@Override
//	public List<Book> getAllBooks() {
//		Connection connection = ModelDAO.openConnection();
//		PreparedStatement preparedStatement = null;
//		List<Book> bookList=null;
//		Book book=null;
//		String print = "select * from Books";
//		try {
//			preparedStatement = connection.prepareStatement(print);
//			ResultSet resultSet = preparedStatement.executeQuery();// if error remove sql
//			while (resultSet.next()) {
//				book=new Book();
//				int id = resultSet.getInt(1);
//				String title = resultSet.getString(2);
//				String author = resultSet.getString(3);
//				String category = resultSet.getString(4);
//				int price = resultSet.getInt(5);
//				book.setBookid(id);
//				book.setAuthor(author);
//				book.setCategory(category);
//				book.setPrice(price);
//				book.setTitle(title);
//				bookList.add(book);
//
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			preparedStatement.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("The size of the BookList is " + booklist.size());
//		Collections.sort(booklist, (book1, book2) -> {
//			return book1.getTitle().compareTo(book2.getTitle());
//		});
//		return bookList;
//
//	}
//
//	@Override
//	public List<Book> getBookbyAuthor(String author) throws AuthorNotFoundException {
//		List<Book> bookList = new ArrayList<>();
//
//		for (Book search : booklist) {
//			if (search.getAuthor().equalsIgnoreCase(author)) {
//				bookList.add(search);
//			}
//		}
//
//		Collections.sort(bookList, (book1, book2) -> {
//			return book1.getAuthor().compareTo(book2.getAuthor());
//		});
//		System.out.println("The Total number of books published by Author: " + bookList.size());
//
//		if (bookList.isEmpty()) {
//			throw new AuthorNotFoundException("\nAuthor is not Found.........");
//		}
//
//		return bookList;
//	}
//
//	@Override
//	public List<Book> getBookbycategory(String category) throws CategoryNotFoundException {
//
//		List<Book> bookList = booklist.stream().filter(book -> book.getCategory().equals(category))
//				.sorted((book1, book2) -> book1.getTitle().compareTo(book2.getTitle()))
//
//				.collect(Collectors.toList());
//
//	
//		if (bookList.isEmpty()) {
//			throw new CategoryNotFoundException("Category is not found");
//		}
//
//		return bookList;
//	}

	@Override
	public boolean deleteBook(int bookid) throws BookNotFoundException {
		Connection connection = ModelDAO.openConnection();

		PreparedStatement preparedStatement = null;
		boolean flag = false;
		String getBookById = "delete from books  where Bookid=?";
		try {

			preparedStatement = connection.prepareStatement(getBookById);
			preparedStatement.setInt(1, bookid);
			preparedStatement.execute();
//			ResultSet resultSet = preparedStatement.executeQuery();
		flag = true;

			preparedStatement.close();
		} catch (SQLException e) {
			throw new BookNotFoundException("Book Not Found....");
		}
		return flag;
	}

	@Override
	public Book getBookById(int bookid) throws BookNotFoundException {
		Connection connection = ModelDAO.openConnection();
		Book book=null;
		PreparedStatement preparedStatement = null;
		String getBookById = "select * from books where Bookid=?";
		try {
			preparedStatement = connection.prepareStatement(getBookById);
			preparedStatement.setInt(1, bookid);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				book=new Book();
				int id = resultSet.getInt(1);
				String title = resultSet.getString(2);
				String author = resultSet.getString(3);
				String category = resultSet.getString(4);
				int price = resultSet.getInt(5);
				book.setBookid(id);
				book.setTitle(title);
				book.setAuthor(author);
				book.setPrice(price);
				book.setCategory(category);
			}
			preparedStatement.close();
		} catch (SQLException e) {
			throw new BookNotFoundException("Book Not Found....");
		}
		return book;
	}

	@Override
	public boolean updateBook(int bookid, int price) {
		Connection connection = ModelDAO.openConnection();
		PreparedStatement preparedStatement = null;
		boolean flag = false;
		String getBookById = "update books set Price=? where Bookid=?";
		try {

			preparedStatement = connection.prepareStatement(getBookById);
			preparedStatement.setInt(1, price);
			preparedStatement.setInt(2, bookid);
			preparedStatement.execute();
//			ResultSet resultSet = preparedStatement.executeQuery();
			flag = true;
			preparedStatement.close();
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

}
