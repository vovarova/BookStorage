package com.lohika.book.storage.service.implementations.file;

import java.io.*;
import java.util.regex.Pattern;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.lohika.book.storage.config.ConfigurationKey;
import com.lohika.book.storage.config.Configurator;

/**
 * @author: vroman
 * Class to manage files (get,remove,store)
 */
public class FileManager {
    private static final Logger LOGGER = Logger.getLogger(FileManager.class);
    private static FileManager instance = null;
    private String fileStorageLocation;
    public static final String fileSeparator = System.getProperty("file.separator");

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
     *
     * @param uploadedInputStream stream of uploaded file
     * @param fileName            fileName to store
     */

    public void saveFile(InputStream uploadedInputStream, String fileName) {
        String[] split =  splitFilePath(fileName);
        if (split.length > 1) {
            File directory = new File(concatLocation(fileStorageLocation, split[split.length - 2]));
            try {
                FileUtils.forceMkdir(directory);
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
        String fileLocation = concatLocation(fileStorageLocation, fileName);
        writeToFile(uploadedInputStream, fileLocation);
    }

    /**
     * Get file by its name
     *
     * @param fileName file name
     * @return {@link File}}
     */

    public File getFile(String fileName) {
        String fileLocation = concatLocation(fileStorageLocation, fileName);
        return new File(fileLocation);
    }

    /**
     * Delete only file from the storage but not all parent folders
     *
     * @param fileName file name
     * @return true if file was deleted,or false.
     */
    public boolean deleteFile(String fileName) {
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


    /**
     * Delete all file parent folders to fileStorageLocation
     *
     * @param fileName file name
     * @return true if file was deleted,or false.
     */
    public boolean deleteFullFilePath(String fileName) {
        boolean deleted = true;
        String fileLocation = concatLocation(fileStorageLocation, fileName);
        String[] split = splitFilePath(fileName);
        if (split.length > 1) {
            String fileDirectory = concatLocation(fileStorageLocation, split[0]);
            try {
                FileUtils.deleteDirectory(new File(fileDirectory));
            } catch (IOException e) {
                deleted = false;
                LOGGER.error(e);
            }
        } else {
            try {
                FileUtils.forceDelete(new File(fileLocation));
            } catch (IOException e) {
                deleted = false;
                LOGGER.error(e);
            }
        }
        return deleted;
    }

    /**
     * Contat file location
     * e.g baselocation=D:\file,and chunk=test result will be D:\file\test
     *
     * @param baseLocation base directory location
     * @param chunks       chunks create full file path with baseLocation
     * @return full file path
     */
    public static String concatLocation(String baseLocation, String... chunks) {
        StringBuilder builder = new StringBuilder(baseLocation);
        if (chunks != null) {
            for (String chunk : chunks) {
                builder.append(fileSeparator).append(chunk);
            }
        }
        return builder.toString();
    }

    private String[] splitFilePath(String filePath) {
        String[] result;
        if (filePath != null) {
            result = filePath.split(Pattern.quote(fileSeparator));
        } else {
            result = new String[0];
        }
        return result;
    }

    private void writeToFile(InputStream uploadedInputStream,
                             String uploadedFileLocation) {
        OutputStream outputStream ;
        try {
            outputStream = new FileOutputStream(new File(
                    uploadedFileLocation));
            IOUtils.copy(uploadedInputStream, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

}
