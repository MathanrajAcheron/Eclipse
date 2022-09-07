package com.bookapp.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.Scanner;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.service.BookImpl;
import com.bookapp.service.BookInter;
import com.bookapp.service.ModelDAO;

public class Client {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		BookInter bookimpl = new BookImpl();
		Connection connection = ModelDAO.openConnection();
		PreparedStatement ps = null;
		int BooksToBeAdded;
		try {

			while (true) {
				System.out.println("1.using SQL \t 2.Using List\t3.Exit");
				int choice = sc.nextInt();
				switch (choice) {
				case 1:// System.out.println("We have already Created Table\nTable Name:Books\nColumns
						// contains
						// Bookid(int),Title(varchar),Author(varchar),category(varchar),Price(int)");
					System.out.println("1.Insert table \t 2.Update table \t3.Delete table \t4.TablePresent?");
					int choice1 = sc.nextInt();
					switch (choice1) {
					// default:System.exit(0);
					case 1:
						String insert = "insert into books values(?,?,?,?,?)";
						ps = connection.prepareStatement(insert);
						System.out.println("Enter the Number of Books to be Added: ");
						BooksToBeAdded = sc.nextInt();
						for (int i = 1; i <= BooksToBeAdded; i++) {
							System.out.println("Enter BookID:");
							int id = sc.nextInt();
							ps.setInt(1, id);
							System.out.println("Enter Title:");
							String title = sc.next();
							ps.setString(2, title);
							System.out.println("Enter Author:");
							String author = sc.next();
							ps.setString(3, author);
							System.out.println("Enter Category:");
							String category = sc.next();
							ps.setString(4, category);
							System.out.println("Enter Price:");
							int price = sc.nextInt();
							ps.setInt(5, price);
							ps.execute();
						}
						break;
					case 2:
						System.out.println("Enter the BookID for Updation:");
						int id = sc.nextInt();
						System.out.println("Enter the Price to be Updated:");
						int price = sc.nextInt();
						boolean updated = bookimpl.updateBook(id, price);
						if (updated) {
							System.out.println("UPDATED SUCCESSFULLY");
						} else {
							System.out.println("NOT UPDATED Check Bookid is correct?");
						}
						break;
					case 3:
						System.out.println("Enter the BookID for Deletion:");
						int deleteId = sc.nextInt();
						boolean deleted = bookimpl.deleteBook(deleteId);
						if (deleted) {
							System.out.println("DELETED SUCCESSFULLY");
						} else {
							System.out.println("NOT DELETED Check Bookid is correct?");
						}
						break;
					case 4:
						System.out.println("Enter the BookID is Present?");
						int presentId = sc.nextInt();
						// System.out.println(bookimpl.getBookById(presentId));
						bookimpl.getBookById(presentId);

						break;
					default:
						System.out.println("Wrong Choice.....\tTry Again.");
						System.exit(0);

					}

					break;
				case 2:
					System.out.println("Number of Books to be Added : ");
					int add = sc.nextInt();
					if (add == 0) {
						System.out.println("Please enter other than 0");
						System.exit(0);
					}
					for (int i = 1; i < add; i++) {
						Book book = new Book();
						System.out.println("----------------------------");
						System.out.println("Enter the Title: ");
						// sc.next();
						String title = sc.next();
						book.setTitle(title);
						// sc.next();
						System.out.println("Enter the Author: ");
						String author = sc.next();
						book.setAuthor(author);
						// sc.next();
						System.out.println("Enter the Category: ");
						String category = sc.next();
						book.setCategory(category);
						System.out.println("Enter the BookID: ");
						int id = sc.nextInt();
						book.setBookid(id);
						System.out.println("Enter the Price: ");
						int bookPrice = sc.nextInt();
						book.setPrice(bookPrice);
						// book=new Book(title,author,category,id,bookPrice);
						bookimpl.addBook(book);

					}
					System.out.println("Connecting....\nPress 1 to Continue...");
					int pressTheNumber = sc.nextInt();
					while (pressTheNumber == 1) {
						System.out
								.println("\n1.Display the Books \t2.Search By Author \t3.Search by Category \t4.Exit");
						int addTheBooks = sc.nextInt();
						switch (addTheBooks) {
						case 1:
							System.out.println("\nDisplaying All the Books...........\n");
							System.out.println(bookimpl.getAllBooks());
							break;
						case 2:
							System.out.println("Enter the Author Name to Search Books : ");
							String author = sc.next();
							System.out.println("Author Search: " + bookimpl.getBookbyAuthor(author));
							break;
						case 3:
							System.out.println("Enter the Category of the Book : ");
							String category = sc.next();
							System.out.println("Category :" + bookimpl.getBookbycategory(category));
							break;
						case 4:
							System.exit(0);
							break;
						default:
							System.out.println("Press the Correct Number....");
							break;

						}

					}
				default:
					System.exit(0);
				}
			}
//		//String sql="create table Books(Bookid integer,Title varchar(10),Author varchar(20),Category varchar(10),Price integer)";
//		String insert="insert into Books(?,?,?,?,?)";
//		PreparedStatement st=null;
//		try {
//			System.out.println("Creating tables..");
//			st = connection.prepareStatement(insert);
//			System.out.println("Hi,Welcome to Online Booking App....");
//			System.out.println("Number of Books to be Added : ");
//			int add = sc.nextInt();
//			if(add==0) {
//				System.out.println("Please enter other than 0");
//				System.exit(0);
//			}
//			
//			for(int i=1;i<=add;i++) {
//				
//			}
//			System.out.println("Connecting....\nPress 1 to Continue...");
//			int pressTheNumber = sc.nextInt();
//			while (pressTheNumber == 1) {
//				System.out.println("\n1.Display the Books \t2.Search By Author \t3.Search by Category \t4.Exit");
//				int addTheBooks = sc.nextInt();
//				switch (addTheBooks) {
//				case 1:System.out.println("\nDisplaying All the Books...........\n");
//				System.out.println(bookimpl.getAllBooks());break;
//				case 2:System.out.println("Enter the Author Name to Search Books : ");
//					String author=sc.next();
//					System.out.println("Author Search: "+bookimpl.getBookbyAuthor(author));break;
//				case 3:System.out.println("Enter the Category of the Book : ");
//				String category=sc.next();
//				System.out.println("Category :"+bookimpl.getBookbycategory(category));break;
//				case 4:System.exit(0);break;
//				default:System.out.println("Press the Correct Number....");
//					
//				}
//
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		finally {
//			
//				try {
//					if(connection!=null)
//					connection.close();
//					if(st!=null)
//					st.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		}

			// TODO Auto-generated method stub

//		try {
//			System.out.println("Number of Books to be Added : ");
//			int add = sc.nextInt();
//			if(add==0) {
//				System.out.println("Please enter other than 0");
//				System.exit(0);
//			}/*
//			for (int i = 0; i < add; i++) {
//				System.out.println("----------------------------");
//				System.out.println("Enter the Title: ");
//				//sc.next();
//				String title = sc.next();
//				book.setTitle(title);
//				//sc.next();
//				System.out.println("Enter the Author: ");
//				String author = sc.next();
//				book.setAuthor(author);
//				//sc.next();
//				System.out.println("Enter the Category: ");
//				String category = sc.next();
//				book.setCategory(category);
//				System.out.println("Enter the BookID: ");
//				int id = sc.nextInt();
//				book.setBookid(id);
//				System.out.println("Enter the Price: ");
//				int bookPrice = sc.nextInt();
//				book.setPrice(bookPrice);
//				//book=new Book(title,author,category,id,bookPrice);
//				bookimpl.addBook(book);
//				
//			}*/
//			System.out.println("Connecting....\nPress 1 to Continue...");
//			int pressTheNumber = sc.nextInt();
//			while (pressTheNumber == 1) {
//				System.out.println("\n1.Display the Books \t2.Search By Author \t3.Search by Category \t4.Exit");
//				int addTheBooks = sc.nextInt();
//				switch (addTheBooks) {
//				case 1:System.out.println("\nDisplaying All the Books...........\n");
//				System.out.println(bookimpl.getAllBooks());break;
//				case 2:System.out.println("Enter the Author Name to Search Books : ");
//					String author=sc.next();
//					System.out.println("Author Search: "+bookimpl.getBookbyAuthor(author));break;
//				case 3:System.out.println("Enter the Category of the Book : ");
//				String category=sc.next();
//				System.out.println("Category :"+bookimpl.getBookbycategory(category));break;
//				case 4:System.exit(0);break;
//				default:System.out.println("Press the Correct Number....");
//					
//				}
//
//			}

//		int number=sc.nextInt();
//		Book book[]=new Book[number];
//		for(int i=0;i<number;i++) {
//			BookInter intern[]=new BookImpl[i]();
//			intern[i].addBook(null);
//			
//		}
//		BookInter intern=new BookImpl();
//		intern.addBook(null);

		} catch (Exception e) {
			System.out.println("Try Again... \n" + e.getMessage());
		} finally {
			System.out.println("\nThank you for using our App...");
			ModelDAO.closeConnection();
			// ps.close();
		}
		// finally {
		// System.out.println("\nThank you for using our App...");
		// ps.close();
		// ModelDAO.closeConnection();
	}

}
