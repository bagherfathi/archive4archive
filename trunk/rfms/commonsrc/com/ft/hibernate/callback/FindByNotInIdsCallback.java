package com.ft.hibernate.callback;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.hibernate.impl.SessionFactoryImpl;

import org.hibernate.persister.entity.EntityPersister;

import java.io.Serializable;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


/**
 * 根据一组ID查询不存在这组ID的对象方法
 *
 */
public class FindByNotInIdsCallback extends FindByIdsCallback {
    public FindByNotInIdsCallback(Class class1, Serializable[] keys) {
        super(class1, keys);
    }

    /*
     *  (non-Javadoc)
     * @see org.springframework.orm.hibernate3.HibernateCallback#doInHibernate(org.hibernate.Session)
     */
    public Object doInHibernate(Session session)
        throws HibernateException, SQLException {
        if (session.getSessionFactory() instanceof SessionFactoryImpl) {
            SessionFactoryImpl factoryImpl =
                (SessionFactoryImpl) session.getSessionFactory();
            EntityPersister persister =
                factoryImpl.getEntityPersister(entityClass.getName());
            String keyName = persister.getIdentifierPropertyName();
            String queryStr = "from " + entityClass.getName() + " obKeys ";

            if ((keys == null) || (keys.length == 0)) {
            } else {
                queryStr = queryStr + "  where obKeys." + keyName
                    + " not in " + joinKeys(keys);
            }

            queryStr = queryStr + " order by obKeys." + keyName;

            List result = session.createQuery(queryStr).list();

            return result;
        }

        return new ArrayList();
    }
}
