package com.ft.rfms.entity.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.busi.PosBindDTO;
import com.ft.rfms.entity.RfmsMerchant;
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
	public List<RfmsTicket> findTicketsByMerchant(Long merchantCode) {
		return query("from RfmsTicket t where operatorId=?",
				new Object[] { merchantCode });
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
	
	public boolean posExists(String posCode,Long ticketId){
		StringBuffer hql = new StringBuffer("from RfmsTicketBind rt");
		hql.append(" where rt.posCode=? and ticketId=?");
		List list=this.query(hql.toString(),new Object[]{posCode,ticketId});
		if(list!=null && !list.isEmpty()){
			return true;
		}
		return false;
	}

	public List<PosBindDTO> findPosByTicketId(Long ticketId){
		StringBuffer hql = new StringBuffer("select new com.ft.rfms.busi.PosBindDTO(rt,pos,branch,mer) from RfmsTicketBind rt,RfmsMerchantPos pos,RfmsMerchantBranch branch,RfmsMerchant mer ");
		hql.append(" where rt.posCode=pos.sysPosCode ");
		hql.append(" and pos.merchantBranchId= branch.merchantBranchId");
		hql.append(" and branch.merchantId=mer.merchantId");
		hql.append("  and rt.ticketId=?");
		return this.query(hql.toString(),new Object[]{ticketId});
		
	}
	
	public List<PosBindDTO> searchPos(String merchantName,String branchName,String posCode){
		if(merchantName==null &&branchName==null && posCode==null ){
			return new ArrayList<PosBindDTO>();
		}
		StringBuffer hql = new StringBuffer("select new com.ft.rfms.busi.PosBindDTO(pos,branch,mer) from RfmsMerchantPos pos,RfmsMerchantBranch branch,RfmsMerchant mer ");
		hql.append(" where pos.merchantBranchId= branch.merchantBranchId");
		hql.append(" and branch.merchantId=mer.merchantId");
		if(merchantName!=null && merchantName.length()>0){
			hql.append(" and mer.merchantName like '%").append(merchantName).append("%'");
		}
		if(branchName!=null && branchName.length()>0){
			hql.append(" and branch.branchName like '%").append(merchantName).append("%'");
		}
		if(posCode!=null && posCode.length()>0){
			hql.append(" and pos.sysPosCode like '%").append(merchantName).append("%'");
		}
		return this.query(hql.toString());
		
	}
	
}