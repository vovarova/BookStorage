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

    /** File path separator depends what OS we use i.e Windows: '/' */
    public static final String FILE_SEPARATOR = System
            .getProperty("file.separator");

    /**
     * Create {@link FileManagerImpl} instance
     */
    public FileManagerImpl() {
    }

    @Override
    public final void saveFile(final InputStream uploadedInputStream,
            final String fileStorageLocation, final String fileName) {
        final String fileLocation = concatLocation(fileStorageLocation,
                fileName);
        writeToFile(uploadedInputStream, fileLocation);
    }

    @Override
    public final File getFile(final String fileStorageLocation,
            final String fileName) {
        final String fileLocation = concatLocation(fileStorageLocation,
                fileName);
        return new File(fileLocation);
    }

    @Override
    public final boolean deleteFile(final String fileStorageLocation,
            final String fileName) {
        boolean deleted = true;
        final String fileLocation = concatLocation(fileStorageLocation,
                fileName);
        try {
            FileUtils.forceDelete(new File(fileLocation));
        } catch (final IOException e) {
            deleted = false;
            LOGGER.error(e);
        }
        return deleted;
    }

    @Override
    public final String concatLocation(final String baseLocation,
            final String... chunks) {
        final StringBuilder builder = new StringBuilder(baseLocation);
        if (chunks != null) {
            for (final String chunk : chunks) {
                builder.append(FILE_SEPARATOR).append(chunk);
            }
        }
        return builder.toString();
    }

    private void writeToFile(final InputStream uploadedInputStream,
            final String uploadedFileLocation) {
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream(new File(uploadedFileLocation));
            IOUtils.copy(uploadedInputStream, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (final IOException e) {
            LOGGER.error(e);
        }
    }

}
