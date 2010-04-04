package com.ft.hibernate.support;

import org.hibernate.CallbackException;
import org.hibernate.EntityMode;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;

import org.hibernate.type.Type;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChainedInterceptor implements Interceptor {
    private Interceptor[] interceptors;

    public boolean onLoad(
        Object entity, Serializable id, Object[] state, String[] propertyNames,
        Type[] types) throws CallbackException {
        boolean result = false;

        for (int i = 0; i < interceptors.length; i++) {
            if (
                interceptors[i].onLoad(
                        entity, id, state, propertyNames, types)) {
                /*
                 * Returns true if one interceptor in the chain has modified the
                 * object state result = true;
                 */
            }
        }

        return result;
    }

    public boolean onFlushDirty(
        Object entity, Serializable id, Object[] currentState,
        Object[] previousState, String[] propertyNames, Type[] types)
        throws CallbackException {
        boolean result = false;

        for (int i = 0; i < interceptors.length; i++) {
            if (
                interceptors[i].onFlushDirty(
                        entity, id, currentState, previousState, propertyNames,
                        types)) {
                /*
                 * Returns true if one interceptor in the chain has modified the
                 * object current state result = true;
                 */
            }
        }

        return result;
    }

    public boolean onSave(
        Object entity, Serializable id, Object[] state, String[] propertyNames,
        Type[] types) throws CallbackException {
        boolean result = false;

        for (int i = 0; i < interceptors.length; i++) {
            if (
                interceptors[i].onSave(
                        entity, id, state, propertyNames, types)) {
                /*
                 * Returns true if one interceptor in the chain has modified the
                 * object state result = true;
                 */
            }
        }

        return result;
    }

    public void onDelete(
        Object entity, Serializable id, Object[] state, String[] propertyNames,
        Type[] types) throws CallbackException {
        for (int i = 0; i < interceptors.length; i++) {
            interceptors[i].onDelete(entity, id, state, propertyNames, types);
        }
    }

    private List createList(Iterator iterator) {
        List list = new ArrayList();

        while (iterator.hasNext()) {
            list.add(iterator.next());
        }

        return list;
    }

    public void preFlush(Iterator entities) throws CallbackException {
        List entityList = createList(entities);

        for (int i = 0; i < interceptors.length; i++) {
            interceptors[i].postFlush(entityList.iterator());
        }
    }

    public void postFlush(Iterator entities) throws CallbackException {
        List entityList = createList(entities);

        for (int i = 0; i < interceptors.length; i++) {
            interceptors[i].postFlush(entityList.iterator());
        }
    }

    public Boolean isTransient(Object entity) {
        Boolean result = null;

        for (int i = 0; i < interceptors.length; i++) {
            result = interceptors[i].isTransient(entity);

            if (result != null) {
                // If any interceptor has returned either true or false, stop
                // the chain
                break;
            }
        }

        return result;
    }

    public int[] findDirty(
        Object entity, Serializable id, Object[] currentState,
        Object[] previousState, String[] propertyNames, Type[] types) {
        int[] result = null;

        for (int i = 0; i < interceptors.length; i++) {
            result = interceptors[i].findDirty(
                    entity, id, currentState, previousState, propertyNames,
                    types);

            if (result != null) {
                /*
                 * If any interceptor has returned something not null, stop the
                 * chain
                 */
                break;
            }
        }

        return result;
    }

    public Object instantiate(String clazz, EntityMode mode, Serializable id)
        throws CallbackException {
        Object result = null;

        for (int i = 0; i < interceptors.length; i++) {
            result = interceptors[i].instantiate(clazz, mode, id);

            if (result != null) {
                /*
                 * If any interceptor has returned something not null, stop the
                 * chain
                 */
                break;
            }
        }

        return result;
    }

    public String getEntityName(Object arg0) throws CallbackException {
        String result = null;

        for (int i = 0; i < interceptors.length; i++) {
            result = interceptors[i].getEntityName(arg0);

            if (result != null) {
                break;
            }
        }

        return result;
    }

    public Object getEntity(String name, Serializable id)
        throws CallbackException {
        Object result = null;

        for (int i = 0; i < interceptors.length; i++) {
            result = interceptors[i].getEntity(name, id);

            if (result != null) {
                /*
                 * If any interceptor has returned something not null, stop the
                 * chain
                 */
                break;
            }
        }

        return result;
    }

    public void afterTransactionBegin(Transaction transcation) {
        for (int i = 0; i < interceptors.length; i++) {
            interceptors[i].afterTransactionBegin(transcation);
        }
    }

    public void beforeTransactionCompletion(Transaction transcation) {
        for (int i = 0; i < interceptors.length; i++) {
            interceptors[i].beforeTransactionCompletion(transcation);
        }
    }

    public void afterTransactionCompletion(Transaction transcation) {
        for (int i = 0; i < interceptors.length; i++) {
            interceptors[i].afterTransactionCompletion(transcation);
        }
    }

    public Interceptor[] getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(Interceptor[] interceptors) {
        this.interceptors = interceptors;
    }

    public void onCollectionRecreate(Object arg0, Serializable arg1)
        throws CallbackException {
        // TODO Auto-generated method stub
    }

    public void onCollectionRemove(Object arg0, Serializable arg1)
        throws CallbackException {
        // TODO Auto-generated method stub
    }

    public void onCollectionUpdate(Object arg0, Serializable arg1)
        throws CallbackException {
        // TODO Auto-generated method stub
    }

    public String onPrepareStatement(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }
}
