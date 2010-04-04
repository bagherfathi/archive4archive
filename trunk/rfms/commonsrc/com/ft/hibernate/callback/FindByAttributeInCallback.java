package com.ft.hibernate.callback;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.hibernate.criterion.Expression;

import java.io.Serializable;

import java.sql.SQLException;

import java.util.Collections;


/**
 * 根据属性进行IN查询的回调类
 *
 * @author Coffee
 */
public class FindByAttributeInCallback implements QueryHibernateCallback {
    protected Class entityClass;
    protected String attribute;
    protected Serializable[] values;

    public FindByAttributeInCallback(
        Class entityClass, String attribute, Serializable[] values) {
        this.entityClass = entityClass;
        this.attribute = attribute;
        this.values = values;
    }

    /*
     *  (non-Javadoc)
     * @see org.springframework.orm.hibernate3.HibernateCallback#doInHibernate(org.hibernate.Session)
     */
    public Object doInHibernate(Session session)
        throws HibernateException, SQLException {
        if (this.values.length == 0) {
            return Collections.EMPTY_LIST;
        }

        Criteria criteria = session.createCriteria(entityClass);
        criteria.add(Expression.in(attribute, values));

        return criteria.list();
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    public Serializable[] getValues() {
        return values;
    }

    public void setValues(Serializable[] values) {
        this.values = values;
    }
}
