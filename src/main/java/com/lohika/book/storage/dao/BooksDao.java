package com.lohika.book.storage.dao;

import com.lohika.book.storage.model.Book;

import javax.persistence.EntityManager;

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

}
