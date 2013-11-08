package com.lohika.book.storage.service.implementations.file;

import com.lohika.book.storage.config.ConfigurationKey;
import com.lohika.book.storage.config.Configurator;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * @author: vroman
 * Class to manage files (get,remove,store)
 */
public class FileManager {
    private static final Logger LOGGER = Logger.getLogger(FileManager.class);
    private static FileManager instance = null;
    private String fileStorageLocation;

    private FileManager() {
        String fileStorage = Configurator.getInstance().getProperty(ConfigurationKey.FILE_STORAGE_URL);
        File file = new File(fileStorage);
        if (file.exists()) {

            fileStorageLocation = fileStorage;
        } else {
            throw new RuntimeException("There no such file directory");
        }
    }

    public static FileManager getInstance() {
        if (instance == null) {
            synchronized (Configurator.class) {
                if (instance == null) {
                    instance = new FileManager();
                }
            }
        }
        return instance;
    }

    public void saveFile(InputStream uploadedInputStream, String fileName) {
        try {
            String fileLocation = fileStorageLocation + "//" + fileName;
            OutputStream outputStream = new FileOutputStream(new File(fileLocation));
            IOUtils.copy(uploadedInputStream, outputStream);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
