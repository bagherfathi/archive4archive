package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.ViApp;

/**
 * 
 * AppView 实体数据访问类
 * 
 * @version 1.0
 * 
 * @spring.bean id="appViewDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class ViAppDao extends BaseDao {

	/**
	 * 构造函数
	 */
	public ViAppDao() {
	}

	/**
	 * 获取查询实体类
	 */
	public Class getReferenceClass() {
		return ViApp.class;
	}

	/**
	 * 根据ID查找对象
	 */
	public ViApp getById(Serializable key) {
		return (ViApp) this.getHibernateTemplate().get(getReferenceClass(),
				key);
	}

	/**
	 * 根据ID查找对象
	 */
	public ViApp loadById(Serializable key) {
		return (ViApp) this.getHibernateTemplate().load(getReferenceClass(),
				key);
	}
	
	/**
	 * 根据受理编号查找受理信息
	 * 
	 * @param appCode
	 *            受理编号
	 * @return App 不存在返回为空
	 */
	public ViApp findByAppCode(String appCode) {
		List result = this.getHibernateTemplate().find(
				"from ViApp where appCode = ?", new Object[] { appCode });

		if (result == null || result.isEmpty()) {
			return null;
		} else {
			return (ViApp) result.get(0);
		}
	}


}