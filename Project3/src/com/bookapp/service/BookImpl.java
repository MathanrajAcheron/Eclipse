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
	String sql="create table Books(Bookid integer,Title varchar(10),Author varchar(20),Category varchar(10),Price integer)";

	PreparedStatement ps = null;

	// Connection connection=ModelDAO.openConnection();
	@Override
	public void addBook(Book book) {
		booklist.add(book);
		String insert = "insert into Books values(?,?,?,?,?)";
		try {
			int id=book.getBookid();
			String title = book.getTitle();
			String author=book.getAuthor();
			String category = book.getCategory();
			int bookPrice = book.getPrice();
			ps = connection.prepareStatement(insert);
			ps.setInt(1, id);
			ps.setString(2, title);
			ps.setString(3, author);
			ps.setString(4, category);
			ps.setInt(5, bookPrice);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@Override
	public List<Book> getAllBooks() {
//		if(booklist.size()!=0){
//			Iterator<Book> i=booklist.iterator();
//		while(i.hasNext()) {
//			Book obj=i.next();
//			System.out.println(obj);
//			
//		}}

		String printAll = "select * from Books";
		try {
			
			ps = connection.prepareStatement(printAll);
			ResultSet rs = ps.executeQuery(printAll);// if error remove sql
			while (rs.next()) {
				Book book=new Book();
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String author = rs.getString(3);
				String category = rs.getString(4);
				int price = rs.getInt(5);
				book.setBookid(id);
				book.setTitle(title);
				book.setAuthor(author);
				book.setCategory(category);
				book.setPrice(price);
				booklist.add(book);
			
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("The size of the BookList is " + booklist.size());
//		Collections.sort(booklist, (b1, b2) -> {
//			return b1.getTitle().compareTo(b2.getTitle());
//		});
		return booklist;

	}

	@Override
	public List<Book> getBookbyAuthor(String author) throws AuthorNotFoundException {
		List<Book> searchByAuthor = new ArrayList<>();
		// searchByAuthor=null;

		// System.out.println("Searching.......");

		String printAll = "select * from Books";
		try {
			Book book=new Book();
			ps = connection.prepareStatement(printAll);
			ResultSet rs = ps.executeQuery(printAll);// if error remove sql
			while (rs.next()) {
				String authorsql = rs.getString(3);
				if(authorsql.equalsIgnoreCase(author)) {
					int id = rs.getInt(1);
					String title = rs.getString(2);
					String author1 = rs.getString(3);
					String category = rs.getString(4);
					int price = rs.getInt(5);
					book.setBookid(id);
					book.setTitle(title);
					book.setAuthor(author1);
					book.setCategory(category);
					book.setPrice(price);
					searchByAuthor.add(book);

				}
				
			}ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (searchByAuthor.isEmpty()) {
			throw new AuthorNotFoundException("\nAuthor is not Found.........");
			// throw new AuthorNotFoundException("\nAuthor is not Found");
		}

		return searchByAuthor;
	}

	@Override
	public List<Book> getBookbycategory(String category) throws CategoryNotFoundException {
		List<Book> bookByCategory = new ArrayList<>();
		String printAll = "select * from Books";
		try {
			Book book=new Book();
			ps = connection.prepareStatement(printAll);
			ResultSet rs = ps.executeQuery(printAll);// if error remove sql
			while (rs.next()) {
				String authorsql = rs.getString(4);
				if(authorsql.equalsIgnoreCase(category)) {
					int id = rs.getInt(1);
					String title = rs.getString(2);
					String author1 = rs.getString(3);
					String category1 = rs.getString(4);
					int price = rs.getInt(5);
					book.setBookid(id);
					book.setTitle(title);
					book.setAuthor(author1);
					book.setCategory(category1);
					book.setPrice(price);
					bookByCategory.add(book);

				}
				
			}ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bookByCategory;
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
		Book book=null;
		try {
			ps = connection.prepareStatement(getBookById);
			ps.setInt(1, bookid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				book=new Book();
				int id = rs.getInt(1);
				if(id==bookid) {
					int id1 = rs.getInt(1);
					String title = rs.getString(2);
					String author1 = rs.getString(3);
					String category1 = rs.getString(4);
					int price = rs.getInt(5);
					book.setBookid(id1);
					book.setTitle(title);
					book.setAuthor(author1);
					book.setCategory(category1);
					book.setPrice(price);

				}
			}
			ps.close();
		} finally {
		return book;
	}
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
