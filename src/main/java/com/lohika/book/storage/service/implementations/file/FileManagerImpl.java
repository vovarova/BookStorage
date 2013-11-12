package com.lohika.book.storage.service.implementations.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.lohika.book.storage.api.file.FileManager;

/**
 * @author: vroman
 * Class to manage files (get,remove,store)
 */
public class FileManagerImpl implements FileManager {
    private static final Logger LOGGER = Logger.getLogger(FileManagerImpl.class);
    public static final String fileSeparator = System.getProperty("file.separator");

    public FileManagerImpl() {
    }

    @Override
    public void saveFile(InputStream uploadedInputStream, String fileStorageLocation, String filePath) {
        String[] split = splitFilePath(filePath);
        if (split.length > 1) {
            File directory = new File(concatLocation(fileStorageLocation, split[split.length - 2]));
            try {
                FileUtils.forceMkdir(directory);
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
        String fileLocation = concatLocation(fileStorageLocation, filePath);
        writeToFile(uploadedInputStream, fileLocation);
    }


    @Override
    public File getFile(String fileStorageLocation, String filePath) {
        String fileLocation = concatLocation(fileStorageLocation, filePath);
        return new File(fileLocation);
    }

    @Override
    public boolean deleteFile(String fileStorageLocation, String filePath) {
        boolean deleted = true;
        String fileLocation = concatLocation(fileStorageLocation, filePath);
        try {
            FileUtils.forceDelete(new File(fileLocation));
        } catch (IOException e) {
            deleted = false;
            LOGGER.error(e);
        }
        return deleted;
    }


    @Override
    public boolean deleteFullFilePath(String fileStorageLocation, String filePath) {
        boolean deleted = true;
        String fileLocation = concatLocation(fileStorageLocation, filePath);
        String[] split = splitFilePath(filePath);
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
        OutputStream outputStream;
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
