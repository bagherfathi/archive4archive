/**
 * 
 */
package com.ft.busi.sm.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ft.busi.sm.model.EntityManager;
import com.ft.commons.page.PageBean;
import com.ft.hibernate.support.BaseDao;

/**
 * @author soler
 *
 */
public class CommonEntityManagerImpl implements EntityManager {

	private BaseDao baseDao;
	
	
	/**
	 * @param baseDao the baseDao to set
	 */
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	/* (non-Javadoc)
	 * @see com.ft.busi.sm.model.EntityManager#getEntity(java.lang.Class, java.io.Serializable)
	 */
	public Object getEntity(Class typeClass, Serializable id) throws Exception {
		return baseDao.getObjectById(typeClass, id);
	}

	/* (non-Javadoc)
	 * @see com.ft.busi.sm.model.EntityManager#getResultSet(java.lang.Class, java.lang.String, java.lang.Object[], com.ft.commons.page.PageBean)
	 */
	public List getResultSet(Class typeClass, String hql, Object[] params,
			PageBean page) throws Exception {
		 List result = new ArrayList();
         result = baseDao.query(hql, params, page);
         return result;

	}

	/* (non-Javadoc)
	 * @see com.ft.busi.sm.model.EntityManager#loadByIds(java.lang.Class, java.io.Serializable[])
	 */
	public List loadByIds(Class typeClass, Serializable[] ids) throws Exception {
		return baseDao.loadByIds(typeClass, ids);
	}

}
