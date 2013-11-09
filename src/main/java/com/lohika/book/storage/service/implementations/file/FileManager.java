package com.lohika.book.storage.service.implementations.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.lohika.book.storage.config.ConfigurationKey;
import com.lohika.book.storage.config.Configurator;

/**
 * @author: vroman Class to manage files (get,remove,store)
 */
public class FileManager {
	private static final Logger LOGGER = Logger.getLogger(FileManager.class);
	private static FileManager instance = null;
	private String fileStorageLocation;

	private FileManager() {
		String fileStorage = Configurator.getInstance().getProperty(
				ConfigurationKey.FILE_STORAGE_URL);
		File file = new File(fileStorage);
		if (file.exists()) {
			fileStorageLocation = fileStorage;
		} else {
			throw new RuntimeException("There no such file directory");
		}
	}

	public static FileManager getInstance() {
		if (instance == null) {
			synchronized (FileManager.class) {
				if (instance == null) {
					instance = new FileManager();
				}
			}
		}
		return instance;
	}

	/**
	 * Save file to storage
	 * @param uploadedInputStream stream of uploaded file
	 * @param fileName fileName to store
	 */
	
	public void saveFile(InputStream uploadedInputStream, String fileName) {
		try {
			String fileLocation = fileStorageLocation + "\\" + fileName;
			OutputStream outputStream = new FileOutputStream(new File(
					fileLocation));
			IOUtils.copy(uploadedInputStream, outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			LOGGER.error(e);
		}
	}

	/**
	 * Get file by its name
	 * @param fileName
	 * @return {@link File}}
	 */
	public File getFile(String fileName) {
		String fileLocation = fileStorageLocation + "\\" + fileName;
		return new File(fileLocation);
	}

	/**
	 * Delete file from the storage
	 * @param fileName
	 * @return true if file was deleted,or false.
	 */
	public boolean deleteFile(String fileName) {
		String fileLocation = fileStorageLocation + "\\" + fileName;
		File file = new File(fileLocation);
		return file.delete();
	}

}
