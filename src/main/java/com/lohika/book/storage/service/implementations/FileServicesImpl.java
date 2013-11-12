package com.lohika.book.storage.service.implementations;

import java.io.File;
import java.io.InputStream;

import com.lohika.book.storage.api.FileServices;
import com.lohika.book.storage.api.file.FileManager;
import com.lohika.book.storage.config.ConfigurationKey;
import com.lohika.book.storage.config.Configurator;
import com.lohika.book.storage.model.Book;
import com.lohika.book.storage.service.implementations.file.FileManagerImpl;

public class FileServicesImpl implements FileServices {
    private static final String FILE_STORAGE = Configurator.getInstance()
            .getProperty(ConfigurationKey.FILE_STORAGE_URL);
    private FileManager fileManager;

    public FileServicesImpl() {
        fileManager = new FileManagerImpl();
    }

    public FileServicesImpl(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void saveFile(InputStream uploadedInputStream, String fileName,
                         Book book) {
        String filePath = getFilePath(book.getId(), fileName);
        fileManager.saveFile(uploadedInputStream, FILE_STORAGE, filePath);

    }

    @Override
    public File getFile(Book book) {
        File resultFile = null;
        String filePath = getFilePath(book);
        if (filePath != null) {
            resultFile = fileManager.getFile(FILE_STORAGE, filePath);
        }
        return resultFile;
    }

    @Override
    public boolean deleteFile(Book book) {
        String filePath = getFilePath(book);
        boolean deleted = false;
        if (filePath != null) {
            deleted = fileManager.deleteFile(FILE_STORAGE, filePath);
        } else {
            throw new NullPointerException("Book " + book
                    + "has invalid file path");
        }
        return deleted;
    }

    @Override
    public boolean deleteFullFilePath(Book book) {
        String filePath = getFilePath(book);
        boolean deleted = false;
        if (filePath != null) {
            deleted = fileManager.deleteFullFilePath(FILE_STORAGE, filePath);
        }
        return deleted;
    }

    private String getFilePath(Integer bookId, String fileName) {
        String filePath = fileManager.concatLocation(String.valueOf(bookId),
                fileName);
        return filePath;
    }

    private String getFilePath(Book book) {
        String fileName = book.getFileName();
        String filePath = null;
        if (fileName != null) {
            filePath = fileManager.concatLocation(String.valueOf(book.getId()),
                    fileName);
        }
        return filePath;
    }

}
