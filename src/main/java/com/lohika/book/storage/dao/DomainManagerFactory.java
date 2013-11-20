package com.lohika.book.storage.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lohika.book.storage.utils.Constants;

/**
 * This is custom entityManagerFactory factory.
 * 
 * @author vroman
 */
public final class DomainManagerFactory implements ServletContextListener {
    private static EntityManagerFactory entityManagerFactory;

    @Override
    public void contextInitialized(final ServletContextEvent event) {
        entityManagerFactory = Persistence
                .createEntityManagerFactory(Constants.PERSISTENCE_UNIT);
    }

    @Override
    public void contextDestroyed(final ServletContextEvent event) {
        entityManagerFactory.close();
    }

    /**
     * Create entity manager
     * 
     * @return {@link EntityManager}
     */
    public static EntityManager createEntityManager() {
        if (entityManagerFactory == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }
        return entityManagerFactory.createEntityManager();
    }

    public static void setEntityManagerFactory(
            final EntityManagerFactory entityManagerFactory) {
        DomainManagerFactory.entityManagerFactory = entityManagerFactory;
    }

}
