package com.bookapp.service;

import java.util.*;
import java.util.stream.*;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class BookImpl implements BookInter {
	List<Book> booklist=new ArrayList<Book>();
	@Override
	public void addBook(Book book) {
		booklist.add(book);
	}

	@Override
	public List<Book> getAllBooks()  {
//		if(list1.size()!=0){
//			Iterator<Book> i=list1.iterator();
//		while(i.hasNext()) {
//			Book obj=i.next();
//			System.out.println(obj);
//			
//		}}
		System.out.println("The size of the BookList is "+booklist.size());
		Collections.sort(booklist,(b1,b2)->{
			return b1.getTitle().compareTo(b2.getTitle());
		});
		return booklist;
		
	}
	

	@Override
	public List<Book> getBookbyAuthor(String author) throws AuthorNotFoundException {
		List<Book> searchByAuthor=new ArrayList<>();
		//searchByAuthor=null;
		try {
			//System.out.println("Searching.......");

			for(Book search:booklist) {
				if(search.getAuthor().equalsIgnoreCase(author)) {
					searchByAuthor.add(search);
				}
			}
			//System.out.println("Sorting the Author...........");

			Collections.sort(searchByAuthor,(b1,b2)->{
				return b1.getAuthor().compareTo(b2.getAuthor());
			});
			System.out.println("The Total number of books published by Author: "+searchByAuthor.size());
//			for(Book book1:searchByAuthor) {
//				System.out.println(book1);
//			}
			if(searchByAuthor.isEmpty()) {
				System.out.println("Author Name is not Found in the List");
				//throw new AuthorNotFoundException("\nAuthor is not Found");
			}
		}catch(Exception e) {
			throw new AuthorNotFoundException("\nAuthor is not Found.........");
		}
		return searchByAuthor;
	}

	@Override
	public List<Book> getBookbycategory(String category) throws CategoryNotFoundException {
		
			List<Book> booksByCategory = booklist
					.stream()
					.filter(book->book.getCategory().equals(category))
					.sorted((b1,b2)->b1.getTitle().compareTo(b2.getTitle()))
					
					.collect(Collectors.toList());
	

//		
			if(booksByCategory.isEmpty()) {
				throw new CategoryNotFoundException("Category is not found");
			}
		
		return booksByCategory;
	}

}
