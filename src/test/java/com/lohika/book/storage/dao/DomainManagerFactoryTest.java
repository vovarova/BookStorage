package com.lohika.book.storage.dao;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.Test;

public class DomainManagerFactoryTest {

    @Test
    public void testCreateEntityManager() {
        EntityManager entityManager = DomainManagerFactory
                .createEntityManager();
        assertNotNull(entityManager);
    }

}
