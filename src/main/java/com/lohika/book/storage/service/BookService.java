package com.lohika.book.storage.service;

import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

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
    Book getById(Integer id);

    /**
     * Delete book by its id .
     * 
     * @param id unique book identifier
     */
    void deleteById(Integer id);

    /**
     * Create book in database.
     * 
     * @param book object to create
     * @return {@link Book}
     */
    Book create(Book book);

    /**
     * Update book objects
     * 
     * @param book object to update
     * @return {@link Book}
     */
    Book update(Book book);

    /**
     * Get all avaliable books.
     * 
     * @return {@link Books}
     */
    Books getAll();

    /**
     * Download file by book id.
     * 
     * @param bookId unique book identifier
     * @return {@link Response} appropriate book file
     */
    Response downloadFile(Integer bookId);

    /**
     * Upload file to book storage.
     * 
     * @param bookId unique book identifier
     * @param uploadedInputStream file to upload
     * @param fileDetail file details
     * @return {@link Response} result of uploading
     */
    Response uploadFile(Integer bookId, InputStream uploadedInputStream,
            FormDataContentDisposition fileDetail);

    /**
     * Delete file for book with id = {bookId}.
     * 
     * @param bookId unique book identifier
     * @return {@link Response} result of deleting
     */
    Response deleteFile(Integer bookId);
}
