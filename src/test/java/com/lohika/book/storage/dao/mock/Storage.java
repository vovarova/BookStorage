package com.lohika.book.storage.dao.mock;

import java.io.InvalidObjectException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {
    private final static Map<Object, Object> domainStorage = new HashMap<Object, Object>();

    private static Storage instance = new Storage();

    private Storage() {
    }

    public static Storage getInstance() {
        return instance;
    }

    public Object getDomainObject(Object key) {
        return domainStorage.get(key);
    }

    public List<Object> getAllObject() {
        return new ArrayList<Object>(domainStorage.values());
    }

    public void deleteDmainObject(Object object) {
        domainStorage.remove(getIdFromObject(object));
    }

    public Object persistDomainObject(Object object) {
        Integer id = domainStorage.size();
        Integer objectId = getIdFromObject(object);
        if (objectId != null) {
            id = objectId;
        } else {
            setIdToObject(object, id);
        }
        domainStorage.put(id, cloneObject(object));
        return object;
    }

    private void setIdToObject(Object object, Integer id) {
        Class<? extends Object> clazz = object.getClass();
        try {
            Method setId = clazz.getDeclaredMethod("setId", Integer.class);
            setId.invoke(object, id);
        } catch (Exception e) {
            new InvalidObjectException("Error while invoking getId method");
        }
    }

    private Integer getIdFromObject(Object object) {
        Class<? extends Object> clazz = object.getClass();
        Integer id = null;
        try {
            Method getId = clazz.getDeclaredMethod("getId");
            id = (Integer) getId.invoke(object);
        } catch (Exception e) {
            new InvalidObjectException("Error while invoking getId method");
        }
        return id;
    }

    private Object cloneObject(Object object) {
        Class<? extends Object> clazz = object.getClass();
        Object clone = null;
        try {
            Constructor<? extends Object> constructor = clazz
                    .getDeclaredConstructor(clazz);
            clone = constructor.newInstance(object);
        } catch (Exception e) {
            new InvalidObjectException("Error while invoking getId method");
        }
        return clone;

    }
}
