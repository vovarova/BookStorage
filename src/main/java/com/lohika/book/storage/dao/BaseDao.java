package com.lohika.book.storage.dao;

import javax.persistence.EntityManager;

/**
 * This class is to declare main DAO functionality.
 * 
 * @author : vroman
 */

public class BaseDao<T> {

    private EntityManager entityManager = null;

    public BaseDao() {
	entityManager = DomainManagerFactory.createEntityManager();
    }

    public BaseDao(EntityManager entityManager) {
	this.entityManager = entityManager;
    }

    /**
     * Get entity by its id
     * 
     * @param id unique object identifier
     * @param clazz <T> class of object to look for
     * @return T
     */
    public T getEntity(Object id, Class<T> clazz) {
	return entityManager.find(clazz, id);
    }

    /**
     * Delete entity
     * 
     * @param entity object to remove
     */
    public void removeEntity(T entity) {
	entityManager.getTransaction().begin();
	entityManager.remove(entity);
	entityManager.getTransaction().commit();
    }

    /**
     * Persists entity to database
     * 
     * @param entity object to persist
     */
    public void persistEntity(T entity) {
	entityManager.getTransaction().begin();
	entityManager.persist(entity);
	entityManager.getTransaction().commit();
    }

    /**
     * Merge entity
     * 
     * @param entity object to merge
     */
    public void mergeEntity(T entity) {
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
