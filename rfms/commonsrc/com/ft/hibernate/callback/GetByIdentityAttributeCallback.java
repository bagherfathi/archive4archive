package com.ft.hibernate.callback;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;

import java.util.List;


/**
 * 根据某个唯一属性得到对象
 *
 */
public class GetByIdentityAttributeCallback implements HibernateCallback {
    private Class entityClass;
    private String attribute;
    private Object value;

    public GetByIdentityAttributeCallback(
        Class class1, String attribute, Object value) {
        super();
        this.attribute = attribute;
        entityClass = class1;
        this.value = value;
    }

    /*
     *  (non-Javadoc)
     * @see org.springframework.orm.hibernate3.HibernateCallback#doInHibernate(org.hibernate.Session)
     */
    public Object doInHibernate(Session session)
        throws HibernateException, SQLException {
        StringBuffer sql = new StringBuffer("from ");
        sql.append(entityClass.getName()).append(" where ");
        sql.append(attribute).append("=?");

        Query query = session.createQuery(sql.toString());
        query.setParameter(0, value);

        List list = query.list();

        if (!list.isEmpty()) {
            return list.iterator().next();
        } else {
            return null;
        }
    }
}
