package com.lohika.book.storage.api;

import com.lohika.book.storage.model.Book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author vroman
 * BookService is to perform main actions with books entites
 */
@Path("book")
public interface BooksService {

    @GET
    @Produces()
    public Book getBookById(Integer id);


}
