package com.lohika.book.storage.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.lohika.book.storage.dao.domain.Book;

public class BookDaoTest {
    private BookDao bookDao=new BookDao();
    
    @Test
    public void testGetById() {
        Book book = new Book();
        bookDao.persistEntity(book);
        Book persistedBook = bookDao.getById(book.getId());
        assertEquals(book.getId(), persistedBook.getId());
        bookDao.removeEntity(book);
    }

    @Test
    public void testGetAll() {
        List<Book> all = bookDao.getAll();
        assertTrue(all.isEmpty());
        int numberBooks = 10;
        for (int i = 0; i < numberBooks; i++) {
            bookDao.persistEntity(new Book());
        }
        all = bookDao.getAll();
        assertFalse(all.isEmpty());
        assertEquals(numberBooks, all.size());
        for (Book book : all) {
            bookDao.removeEntity(book);
        }
    }

}
