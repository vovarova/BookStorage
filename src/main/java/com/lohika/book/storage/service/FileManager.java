package com.lohika.book.storage.service;

import java.io.File;
import java.io.InputStream;

public interface FileManager {

    /**
     * Save file to storage
     * 
     * @param uploadedInputStream stream of uploaded file
     * @param fileStorageLocation location of file storage
     * @param fileName name of file in the fileStorage
     */

    void saveFile(InputStream uploadedInputStream, String fileStorageLocation,
	    String fileName);

    /**
     * Get file by its name
     * 
     * @param fileStorageLocation location of file storage
     * @param fileName fileName in fileStorage
     * @return {@link File}
     */

    File getFile(String fileStorageLocation, String fileName);

    /**
     * Delete only file from the storage but not all parent folders
     * 
     * @param fileStorageLocation location of file storage
     * @param fileName name of file in the fileStorage
     * @return true if file was deleted,or false.
     */
    boolean deleteFile(String fileStorageLocation, String fileName);

    /**
     * Contat file location e.g baselocation=D:\file,and chunk=test result will
     * be D:\file\test
     * 
     * @param baseLocation base directory location
     * @param chunks chunks create full file path with baseLocation
     * @return full file path
     */
    String concatLocation(String baseLocation, String... chunks);

}