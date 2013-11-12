package com.lohika.book.storage.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lohika.book.storage.model.Book;
import com.lohika.book.storage.model.Books;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.io.InputStream;

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
    @Consumes(MediaType.APPLICATION_XML)
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


    /**
     * Download file by book id
     *
     * @param bookId unique book identifier
     * @return {@link Response} appropriate book file
     */
    @GET
    @Path("file/{bookId}")
    public Response downloadBookFile(@PathParam(value = "bookId") Integer bookId);

    /**
     * Upload file to book storage
     *
     * @param bookId              unique book identifier
     * @param uploadedInputStream file to upload
     * @param fileDetail          file details
     * @return {@link Response} result of uploading
     */
    @POST
    @Path("file/{bookId}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadBookFile(@PathParam(value = "bookId") Integer bookId, @FormDataParam("file") InputStream uploadedInputStream,
                                   @FormDataParam("file") FormDataContentDisposition fileDetail);

    /**
     * Delete file for book with id = {bookId}
     *
     * @param bookId unique book identifier
     * @return {@link Response} result of deleting
     */
    @DELETE
    @Path("file/{bookId}")
    public Response deleteBookFile(@PathParam(value = "bookId") Integer bookId);
}
