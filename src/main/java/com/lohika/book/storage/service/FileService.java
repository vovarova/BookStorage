package com.lohika.book.storage.service;

import java.io.File;
import java.io.InputStream;

import com.lohika.book.storage.dao.domain.Book;

/**
 * FileService API defines main file functionality.
 * 
 * @author vroman
 */
public interface FileService {

    /**
     * Save file to storage.
     * 
     * @param uploadedInputStream stream of uploaded file
     * @param fileName fileName to store
     * @param book book entity to link file to
     */
    void saveFile(InputStream uploadedInputStream, String fileName, Book book);

    /**
     * Get file linked to book.
     * 
     * @param book Book entity
     * @return {@link File}
     */
    File getFile(Book book);

    /**
     * Delete file from the storage.
     * 
     * @param book Book entity
     * @return true if file was deleted,or false.
     */
    boolean deleteFile(Book book);
}