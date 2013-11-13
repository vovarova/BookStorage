package com.lohika.book.storage.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.lohika.book.storage.domain.Book;
import com.lohika.book.storage.model.Books;

/**
 * Declare Books Dao functionality.
 * 
 * @author: vroman
 */
public class BooksDao extends BaseDao<Book> {

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
    public Book getById(Integer id) {
	return getEntity(id, Book.class);
    }

    /**
     * Get all available books
     * 
     * @return {@link Books}
     */
    public Books getAll() {
	TypedQuery<Book> createQuery = getEntityManager().createQuery(
		"Select b from Book b", Book.class);
	List<Book> resultList = createQuery.getResultList();
	return new Books(resultList);
    }

}
