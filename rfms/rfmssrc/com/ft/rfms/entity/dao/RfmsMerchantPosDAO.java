package com.ft.rfms.entity.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsMerchantPos;

/**
 * RfmsMerchantPos 实体数据访问类
 * 
 * @spring.bean id="RfmsMerchantPosDAO"
 * 
 * @spring.property ref="sessionFactory" name="sessionFactory"
 * 
 */
public class RfmsMerchantPosDAO extends BaseDao {

	public RfmsMerchantPosDAO() {
	}

	public RfmsMerchantPosDAO(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * 获取查询实体类
	 */
	public Class getReferenceClass() {
		return RfmsMerchantPos.class;
	}

	/**
	 * 根据ID查找对象
	 */
	public RfmsMerchantPos getById(Serializable key) {
		return (RfmsMerchantPos) this.getHibernateTemplate().get(
				getReferenceClass(), key);
	}

	/**
	 * 根据ID查找对象
	 */
	public RfmsMerchantPos loadById(Serializable key) {
		return (RfmsMerchantPos) this.getHibernateTemplate().load(
				getReferenceClass(), key);
	}

	/**
	 * 查找所有对象
	 */
	public java.util.List findAll() {
		return this.getHibernateTemplate().loadAll(getReferenceClass());
	}

	public List findPosByBranchId(Long branchId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ").append(this.getReferenceClass().getName()).append(
				" pos where pos.").append(
				RfmsMerchantPos.PROP_MERCHANT_BRANCH_ID).append("=?");
		return this.query(hql.toString(),new Long[]{branchId});
	}

}