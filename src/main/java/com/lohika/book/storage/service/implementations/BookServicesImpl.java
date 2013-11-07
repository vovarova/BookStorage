package com.lohika.book.storage.service.implementations;

import javax.ws.rs.Path;

import com.lohika.book.storage.api.BookServices;
import com.lohika.book.storage.dao.BooksDao;
import com.lohika.book.storage.model.Book;
import com.lohika.book.storage.model.Books;

@Path("/book")
public class BookServicesImpl implements BookServices {
	
	@Override
	public Book getBookById(Integer id) {
		BooksDao booksDao = new BooksDao();
		Book book = booksDao.getBookById(id);
		return book;
	}

	@Override
	public void deleteBookById(Integer id) {
		BooksDao booksDao = new BooksDao();
		Book book = booksDao.getBookById(id);
		if(book!=null){			
			booksDao.removeEntity(book);
		}
	}

	@Override
	public Book createBook(Book book) {
		BooksDao booksDao = new BooksDao();
		booksDao.persistEntity(book);
		return book;
	}

	@Override
	public Book updateBook(Book book) {
		BooksDao booksDao = new BooksDao();
		booksDao.mergeEntity(book);
		return book;

	}

	@Override
	public Books getAllBooks() {
		return new BooksDao().getAllBooks();
	}

}
