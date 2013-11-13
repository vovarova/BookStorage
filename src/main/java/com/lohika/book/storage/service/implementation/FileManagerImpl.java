package com.lohika.book.storage.service.implementation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.lohika.book.storage.service.FileManager;

/**
 * Class to manage files (get,remove,store).
 * 
 * @author: vroman
 */
public class FileManagerImpl implements FileManager {

    private static final Logger LOGGER = Logger
	    .getLogger(FileManagerImpl.class);

    public static final String fileSeparator = System
	    .getProperty("file.separator");

    public FileManagerImpl() {
    }

    @Override
    public void saveFile(InputStream uploadedInputStream,
	    String fileStorageLocation, String fileName) {
	String fileLocation = concatLocation(fileStorageLocation, fileName);
	writeToFile(uploadedInputStream, fileLocation);
    }

    @Override
    public File getFile(String fileStorageLocation, String fileName) {
	String fileLocation = concatLocation(fileStorageLocation, fileName);
	return new File(fileLocation);
    }

    @Override
    public boolean deleteFile(String fileStorageLocation, String fileName) {
	boolean deleted = true;
	String fileLocation = concatLocation(fileStorageLocation, fileName);
	try {
	    FileUtils.forceDelete(new File(fileLocation));
	} catch (IOException e) {
	    deleted = false;
	    LOGGER.error(e);
	}
	return deleted;
    }

    @Override
    public String concatLocation(String baseLocation, String... chunks) {
	StringBuilder builder = new StringBuilder(baseLocation);
	if (chunks != null) {
	    for (String chunk : chunks) {
		builder.append(fileSeparator).append(chunk);
	    }
	}
	return builder.toString();
    }

    private void writeToFile(InputStream uploadedInputStream,
	    String uploadedFileLocation) {
	OutputStream outputStream;
	try {
	    outputStream = new FileOutputStream(new File(uploadedFileLocation));
	    IOUtils.copy(uploadedInputStream, outputStream);
	    outputStream.flush();
	    outputStream.close();
	} catch (IOException e) {
	    LOGGER.error(e);
	}
    }

}
