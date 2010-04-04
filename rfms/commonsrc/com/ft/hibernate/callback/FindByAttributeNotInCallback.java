package com.ft.hibernate.callback;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.hibernate.criterion.Expression;

import java.io.Serializable;

import java.sql.SQLException;


/**
 * 根据属性进行NOT IN查询的回调方法
 *
 */
public class FindByAttributeNotInCallback extends FindByAttributeInCallback {
    public FindByAttributeNotInCallback(
        Class entityClass, String attribute, Serializable[] values) {
        super(entityClass, attribute, values);
    }

    /*
     *  (non-Javadoc)
     * @see org.springframework.orm.hibernate3.HibernateCallback#doInHibernate(org.hibernate.Session)
     */
    public Object doInHibernate(Session session)
        throws HibernateException, SQLException {
        Criteria criteria = session.createCriteria(entityClass);
        criteria.add(Expression.not(Expression.in(attribute, values)));

        return criteria.list();
    }
}
