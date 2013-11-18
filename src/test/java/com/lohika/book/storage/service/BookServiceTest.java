package com.lohika.book.storage.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lohika.book.storage.dao.DomainManagerFactory;
import com.lohika.book.storage.dao.domain.Book;
import com.lohika.book.storage.model.Books;
import com.lohika.book.storage.service.implementation.BookServiceImpl;

public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @BeforeClass
    public static void initializeDbConnections() {
        EntityManagerFactory createEntityManagerFactory = Persistence
                .createEntityManagerFactory("persistenceTest");
        DomainManagerFactory
                .setEntityManagerFactory(createEntityManagerFactory);
    }

    @AfterClass
    public static void closeDbConections() {
        new DomainManagerFactory().contextDestroyed(null);
    }

    @Test
    public void testGetById() {
        Book book = new Book("test", "test", null, null);
        Book persistedBook = bookService.create(book);
        Book bookById = bookService.getById(persistedBook.getId());
        assertNotNull(bookById);
        assertNotNull(bookById.getId());
        assertEquals(persistedBook, bookById);
        bookService.deleteById(bookById.getId());
        bookById = bookService.getById(persistedBook.getId());
        assertNull(bookById);
    }

    @Test
    public void testUpdate() {
        Book book = new Book("test", "test", null, null);
        Book persistedBook = bookService.create(book);
        persistedBook.setAuthor("testAuthor");
        bookService.update(persistedBook);
        Book updatedBook = bookService.getById(persistedBook.getId());
        assertEquals(persistedBook, updatedBook);
        bookService.deleteById(persistedBook.getId());
    }

    @Test
    public void testGetAll() {
        int collectionSize = 10;
        for (int i = 0; i < collectionSize; i++) {
            Book book = new Book("test", "test", null, null);
            bookService.create(book);
        }
        Books allBooks = bookService.getAll();
        assertNotNull(allBooks);
        List<Book> bookCollection = allBooks.getBookCollection();
        for (Book book : bookCollection) {
            bookService.deleteById(book.getId());
        }
        assertNotNull(bookCollection);
        assertEquals(bookCollection.size(), collectionSize);
    }

    @Test
    public void testFileProcessing() {
        Book book = new Book("test", "test", null, null);
        Book persistedBook = bookService.create(book);
        String testText = "Test text";
        String fileName = "test.test";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                testText.getBytes());
        FormDataContentDisposition fileDetail = mock(FormDataContentDisposition.class);
        when(fileDetail.getFileName()).thenReturn(fileName);
        bookService.uploadFile(persistedBook.getId(), byteArrayInputStream,
                fileDetail);
        Response downloadFile = bookService.downloadFile(persistedBook.getId());
        assertEquals(downloadFile.getStatus(), 200);
        Object file = downloadFile.getEntity();
        assertNotNull(file);
        assertTrue(file instanceof File);
        bookService.deleteFile(persistedBook.getId());
        try {
            downloadFile = bookService.downloadFile(persistedBook.getId());
            fail("There must be thrown exception");
        } catch (Exception e) {
            assertTrue(e instanceof NotFoundException);
        }
        bookService.deleteById(persistedBook.getId());
    }
}
