package com.lohika.book.storage.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.mockito.Mockito.*;
import org.junit.Test;

public class DomainManagerFactoryTest {

    @Test
    public void testCreateEntityManager() {
        EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);
        when(entityManagerFactory.createEntityManager()).thenReturn(
                mock(EntityManager.class));
        DomainManagerFactory.setEntityManagerFactory(entityManagerFactory);
        EntityManager entityManager = DomainManagerFactory
                .createEntityManager();
        assertNotNull(entityManager);
    }

}
