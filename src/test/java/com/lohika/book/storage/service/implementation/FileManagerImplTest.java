package com.lohika.book.storage.service.implementation;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.lohika.book.storage.service.FileManager;

public class FileManagerImplTest {

    private FileManager fileManager = new FileManagerImpl();
    private String fileStorage = "";
    private String fileName = "test.txt";
    public static final String FILE_SEPARATOR = System
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
        String baseLocation="baseLocation";
        String chunk1="chunk1";
        String chunk2="chunk2";
        String expectedResult=baseLocation+FILE_SEPARATOR+chunk1+FILE_SEPARATOR+chunk2;
        String actualResult= fileManager.concatLocation(baseLocation, chunk1,chunk2);
        assertNotNull(actualResult);
        assertEquals(expectedResult,actualResult);        
    }
}
