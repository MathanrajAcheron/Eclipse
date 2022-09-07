package com.bookapp.main;

import java.util.*;
import java.util.Scanner;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.service.BookImpl;
import com.bookapp.service.BookInter;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
	//	Book book = new Book();
		BookInter bookimpl = new BookImpl();
		System.out.println("Hi,Welcome to Online Booking App....");
		try {
			System.out.println("Number of Books to be Added : ");
			int add = sc.nextInt();
			if(add==0) {
				System.out.println("Please enter other than 0");
				System.exit(0);
			}
			for (int i = 0; i < add; i++) {
				Book book=new Book();
				System.out.println("----------------------------");
				System.out.println("Enter the Title: ");
				//sc.next();
				String title = sc.next();
				book.setTitle(title);
				//sc.next();
				System.out.println("Enter the Author: ");
				String author = sc.next();
				book.setAuthor(author);
				//sc.next();
				System.out.println("Enter the Category: ");
				String category = sc.next();
				book.setCategory(category);
				System.out.println("Enter the BookID: ");
				int id = sc.nextInt();
				book.setBookid(id);
				System.out.println("Enter the Price: ");
				int bookPrice = sc.nextInt();
				book.setPrice(bookPrice);
				//book=new Book(title,author,category,id,bookPrice);
				bookimpl.addBook(book);
				
			}
			System.out.println("Connecting....\nPress 1 to Continue...");
			int pressTheNumber = sc.nextInt();
			while (pressTheNumber == 1) {
				System.out.println("\n1.Display the Books \t2.Search By Author \t3.Search by Category \t4.Exit");
				int addTheBooks = sc.nextInt();
				switch (addTheBooks) {
				case 1:System.out.println("\nDisplaying All the Books...........\n");
				System.out.println(bookimpl.getAllBooks());break;
				case 2:System.out.println("Enter the Author Name to Search Books : ");
					String author=sc.next();
					System.out.println("Author Search: "+bookimpl.getBookbyAuthor(author));break;
				case 3:System.out.println("Enter the Category of the Book : ");
				String category=sc.next();
				System.out.println("Category :"+bookimpl.getBookbycategory(category));break;
				case 4:System.exit(0);break;
				default:System.out.println("Press the Correct Number....");
					
				}

			}

//		int number=sc.nextInt();
//		Book book[]=new Book[number];
//		for(int i=0;i<number;i++) {
//			BookInter intern[]=new BookImpl[i]();
//			intern[i].addBook(null);
//			
//		}
//		BookInter intern=new BookImpl();
//		intern.addBook(null);

		} 
		catch (Exception e) {
			System.out.println("Try Again... \n" + e.getMessage());
		} finally {
			System.out.println("\nThank you for using our App...");
			sc.close();
		}

	}
}
