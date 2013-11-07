package com.lohika.book.storage.service.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import com.lohika.book.storage.api.BookServices;
import com.lohika.book.storage.model.Book;
import com.lohika.book.storage.model.Books;

@Path("/book")
public class BookServicesImpl implements BookServices {
	
	@Override
	public Book getBookById(Integer id) {
		return new Book("author", "title", 12d, "contentUrl");
	}

	@Override
	public void deleteBookById(Integer id) {
		System.out.println("delete");

	}

	@Override
	public Book createBook(Book book) {
		return new Book("author", "title", 12d, "contentUrl");
	}

	@Override
	public Book updateBook(Book book) {
		return new Book("author", "title", 12d, "contentUrl");
	}

	@Override
	public Books getAllBooks() {
		List<Book> bookList=new ArrayList<Book>();
		bookList.add(new Book("author1", "title", 12d, "contentUrl"));
		bookList.add(new Book("author2", "title", 12d, "contentUrl"));
		bookList.add(new Book("author3", "title", 12d, "contentUrl"));
		bookList.add(new Book("author4", "title", 12d, "contentUrl"));
		Books books = new Books();
		books.setBookCollection(bookList);
		return books;
	}

}
