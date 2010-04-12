package com.ft.rfms.entity.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsMerchant;
import com.ft.rfms.entity.RfmsTicket;
import com.ft.rfms.entity.RfmsTicketDetail;

/**
 * RfmsTicket ʵ�����ݷ�����
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
	 * ��ȡ��ѯʵ����
	 */
	public Class getReferenceClass() {
		return RfmsTicket.class;
	}

	/**
	 * ����ID���Ҷ���
	 */
	public RfmsTicket getById(Serializable key) {
		return (RfmsTicket) this.getHibernateTemplate().get(
				getReferenceClass(), key);
	}

	/**
	 * ����ID���Ҷ���
	 */
	public RfmsTicket loadById(Serializable key) {
		return (RfmsTicket) this.getHibernateTemplate().load(
				getReferenceClass(), key);
	}

	/**
	 * �������ж���
	 */
	public java.util.List findAll() {
		return this.getHibernateTemplate().loadAll(getReferenceClass());
	}

	/**
	 * �õ�ָ��״̬�ķ�ȯ���·���Ϣ
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
	public List<RfmsTicket> findTicketsByMerchant(String merchantCode) {
		return query("from RfmsTicket t where t.merchantId=?",
				new Object[] { merchantCode });
	}

	/**
	 * �õ��Ѿ��·��ķ�ȯ���·���Ϣ
	 * 
	 * @param rfmsTicketId
	 * @param Status
	 * @return
	 */
	public List<RfmsTicketDetail> getRfmsTicketDetailByBigThanStatus(
			Long rfmsTicketId, Long status) {
		StringBuffer hql = new StringBuffer("from RfmsTicketDetail rt");
		hql.append(" where rt.").append(RfmsTicket.PROP_TICKET_ID).append("=?")
				.append(" and rt.").append(RfmsTicket.PROP_STATUS)
				.append("> ?");

		return this
				.query(hql.toString(), new Object[] { rfmsTicketId, status });
	}

	public List<RfmsTicket> searchTicket(String industry, String merchantName,
			String ticketNo, Long ticketType) throws Exception {
		StringBuffer hql = new StringBuffer(
				"from RfmsTicket rt,RfmsMerchant as rm");
		hql.append(" where rm.").append(RfmsMerchant.PROP_INDUSTRY)
				.append("=?").append(" and rm.").append(
						RfmsMerchant.PROP_MERCHANT_NAME).append("= ?").append(
						" and rt.").append(RfmsTicket.PROP_TICKET_SERIAL)
				.append(" and rt.").append(RfmsTicket.PROP_TYPE).append(
						" and rt.").append(RfmsTicket.PROP_OPERATOR_ID).append(
						"=").append(RfmsMerchant.PROP_MERCHANT_CODE);

		return null;
	}

	public RfmsTicket searchTicket(String merchantCode, String ticketCode)
			throws Exception {
		StringBuffer hql = new StringBuffer("from RfmsTicket rt");
		hql.append(" where rt.").append(RfmsTicket.PROP_OPERATOR_ID).append(
				"=?").append(" and rt.").append(RfmsTicket.PROP_TICKET_SERIAL)
				.append("=?");

		return (RfmsTicket) this.query(hql.toString(), new Object[] {
				merchantCode, ticketCode });
	}

}