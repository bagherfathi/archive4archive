package com.ft.hibernate.callback;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.hibernate.impl.SessionFactoryImpl;

import org.hibernate.persister.entity.EntityPersister;

import org.springframework.orm.hibernate3.HibernateCallback;

import java.io.Serializable;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


/**
 * 根据一组ID查询对象的方法
 *
 * @author Coffee
 */
public class FindByIdsCallback implements HibernateCallback {
    protected Class entityClass;
    protected Serializable[] keys;

    public FindByIdsCallback(Class class1, Serializable[] keys) {
        super();
        entityClass = class1;
        this.keys = keys;
    }

    /*
    *  (non-Javadoc)
    * @see org.springframework.orm.hibernate3.HibernateCallback#doInHibernate(org.hibernate.Session)
     */
    public Object doInHibernate(Session session)
        throws HibernateException, SQLException {
        if ((keys == null) || (keys.length == 0)) {
            return new ArrayList();
        }

        if (session.getSessionFactory() instanceof SessionFactoryImpl) {
            SessionFactoryImpl factoryImpl =
                (SessionFactoryImpl) session.getSessionFactory();
            EntityPersister persister =
                factoryImpl.getEntityPersister(entityClass.getName());
            String keyName = persister.getIdentifierPropertyName();
            String queryStr =
                "from " + entityClass.getName() + " obKeys where obKeys."
                + keyName + " in " + joinKeys(keys)
                + " order by obKeys." + keyName;
            List result = session.createQuery(queryStr).list();

            return result;
        }

        return new ArrayList();
    }

   static public String joinKeys(Object[] keys) {
        StringBuffer inStr = new StringBuffer("(");

        for (int i = 0; i < (keys.length - 1); i++) {
            inStr.append(keys[i]).append(",");
        }

        inStr.append(keys[keys.length - 1]).append(")");

        return inStr.toString();
    }
}
