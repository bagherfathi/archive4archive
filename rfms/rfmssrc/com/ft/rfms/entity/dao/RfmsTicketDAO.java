package com.ft.rfms.entity.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsTicket;
import com.ft.rfms.entity.RfmsTicketDetail;

/**
 * RfmsTicket 实体数据访问类
 * 
 * @spring.bean id="RfmsTicketDAO"
 * 
 * @spring.property ref="sessionFactory" name="sessionFactory"
 * 
 */
public class RfmsTicketDAO extends BaseDao {

	public RfmsTicketDAO() {
	}

	public RfmsTicketDAO(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * 获取查询实体类
	 */
	public Class getReferenceClass() {
		return RfmsTicket.class;
	}

	/**
	 * 根据ID查找对象
	 */
	public RfmsTicket getById(Serializable key) {
		return (RfmsTicket) this.getHibernateTemplate().get(
				getReferenceClass(), key);
	}

	/**
	 * 根据ID查找对象
	 */
	public RfmsTicket loadById(Serializable key) {
		return (RfmsTicket) this.getHibernateTemplate().load(
				getReferenceClass(), key);
	}

	/**
	 * 查找所有对象
	 */
	public java.util.List findAll() {
		return this.getHibernateTemplate().loadAll(getReferenceClass());
	}

	/**
	 * 得到指定状态的飞券卡下发信息
	 * 
	 * @param rfmsTicketId
	 * @param Status
	 * @return
	 */
	public List<RfmsTicketDetail> getRfmsTicketDetailByStatus(
			Long rfmsTicketId, Long status) {
		StringBuffer hql = new StringBuffer("from RfmsTicketDetail rt");
		hql.append(" where rt.").append(RfmsTicket.PROP_TICKET_ID).append("=?")
				.append(" and rt.").append(RfmsTicket.PROP_STATUS).append("=?");

		return this
				.query(hql.toString(), new Object[] { rfmsTicketId, status });
	}
    
    @SuppressWarnings("unchecked")
	public List<RfmsTicket> findTicketsByMerchant(String merchantCode){
    	return query("from RfmsTicket t where t.merchantId=?",new Object[]{merchantCode});
    }


	/**
	 * 得到已经下发的飞券卡下发信息
	 * 
	 * @param rfmsTicketId
	 * @param Status
	 * @return
	 */
	public List<RfmsTicketDetail> getRfmsTicketDetailByBigThanStatus(
			Long rfmsTicketId, Long status) {
		StringBuffer hql = new StringBuffer("from RfmsTicketDetail rt");
		hql.append(" where rt.").append(RfmsTicket.PROP_TICKET_ID).append("=?")
				.append(" and rt.").append(RfmsTicket.PROP_STATUS).append("> ?");

		return this
				.query(hql.toString(), new Object[] { rfmsTicketId, status });
	}

}