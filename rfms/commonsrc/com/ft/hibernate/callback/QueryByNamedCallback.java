package com.ft.hibernate.callback;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;

import java.util.List;


/**
 * 根据查询名称进行查询
 *
 */
public class QueryByNamedCallback implements HibernateCallback {
    String queryString;
    Object[] params;
    int begin;
    int size;

    public QueryByNamedCallback(
        String string, Object[] params, int begin, int size) {
        super();
        this.begin = begin;
        this.params = params;
        queryString = string;
        this.size = size;
    }

    /*
     *  (non-Javadoc)
     * @see org.springframework.orm.hibernate3.HibernateCallback#doInHibernate(org.hibernate.Session)
     */
    public Object doInHibernate(Session arg0)
        throws HibernateException, SQLException {
        return this.queryByNamed(queryString, params, begin, size, arg0);
    }

    /**
     * 根据定义查询语句进行查询
     *
     * @param named 查询定义
     * @param params 参数
     * @param begin 开始
     * @param size 大小
     * @param session Hibernate Session
     *
     * @return
     */
    protected List queryByNamed(
        String named, Object[] params, int begin, int size, Session session) {
        Query query = session.getNamedQuery(named);

        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }

        query.setFirstResult(begin);
        query.setMaxResults(size);

        return query.list();
    }
}
