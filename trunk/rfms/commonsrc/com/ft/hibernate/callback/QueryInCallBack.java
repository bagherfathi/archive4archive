package com.ft.hibernate.callback;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.impl.SessionFactoryImpl;
import org.springframework.orm.hibernate3.HibernateCallback;

public class QueryInCallBack implements HibernateCallback {
	private String hql;

	protected Serializable[] keys;
	
	private String conditions;

	public QueryInCallBack(String hql, Serializable[] keys) {
		super();
		this.hql = hql;
		this.keys = keys;
	}
	
	public QueryInCallBack(String hql, Serializable[] keys, String conditions) {
		super();
		this.hql = hql;
		this.keys = keys;
		this.conditions = conditions;
	}

	public Object doInHibernate(Session session) throws HibernateException,
			SQLException {
		if ((keys == null) || (keys.length == 0)) {
			return new ArrayList();
		}

		if (session.getSessionFactory() instanceof SessionFactoryImpl) {
			String queryStr = hql + FindByNotInIdsCallback.joinKeys(keys);
			if(null != conditions)
				queryStr += " " + conditions;
			List result = session.createQuery(queryStr).list();

			return result;
		}

		return new ArrayList();
	}

}
