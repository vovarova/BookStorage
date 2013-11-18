package com.lohika.book.storage.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;

import org.junit.Test;

import com.lohika.book.storage.dao.domain.Book;
import com.lohika.book.storage.service.implementation.FileServiceImpl;

public class FileServiceTest {
    private FileService fileService = new FileServiceImpl();
    String testText = "Thids is test text";
    String fileName = "test.test";

    @Test
    public void testFileProcessing() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                testText.getBytes());
        Book book = new Book();
        book.setId(-1);
        book.setFileName(fileName);
        File file = fileService.getFile(book);
        assertNotNull(file);
        assertFalse(file.exists());
        fileService.saveFile(byteArrayInputStream, fileName, book);
        file = fileService.getFile(book);
        assertNotNull(file);
        assertTrue(file.exists());
        fileService.deleteFile(book);
        file = fileService.getFile(book);
        assertNotNull(file);
        assertFalse(file.exists());
    }

}
