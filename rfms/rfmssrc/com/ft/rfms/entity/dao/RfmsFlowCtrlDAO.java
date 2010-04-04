package com.ft.rfms.entity.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsFlowCtrl;

/**
 * RfmsFlowCtrl ʵ�����ݷ�����
 * 
 * @spring.bean id="RfmsFlowCtrlDAO"
 * 
 * @spring.property ref="sessionFactory" name="sessionFactory"
 * 
 */
public class RfmsFlowCtrlDAO extends BaseDao {

	public RfmsFlowCtrlDAO() {
	}

	public RfmsFlowCtrlDAO(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * ��ȡ��ѯʵ����
	 */
	public Class getReferenceClass() {
		return RfmsFlowCtrl.class;
	}

	/**
	 * ����ID���Ҷ���
	 */
	public RfmsFlowCtrl getById(Serializable key) {
		return (RfmsFlowCtrl) this.getHibernateTemplate().get(
				getReferenceClass(), key);
	}

	/**
	 * ����ID���Ҷ���
	 */
	public RfmsFlowCtrl loadById(Serializable key) {
		return (RfmsFlowCtrl) this.getHibernateTemplate().load(
				getReferenceClass(), key);
	}

	/**
	 * �������ж���
	 */
	public java.util.List findAll() {
		return this.getHibernateTemplate().loadAll(getReferenceClass());
	}

	public List findByAuditStatusBefore(Long auditStatus) {
		StringBuffer hql = new StringBuffer();
		hql.append("from RfmsFlowCtrl ct where ct.").append(
				RfmsFlowCtrl.PROP_STATUS_BEFORE).append("=?");
		return this.query(hql.toString(), new Long[] { auditStatus });
	}
	
	public List findNextCtrlByStatusBefore(Long curStatus) {

		StringBuffer hql = new StringBuffer();
		hql
				.append("select distinct ct1 from RfmsFlowCtrl ct,RfmsFlowCtrl ct1 where 1=1 ");
		hql.append(" and ct.").append(RfmsFlowCtrl.PROP_STATUS_AFTER).append(
				"=ct1.").append(RfmsFlowCtrl.PROP_STATUS_BEFORE);
		hql.append(" and ct.").append(RfmsFlowCtrl.PROP_STATUS_BEFORE).append(
				"=?");
		return this.query(hql.toString(), new Long[] { curStatus });
	}

	public List findNextCtrlRoleIdsByStatusBefore(Long curStatus) {
		StringBuffer hql = new StringBuffer();
		hql.append("select distinct ct1.").append(RfmsFlowCtrl.PROP_ROLE_ID);
		hql.append(" from RfmsFlowCtrl ct,RfmsFlowCtrl ct1 where 1=1 ");
		hql.append(" and ct.").append(RfmsFlowCtrl.PROP_STATUS_AFTER);
		hql.append("=ct1.").append(RfmsFlowCtrl.PROP_STATUS_BEFORE);
		hql.append(" and ct.").append(RfmsFlowCtrl.PROP_STATUS_BEFORE).append(
				"=?");
		return this.query(hql.toString(), new Long[] { curStatus });
	}

}