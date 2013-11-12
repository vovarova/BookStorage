package com.lohika.book.storage.api.file;

import java.io.File;
import java.io.InputStream;

public interface FileManager {

	/**
	 * Save file to storage
	 *
	 * @param uploadedInputStream stream of uploaded file
	 * @param fileStorageLocation location of file storage
	 * @param filePath            filePath in fileStorage
	 */

	public void saveFile(InputStream uploadedInputStream,String fileStorageLocation,
			String filePath);

	/**
	 * Get file by its name
	 *
	 * @param fileStorageLocation location of file storage
	 * @param filePath	filePath in fileStorage
	 * @return {@link File}}
	 */

	public File getFile(String fileStorageLocation,String filePath);

	/**
	 * Delete only file from the storage but not all parent folders
	 *
	 * @param fileStorageLocation location of file storage
	 * @param filePath	filePath in fileStorage
	 * @return true if file was deleted,or false.
	 */
	public boolean deleteFile(String fileStorageLocation,String filePath);

	/**
	 * Delete all file parent folders from fileStorageLocation to filePath
	 *
	 * @param fileStorageLocation location of file storage
	 * @param filePath file name
	 * @return true if file was deleted,or false.
	 */
	public boolean deleteFullFilePath(String fileStorageLocation,String filePath);
	
	 /**
     * Contat file location
     * e.g baselocation=D:\file,and chunk=test result will be D:\file\test
     *
     * @param baseLocation base directory location
     * @param chunks       chunks create full file path with baseLocation
     * @return full file path
     */
    public String concatLocation(String baseLocation, String... chunks);

}