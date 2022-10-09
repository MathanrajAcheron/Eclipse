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
