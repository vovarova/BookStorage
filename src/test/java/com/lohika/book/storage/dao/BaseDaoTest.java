package com.lohika.book.storage.dao;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.lohika.book.storage.dao.domain.Book;
import com.lohika.book.storage.dao.mock.EntityManagerMock;

public class BaseDaoTest {
    private BaseDao<Book> baseDao;

    @Before
    public void initialize() {
        baseDao = new BaseDao<Book>(new EntityManagerMock().getEntityManager());
    }

    @Test
    public void testGetEntity() {
        Book entity = new Book();
        assertNull(baseDao.getEntity(entity.getId(), Book.class));
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
        assertNull(baseDao.getEntity(entity.getId(), Book.class));
        baseDao.persistEntity(entity);
        Book persistedEntity = baseDao.getEntity(entity.getId(), Book.class);
        assertNotNull(persistedEntity);
        baseDao.removeEntity(persistedEntity);
    }

    @Test
    public void testMergeEntity() {
        Book entity = new Book();
        entity.setAuthor("Old Author");
        assertNull(baseDao.getEntity(entity.getId(), Book.class));
        baseDao.persistEntity(entity);
        Book persistedEntity = baseDao.getEntity(entity.getId(), Book.class);
        assertNotNull(persistedEntity);
        entity.setAuthor("New Athor");
        assertNotSame(persistedEntity.getAuthor(), entity.getAuthor());
        baseDao.mergeEntity(entity);
        persistedEntity = baseDao.getEntity(entity.getId(), Book.class);
        assertSame(persistedEntity.getAuthor(), entity.getAuthor());
        baseDao.removeEntity(persistedEntity);
    }

}
