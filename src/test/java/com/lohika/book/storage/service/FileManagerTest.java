package com.lohika.book.storage.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.lohika.book.storage.config.ConfigurationKey;
import com.lohika.book.storage.config.Configurator;
import com.lohika.book.storage.service.FileManager;
import com.lohika.book.storage.service.implementation.FileManagerImpl;

public class FileManagerTest {

    private FileManager fileManager = new FileManagerImpl();
    private String fileStorage = Configurator.getInstance().getProperty(
            ConfigurationKey.FILE_STORAGE_URL);;
    private String fileName = "test.test";
    private static final String FILE_SEPARATOR = System
            .getProperty("file.separator");

    @Test
    public void testFileProcessingScenario() throws IOException {
        String testText = "Thids is test text";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                testText.getBytes());
        File file = fileManager.getFile(fileStorage, fileName);
        assertNotNull(file);
        assertFalse(file.exists());
        fileManager.saveFile(byteArrayInputStream, fileStorage, fileName);
        file = fileManager.getFile(fileStorage, fileName);
        assertNotNull(file);
        assertTrue(file.exists());
        fileManager.deleteFile(fileStorage, fileName);
        file = fileManager.getFile(fileStorage, fileName);
        assertNotNull(file);
        assertFalse(file.exists());
    }

    @Test
    public void testConcatLocation() {
        String baseLocation = "baseLocation";
        String chunk1 = "chunk1";
        String chunk2 = "chunk2";
        String expectedResult = baseLocation + FILE_SEPARATOR + chunk1
                + FILE_SEPARATOR + chunk2;
        String actualResult = fileManager.concatLocation(baseLocation, chunk1,
                chunk2);
        assertNotNull(actualResult);
        assertEquals(expectedResult, actualResult);
    }
}
