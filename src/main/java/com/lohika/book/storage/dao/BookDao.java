package com.lohika.book.storage.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.lohika.book.storage.dao.domain.Book;

/**
 * Declare Books Dao functionality.
 * 
 * @author: vroman
 */
public final class BookDao extends BaseDao<Book> {
    
    /**
     * Initialize {@link BookDao} with Default {@link EntityManager} from
     * {@link DomainManagerFactory}
     */
    public BookDao() {
        super();
    }
    
    /**
     * Initialize {@link BaseDao} with custom {@link EntityManager}
     * 
     * @param entityManager custom initialized entityManager
     */
    public BookDao(final EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * Get book by its id
     * 
     * @param id
     *            unique book identifier
     * @return {@link Book}
     */
    public Book getById(final Integer id) {
        return getEntity(id, Book.class);
    }

    /**
     * Get all available books
     * 
     * @return collection of book
     */
    public List<Book> getAll() {
        TypedQuery<Book> createQuery = getEntityManager().createQuery(
                "Select b from Book b", Book.class);
        List<Book> resultList = createQuery.getResultList();
        return resultList;
    }

}
