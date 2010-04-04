package com.ft.hibernate.callback;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.SQLException;

import java.util.Iterator;


/**
 * 计算记录数的回调类
 */
public class CountCallback implements QueryHibernateCallback {
    private String queryString;
    private Object[] params;

    public CountCallback(String string, Object[] params) {
        super();
        this.params = params;
        queryString = string;
    }

    /*
     *  (non-Javadoc)
     * @see org.springframework.orm.hibernate3.HibernateCallback#doInHibernate(org.hibernate.Session)
     */
    public Object doInHibernate(Session session)
        throws HibernateException, SQLException {
        String countSql = createCountQL(queryString);
        Query countQuery = session.createQuery(countSql);

        for (int i = 0; i < params.length; i++) {
            countQuery.setParameter(i, params[i]);
        }

        Iterator iter = countQuery.iterate();
        Number countValue = new Integer(0);

        if (iter.hasNext()) {
            countValue = (Number) iter.next();
        }

        return countValue;
    }

    /**
     * 创建查询语句
     *
     * @param queryStr
     *
     * @return
     */
    private String createCountQL(String queryStr) {
        int idxF = queryStr.indexOf("from");
        int idxD = queryStr.indexOf("distinct");

        if (idxD > 0) {
            String result =
                queryStr.substring(idxD + "distinct".length(), idxF);

            return "select distinct count(" + result + ") "
            + queryStr.substring(idxF);
        } else {
            return "select count(*) " + queryStr.substring(idxF);
        }
    }
}
