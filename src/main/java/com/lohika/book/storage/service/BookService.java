package com.lohika.book.storage.service;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.lohika.book.storage.dao.domain.Book;
import com.lohika.book.storage.model.Books;

/**
 * BookService API is to perform main actions with books entites.
 * 
 * @author vroman
 */
public interface BookService {

    /**
     * Get book by its id.
     * 
     * @param id unique book identifier
     * @return {@link Book}
     */
    @GET
    @Path("{id}")
    Book getById(@PathParam(value = "id") Integer id);

    /**
     * Delete book by its id .
     * 
     * @param id unique book identifier
     */

    @DELETE
    @Path("{id}")
    void deleteById(@PathParam(value = "id") Integer id);

    /**
     * Create book in database.
     * 
     * @param book object to create
     * @return {@link Book}
     */
    @POST
    @Path("/")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    Book create(Book book);

    /**
     * Update book objects
     * 
     * @param book object to update
     * @return {@link Book}
     */
    @PUT
    @Path("/")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    Book update(Book book);

    /**
     * Get all avaliable books.
     * 
     * @return {@link Books}
     */
    @GET
    @Path("all")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    Books getAll();

    /**
     * Download file by book id.
     * 
     * @param bookId unique book identifier
     * @return {@link Response} appropriate book file
     */
    @GET
    @Path("file/{bookId}")
    Response downloadFile(@PathParam(value = "bookId") Integer bookId);

    /**
     * Upload file to book storage.
     * 
     * @param bookId unique book identifier
     * @param uploadedInputStream file to upload
     * @param fileDetail file details
     * @return {@link Response} result of uploading
     */
    @POST
    @Path("file/{bookId}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    Response uploadFile(@PathParam(value = "bookId") Integer bookId,
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail);

    /**
     * Delete file for book with id = {bookId}.
     * 
     * @param bookId unique book identifier
     * @return {@link Response} result of deleting
     */
    @DELETE
    @Path("file/{bookId}")
    Response deleteFile(@PathParam(value = "bookId") Integer bookId);
}
