package com.ft.hibernate.callback;

import com.ft.hibernate.support.EntityQuerySupport;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;


/**
 * 计算代group的查询录数的回调类
 *
 */
public class CountGroupCallback implements HibernateCallback {
    private Class entityCountClass;
    private String attribute;
    private Class entityClass;

    public CountGroupCallback(
        Class entityClass, Class entityCountClass, String attribute) {
        super();
        this.attribute = attribute;
        this.entityClass = entityClass;
        this.entityCountClass = entityCountClass;
    }

    /*
     *  (non-Javadoc)
     * @see org.springframework.orm.hibernate3.HibernateCallback#doInHibernate(org.hibernate.Session)
     */
    public Object doInHibernate(Session session)
        throws HibernateException, SQLException {
        EntityQuerySupport entityQuerySupport =
            EntityQuerySupport.getInstance();

        return entityQuerySupport.countGroup(
            entityCountClass, attribute, entityClass, session);
    }
}
