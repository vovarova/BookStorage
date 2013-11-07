package com.lohika.book.storage.api;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.lohika.book.storage.model.Book;
import com.lohika.book.storage.model.Books;

/**
 * @author vroman
 *         BookServices API is to perform main actions with books entites
 */
public interface BookServices {

    /**
     * Get book by its id
     *
     * @param id unique book identifier
     * @return {@link Book}
     */
    @GET
    @Path("{id}")
    public Book getBookById(@PathParam(value = "id") Integer id);

    /**
     * Delete book by its id
     *
     * @param id unique book identifier
     */

    @DELETE
    @Path("{id}")
    public void deleteBookById(@PathParam(value = "id") Integer id);

    /**
     * Create book in database
     *
     * @param book object to create
     * @return {@link Book}
     */
    @PUT
    @Path("/")
    public Book createBook(Book book);

    /**
     * Update book objects
     *
     * @param book object to update
     * @return {@link Book}
     */
    @POST
    @Path("/")
    public Book updateBook(Book book);

    /**
     * Get all avaliable books
     *
     * @return {@link Books}
     */
    @GET
    @Path("all")
    public Books getAllBooks();
}
