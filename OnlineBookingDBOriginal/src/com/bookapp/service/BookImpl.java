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

public class BookImpl implements BookInter {
	List<Book> booklist = new ArrayList<Book>();
	Scanner sc = new Scanner(System.in);
	Connection connection = ModelDAO.openConnection();
	// String sql="create table Books(Bookid integer,Title varchar(10),Author
	// varchar(20),Category varchar(10),Price integer)";

	PreparedStatement ps = null;

	// Connection connection=ModelDAO.openConnection();
	@Override
	public void addBook(Book book) {
//		String insert = "insert into Books(?,?,?,?,?)";
//		try {
//			st = connection.prepareStatement(insert);
//			System.out.println("Enter the BookID: ");
//			int id = sc.nextInt();
//			st.setInt(1, id);
//			System.out.println("Enter the Title: ");
//			String title = sc.next();
//			st.setString(2, title);
//			System.out.println("Enter the Author: ");
//			String author = sc.next();
//			st.setString(3, author);
//			System.out.println("Enter the Category: ");
//			String category = sc.next();
//			st.setString(4, category);
//			System.out.println("Enter the Price: ");
//			int bookPrice = sc.nextInt();
//			st.setInt(5, bookPrice);
//			st.execute();
//			st.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		booklist.add(book);
	}

	@Override
	public List<Book> getAllBooks() {
//		if(list1.size()!=0){
//			Iterator<Book> i=list1.iterator();
//		while(i.hasNext()) {
//			Book obj=i.next();
//			System.out.println(obj);
//			
//		}}

//		String printall = "select * from Books";
//		try {
//			st = connection.prepareStatement(printall);
//			ResultSet rs = st.executeQuery(printall);// if error remove sql
//			while (rs.next()) {
//				int id = rs.getInt(1);
//				String title = rs.getString(2);
//				String author = rs.getString(3);
//				String cate = rs.getString(4);
//				int price = rs.getInt(5);
//				System.out.println("ID: " + id + " Title: " + title + " Author: " + author + " Category: " + cate
//						+ " Price: " + price);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("The size of the BookList is " + booklist.size());
		Collections.sort(booklist, (b1, b2) -> {
			return b1.getTitle().compareTo(b2.getTitle());
		});
		return booklist;

	}

	@Override
	public List<Book> getBookbyAuthor(String author) throws AuthorNotFoundException {
		List<Book> searchByAuthor = new ArrayList<>();
		// searchByAuthor=null;

		// System.out.println("Searching.......");

		for (Book search : booklist) {
			if (search.getAuthor().equalsIgnoreCase(author)) {
				searchByAuthor.add(search);
			}
		}
		// System.out.println("Sorting the Author...........");

		Collections.sort(searchByAuthor, (b1, b2) -> {
			return b1.getAuthor().compareTo(b2.getAuthor());
		});
		System.out.println("The Total number of books published by Author: " + searchByAuthor.size());
//			for(Book book1:searchByAuthor) {
//				System.out.println(book1);
//			}
		if (searchByAuthor.isEmpty()) {
			throw new AuthorNotFoundException("\nAuthor is not Found.........");
			// throw new AuthorNotFoundException("\nAuthor is not Found");
		}

		return searchByAuthor;
	}

	@Override
	public List<Book> getBookbycategory(String category) throws CategoryNotFoundException {

		List<Book> booksByCategory = booklist.stream().filter(book -> book.getCategory().equals(category))
				.sorted((b1, b2) -> b1.getTitle().compareTo(b2.getTitle()))

				.collect(Collectors.toList());

//	
		if (booksByCategory.isEmpty()) {
			throw new CategoryNotFoundException("Category is not found");
		}

		return booksByCategory;
	}

	@Override
	public boolean deleteBook(int bookid) throws BookNotFoundException {
		boolean flag = false;
		String getBookById = "delete from books  where Bookid=?";
		try {

			ps = connection.prepareStatement(getBookById);
			ps.setInt(1, bookid);
			ps.execute();
//			ResultSet rs = ps.executeQuery();
		flag = true;
//			while (rs.next()) {
//				int id = rs.getInt(1);
//				String title = rs.getString(2);
//				String author = rs.getString(3);
//				String category = rs.getString(4);
//				int localprice = rs.getInt(5);
//				System.out.println(id + "\t" + title + "\t" + author + "\t" + category + "\t" + localprice);
//			}
			ps.close();
		} catch (SQLException e) {
			throw new BookNotFoundException("Book Not Found....");
		}
		return flag;
	}

	@Override
	public Book getBookById(int bookid) throws BookNotFoundException {
		// TODO Auto-generated method stub
		String getBookById = "select * from books where Bookid=?";
		try {
			ps = connection.prepareStatement(getBookById);
			ps.setInt(1, bookid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String author = rs.getString(3);
				String category = rs.getString(4);
				int price = rs.getInt(5);
				System.out.println(id + "\t" + title + "\t" + author + "\t" + category + "\t" + price);
			}
			ps.close();
		} catch (SQLException e) {
			throw new BookNotFoundException("Book Not Found....");
		}
		return null;
	}

	@Override
	public boolean updateBook(int bookid, int price) {
		boolean flag = false;
		String getBookById = "update books set Price=? where Bookid=?";
		try {

			ps = connection.prepareStatement(getBookById);
			ps.setInt(1, price);
			ps.setInt(2, bookid);
			ps.execute();
//			ResultSet rs = ps.executeQuery();
			flag = true;
//			while (rs.next()) {
//				int id = rs.getInt(1);
//				String title = rs.getString(2);
//				String author = rs.getString(3);
//				String category = rs.getString(4);
//				int localprice = rs.getInt(5);
//				System.out.println(id + "\t" + title + "\t" + author + "\t" + category + "\t" + localprice);
//			}
			ps.close();
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

}
