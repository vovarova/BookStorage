package com.lohika.book.storage.service.implementation;

import java.io.File;
import java.io.InputStream;

import com.lohika.book.storage.service.FileManager;

public class FileManagerStub implements FileManager {

    @Override
    public void saveFile(InputStream uploadedInputStream,
            String fileStorageLocation, String fileName) {
        // TODO Auto-generated method stub

    }

    @Override
    public File getFile(String fileStorageLocation, String fileName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean deleteFile(String fileStorageLocation, String fileName) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String concatLocation(String baseLocation, String... chunks) {
        // TODO Auto-generated method stub
        return null;
    }

}
