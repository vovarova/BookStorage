package com.lohika.book.storage.dao;

import com.lohika.book.storage.listeners.EMF;

import javax.persistence.EntityManager;

/**
 * @author : vroman
 *         This class is to declare main DAO functionality
 */

public class BaseDao {
    private EntityManager entityManager = null;

    public BaseDao() {
        entityManager = EMF.createEntityManager();
    }

    public BaseDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Get entity by its id
     *
     * @param id       unique object identifier
     * @param clazz<T> class of object to look for
     * @return T
     */
    public <T> T getEntity(Object id, Class<T> clazz) {
        return entityManager.find(clazz, id);
    }

    /**
     * Delete entity
     *
     * @param entity object to remove
     */
    public void removeEntity(Object entity) {
        entityManager.remove(entity);
    }

    /**
     * Persists entity to database
     *
     * @param entity object to persist
     */
    public void persistEntity(Object entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    /**
     * Merge entity
     *
     * @param entity object to merge
     */
    public void mergeEntity(Object entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


}
