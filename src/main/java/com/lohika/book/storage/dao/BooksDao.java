package com.lohika.book.storage.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.lohika.book.storage.model.Book;
import com.lohika.book.storage.model.Books;

/**
 * @author: vroman
 * Declare Books Dao functionality
 */
public class BooksDao extends BaseDao {
    public BooksDao() {
        super();
    }

    public BooksDao(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * Get book by its id
     *
     * @param id unique book identifier
     * @return {@link Book}
     */
    public Book getBookById(Integer id) {
        return getEntity(id, Book.class);
    }

    /**
     * Get all available books
     *
     * @return {@link Books}
     */
    public Books getAllBooks() {
        TypedQuery<Book> createQuery = getEntityManager().createQuery("Select b from Book b", Book.class);
        List<Book> resultList = createQuery.getResultList();
        return new Books(resultList);
    }

}
