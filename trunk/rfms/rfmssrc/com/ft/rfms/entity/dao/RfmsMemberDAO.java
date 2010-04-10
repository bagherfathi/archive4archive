package com.ft.rfms.entity.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsMember;
import com.ft.rfms.entity.RfmsTicket;

/**
 * RfmsMember ʵ�����ݷ�����
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
	 * ��ȡ��ѯʵ����
	 */
	public Class getReferenceClass() {
		return RfmsMember.class;
	}

	/**
	 * ����ID���Ҷ���
	 */
	public RfmsMember getById(Serializable key) {
		return (RfmsMember) this.getHibernateTemplate().get(
				getReferenceClass(), key);
	}

	/**
	 * ����ID���Ҷ���
	 */
	public RfmsMember loadById(Serializable key) {
		return (RfmsMember) this.getHibernateTemplate().load(
				getReferenceClass(), key);
	}

	/**
	 * �������ж���
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