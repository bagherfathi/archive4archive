package com.ft.hibernate.callback;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.sql.SQLException;


/**
 * 根据属性查询是否存在对象
 *
 */
public class NotExistCallback extends ExistCallback {
    public NotExistCallback(
        String type, Class class1, String[] attribute, Object[] value) {
        super(type, class1, attribute, value);
    }

    /*
     *  (non-Javadoc)
     * @see org.springframework.orm.hibernate3.HibernateCallback#doInHibernate(org.hibernate.Session)
     */
    public Object doInHibernate(Session session)
        throws HibernateException, SQLException {
        Object obj = super.doInHibernate(session);

        if (obj instanceof Boolean) {
            Boolean value = (Boolean) obj;

            return new Boolean(!value.booleanValue());
        } else {
            return new Boolean(false);
        }
    }
}
