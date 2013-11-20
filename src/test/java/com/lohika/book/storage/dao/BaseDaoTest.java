package com.lohika.book.storage.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lohika.book.storage.dao.domain.Book;

public class BaseDaoTest {
    private BaseDao<Book> baseDao=new BaseDao<Book>();
    
    @Test
    public void testGetEntity() {
        Book entity = new Book();
        try{            
            baseDao.getEntity(entity.getId(), Book.class);
        }catch(Exception e){
            assertTrue(e instanceof IllegalArgumentException);
        }
        baseDao.persistEntity(entity);
        Book persistedEntity = baseDao.getEntity(entity.getId(), Book.class);
        assertEquals(entity.getId(), persistedEntity.getId());
        baseDao.removeEntity(persistedEntity);
    }

    @Test
    public void testRemoveEntity() {
        Book entity = new Book();
        baseDao.persistEntity(entity);
        Book persistedEntity = baseDao.getEntity(entity.getId(), Book.class);
        assertNotNull(persistedEntity);
        baseDao.removeEntity(persistedEntity);
        Book removedEntity = baseDao.getEntity(entity.getId(), Book.class);
        assertNull(removedEntity);
    }

    @Test
    public void testPersistEntity() {
        Book entity = new Book();
        baseDao.persistEntity(entity);
        Book persistedEntity = baseDao.getEntity(entity.getId(), Book.class);
        assertNotNull(persistedEntity);
        baseDao.removeEntity(persistedEntity);
    }

    @Test
    public void testMergeEntity() {
        Book entity = new Book();
        entity.setAuthor("Old Author");
        baseDao.persistEntity(entity);
        Book persistedEntity = baseDao.getEntity(entity.getId(), Book.class);
        assertNotNull(persistedEntity);
        entity.setAuthor("New Athor");
        baseDao.mergeEntity(entity);
        persistedEntity = baseDao.getEntity(entity.getId(), Book.class);
        assertEquals(persistedEntity.getAuthor(), entity.getAuthor());
        baseDao.removeEntity(persistedEntity);
    }

}
