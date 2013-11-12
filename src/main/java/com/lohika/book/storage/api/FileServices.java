package com.lohika.book.storage.api;

import java.io.File;
import java.io.InputStream;

import com.lohika.book.storage.model.Book;

/**
 * FileServices API defines main file functionality
 * @author vroman
 *
 */
public interface FileServices {

	/**
	 * Save file to storage
	 *
	 * @param uploadedInputStream stream of uploaded file
	 * @param fileName            fileName to store
	 * @param book book entity to link file to
	 */

	public void saveFile(InputStream uploadedInputStream,
			String fileName, Book book);

	/**
	 * Get file linked to book
	 * @param book Book entity
	 * @return {@link File}}
	 */

	public File getFile(Book book);

	/**
	 * Delete only file from the storage but not all parent folders
	 *
	 * @param book Book entity
	 * @return true if file was deleted,or false.
	 */
	public boolean deleteFile(Book book);

	/**
	 * Delete all file parent folders
	 *
	 * @param book Book entity
	 * @return true if file was deleted,or false.
	 */
	public boolean deleteFullFilePath(Book book);

}