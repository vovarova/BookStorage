package com.lohika.book.storage.service.implementation;

import java.io.File;
import java.io.InputStream;

import com.lohika.book.storage.service.FileManager;

/**
 * This is fileManager stub
 * 
 * @author vroman
 * 
 */
public class FileManagerStub implements FileManager {

    @Override
    public void saveFile(final InputStream uploadedInputStream,
            final String fileStorageLocation, final String fileName) {
        // TODO Auto-generated method stub

    }

    @Override
    public File getFile(final String fileStorageLocation, final String fileName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean deleteFile(final String fileStorageLocation,
            final String fileName) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String concatLocation(final String baseLocation,
            final String... chunks) {
        // TODO Auto-generated method stub
        return null;
    }

}
