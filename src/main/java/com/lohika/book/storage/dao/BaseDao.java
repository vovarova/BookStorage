package com.lohika.book.storage.dao;

import javax.persistence.EntityManager;

/**
 * This class is to declare main DAO functionality.
 * 
 * @author : vroman
 * @param <T> must be domain entity
 */
public class BaseDao<T> {
    private EntityManager entityManager;

    /**
     * Initialize {@link BaseDao} with Default {@link EntityManager} from
     * {@link DomainManagerFactory}
     */
    public BaseDao() {
        entityManager = DomainManagerFactory.createEntityManager();
    }

    /**
     * Initialize {@link BaseDao} with custom {@link EntityManager}
     * 
     * @param entityManager custom initialized entityManager
     */
    public BaseDao(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Get entity by its id
     * 
     * @param id unique object identifier
     * @param clazz <T> class of object to look for
     * @return T
     */
    public T getEntity(final Object id, final Class<T> clazz) {
        return entityManager.find(clazz, id);
    }

    /**
     * Delete entity
     * 
     * @param entity object to remove
     */
    public void removeEntity(final T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    /**
     * Persists entity to database
     * 
     * @param entity object to persist
     */
    public void persistEntity(final T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    /**
     * Merge entity
     * 
     * @param entity object to merge
     */
    public void mergeEntity(final T entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    public final EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
