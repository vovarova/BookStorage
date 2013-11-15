package com.lohika.book.storage.dao.mock;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class EntityManagerMock {
    private EntityManager entityManager;
    private Storage storage = Storage.getInstance();

    public EntityManagerMock() {
        initializeMock();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void initializeMock() {
        entityManager = mock(EntityManager.class);
        when(entityManager.getTransaction()).thenReturn(
                mock(EntityTransaction.class));
        doAnswer(new PersistManagerAnswer()).when(entityManager).persist(any());
        when(entityManager.find((Class<?>) any(), any())).thenAnswer(
                new FindManagerAnswer());
        doAnswer(new DeleteManagerAnswer()).when(entityManager).remove(any());
        when(entityManager.merge(any())).thenAnswer(new PersistManagerAnswer());
        TypedQuery createQuery = mock(TypedQuery.class);
        when(createQuery.getResultList()).thenAnswer(
                new ResultListManagerAnswer());
        when(entityManager.createQuery(anyString(), (Class<?>) any()))
                .thenReturn(createQuery);
    }

    private class PersistManagerAnswer implements Answer<Object> {
        @Override
        public Object answer(InvocationOnMock invocation) {
            Object[] args = invocation.getArguments();
            Object object = args[0];
            Storage.getInstance().persistDomainObject(object);
            return object;
        }
    }

    private class FindManagerAnswer implements Answer<Object> {
        @Override
        public Object answer(InvocationOnMock invocation) {
            Object[] args = invocation.getArguments();
            Object id = args[1];
            if (args[1] == null) {
                return null;
            } else {
                Object domainObject = storage.getDomainObject(id);
                if (domainObject == null
                        || !args[0].equals(domainObject.getClass())) {
                    return null;
                } else {
                    return domainObject;
                }
            }
        }
    }

    private class DeleteManagerAnswer implements Answer<Object> {
        @Override
        public Object answer(InvocationOnMock invocation) {
            Object[] args = invocation.getArguments();
            Object object = args[0];
            storage.deleteDmainObject(object);
            return object;
        }
    }

    @SuppressWarnings("rawtypes")
    private class ResultListManagerAnswer implements Answer<List> {
        @Override
        public List answer(InvocationOnMock invocation) {
            return storage.getAllObject();
        }

    }
}
