package com.lohika.book.storage.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lohika.book.storage.model.Book;
import com.lohika.book.storage.model.BookGenre;

/**
 * @author vroman
 *         Test service
 */
@Path("/")
public class MainService {
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Book getIndexPage() {
    	return new Book("author", "title", 12d, "contentUrl",new BookGenre("sdsd"));  
    }
}
