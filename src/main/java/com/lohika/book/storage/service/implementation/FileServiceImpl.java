package com.lohika.book.storage.service.implementation;

import java.io.File;
import java.io.InputStream;

import com.lohika.book.storage.service.FileService;
import com.lohika.book.storage.service.FileManager;
import com.lohika.book.storage.config.ConfigurationKey;
import com.lohika.book.storage.config.Configurator;
import com.lohika.book.storage.domain.Book;

public class FileServiceImpl implements FileService {

    private static final String FILE_STORAGE = Configurator.getInstance()
	    .getProperty(ConfigurationKey.FILE_STORAGE_URL);
    private FileManager fileManager;

    public FileServiceImpl() {
	fileManager = new FileManagerImpl();
    }

    public FileServiceImpl(FileManager fileManager) {
	this.fileManager = fileManager;
    }

    @Override
    public void saveFile(InputStream uploadedInputStream, String fileName,
	    Book book) {
	fileManager.saveFile(uploadedInputStream, FILE_STORAGE,
		String.valueOf(book.getId()));
    }

    @Override
    public File getFile(Book book) {
	File resultFile = null;
	if (book.getFileName() != null) {
	    resultFile = fileManager.getFile(FILE_STORAGE,
		    String.valueOf(book.getId()));
	}
	return resultFile;
    }

    @Override
    public boolean deleteFile(Book book) {
	boolean deleted = false;
	if (book.getFileName() != null) {
	    deleted = fileManager.deleteFile(FILE_STORAGE,
		    String.valueOf(book.getId()));
	}
	return deleted;
    }

}
