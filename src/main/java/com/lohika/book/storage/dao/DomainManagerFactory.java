package com.lohika.book.storage.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * This is custom entityManagerFactory factory.
 * 
 * @author vroman
 */
public final class DomainManagerFactory implements ServletContextListener {

    private static final String PERSISTENCE_UNIT = "persistenceUnit";
    private static EntityManagerFactory emf;

    @Override
    public void contextInitialized(final ServletContextEvent event) {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }

    @Override
    public void contextDestroyed(final ServletContextEvent event) {
        emf.close();
    }

    /**
     * Create entity manager
     * 
     * @return {@link EntityManager}
     */
    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }
        return emf.createEntityManager();
    }

}
