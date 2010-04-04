package com.ft.hibernate.callback;

import com.ft.hibernate.support.EntityQuerySupport;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;


/**
 * 根据实体一组属性检查的实体是否存在的回调类
 *
 * @author Coffee
 */
public class ExistCallback implements HibernateCallback {
    protected Class entityClass;
    protected String[] attribute;
    protected Object[] value;
    protected String type;

    public ExistCallback(
        String type, Class class1, String[] attribute, Object[] value) {
        super();
        this.attribute = attribute;
        entityClass = class1;
        this.value = value;
        this.type = type;
    }

    /*
     *  (non-Javadoc)
     * @see org.springframework.orm.hibernate3.HibernateCallback#doInHibernate(org.hibernate.Session)
     */
    public Object doInHibernate(Session session)
        throws HibernateException, SQLException {
        boolean result =
            EntityQuerySupport.getInstance()
                              .exist(
                type, entityClass, attribute, value, session);

        return new Boolean(result);
    }
}
