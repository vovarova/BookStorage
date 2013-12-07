package com.lohika.book.storage.service.implementation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.lohika.book.storage.service.FileService;
import com.lohika.book.storage.service.FileManager;
import com.lohika.book.storage.config.ConfigurationKey;
import com.lohika.book.storage.config.Configurator;
import com.lohika.book.storage.dao.domain.Book;

/**
 * FileService implementing store files in file system
 * 
 * @author vroman
 */
public class FileServiceImpl implements FileService {

    private static final String FILE_STORAGE = getFileStorage(); 
    private static final Logger LOGGER = Logger.getLogger(FileServiceImpl.class);
    private final FileManager fileManager;

    /** 
     * Initialize {@link FileService} with Default FileManager
     */
    public FileServiceImpl() {
        fileManager = new FileManagerImpl();
    }
    
    private static String getFileStorage() {
        String fileStoragePath = Configurator.getInstance().getProperty(
                ConfigurationKey.FILE_STORAGE_URL);
        File fileStorage = new File(fileStoragePath);
        if (!fileStorage.exists()) {
            try {
                FileUtils.forceMkdir(fileStorage);
            } catch (IOException e) {
                fileStoragePath = "..";
                LOGGER.error(e);
            }
        }
        return fileStoragePath;
    }

    /**
     * Initialize {@link FileService} with parameter {@link FileManager}
     * 
     * @param fileManager custom {@link FileManager}
     */
    public FileServiceImpl(final FileManager fileManager) {
        this.fileManager = fileManager;
    }
    

    @Override
    public final void saveFile(final InputStream uploadedInputStream,
            final String fileName, final Book book) {
        fileManager.saveFile(uploadedInputStream, FILE_STORAGE,
                String.valueOf(book.getId()));
    }

    @Override
    public final File getFile(final Book book) {
        File resultFile = null;
        if (book.getFileName() != null) {
            resultFile = fileManager.getFile(FILE_STORAGE,
                    String.valueOf(book.getId()));
        }
        return resultFile;
    }

    @Override
    public final boolean deleteFile(final Book book) {
        boolean deleted = false;
        if (book.getFileName() != null) {
            deleted = fileManager.deleteFile(FILE_STORAGE,
                    String.valueOf(book.getId()));
        }
        return deleted;
    }
}
