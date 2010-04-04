package com.ft.rfms.entity.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsFlowCtrl;

/**
 * RfmsFlowCtrl 实体数据访问类
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
	 * 获取查询实体类
	 */
	public Class getReferenceClass() {
		return RfmsFlowCtrl.class;
	}

	/**
	 * 根据ID查找对象
	 */
	public RfmsFlowCtrl getById(Serializable key) {
		return (RfmsFlowCtrl) this.getHibernateTemplate().get(
				getReferenceClass(), key);
	}

	/**
	 * 根据ID查找对象
	 */
	public RfmsFlowCtrl loadById(Serializable key) {
		return (RfmsFlowCtrl) this.getHibernateTemplate().load(
				getReferenceClass(), key);
	}

	/**
	 * 查找所有对象
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