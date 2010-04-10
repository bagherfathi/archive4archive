package com.ft.rfms.entity.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsMember;
import com.ft.rfms.entity.RfmsTicket;

/**
 * RfmsMember 实体数据访问类
 * 
 * @spring.bean id="RfmsMemberDAO"
 * 
 * @spring.property ref="sessionFactory" name="sessionFactory"
 * 
 */
public class RfmsMemberDAO extends BaseDao {

	public RfmsMemberDAO() {
	}

	public RfmsMemberDAO(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * 获取查询实体类
	 */
	public Class getReferenceClass() {
		return RfmsMember.class;
	}

	/**
	 * 根据ID查找对象
	 */
	public RfmsMember getById(Serializable key) {
		return (RfmsMember) this.getHibernateTemplate().get(
				getReferenceClass(), key);
	}

	/**
	 * 根据ID查找对象
	 */
	public RfmsMember loadById(Serializable key) {
		return (RfmsMember) this.getHibernateTemplate().load(
				getReferenceClass(), key);
	}

	/**
	 * 查找所有对象
	 */
	public java.util.List findAll() {
		return this.getHibernateTemplate().loadAll(getReferenceClass());
	}

	public List<RfmsMember> getRfmsMemberByStatus(String status) {
		StringBuffer hql = new StringBuffer("from RfmsMember");
		hql.append(" where ").append(RfmsMember.PROP_STATUS)
				.append("=?");

		return this.query(hql.toString(), new Object[] { status });
	}

}