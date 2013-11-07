package com.lohika.book.storage.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.lohika.book.storage.model.Book;
import com.lohika.book.storage.model.Books;

/**
 * @author: vroman
 */
public class BooksDao extends BaseDao {
    public BooksDao() {
        super();
    }

    public BooksDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Book getBookById(Integer id){
       return getEntity(id,Book.class);
    }
    
    public Books getAllBooks(){
    	TypedQuery<Book> createQuery = getEntityManager().createQuery("Select b from Book b",Book.class);
    	List<Book> resultList = createQuery.getResultList();
    	return new Books(resultList);
    }

}
