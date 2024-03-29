package com.ft.hibernate.callback;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;

import java.util.List;


/**
 * 查询的回调接口
 *
 */
public class QueryCallback implements HibernateCallback {
    String queryString;
    Object[] params;
    int begin;
    int size;

    public QueryCallback(String string, Object[] params, int begin, int size) {
        super();
        this.begin = begin;
        this.params = params;
        queryString = string;
        this.size = size;
    }

    /**
     * 根据查询语句查询对象
     *
     * @param queryString 查询语句
     * @param params 参数
     * @param begin 起列
     * @param size 大小
     * @param session Hibernate Session;
     *
     * @return 返回对象列表
     */
    public List query(
        String queryString, Object[] params, int begin, int count,
        Session session) {
        Query query = session.createQuery(queryString);

        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }

        query.setFirstResult(begin);
        query.setMaxResults(count);

        return query.list();
    }

    /*
     *  (non-Javadoc)
     * @see org.springframework.orm.hibernate3.HibernateCallback#doInHibernate(org.hibernate.Session)
     */
    public Object doInHibernate(Session session)
        throws HibernateException, SQLException {
        return this.query(
            queryString, this.params, this.begin, this.size, session);
    }
}
