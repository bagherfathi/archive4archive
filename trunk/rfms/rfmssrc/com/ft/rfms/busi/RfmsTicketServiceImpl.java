package com.ft.rfms.busi;

import java.util.Date;
import java.util.List;

import com.ft.common.busi.BaseServiceImpl;
import com.ft.rfms.entity.RfmsTicket;
import com.ft.rfms.entity.RfmsTicketBind;
import com.ft.rfms.entity.RfmsTicketDetail;
import com.ft.rfms.entity.dao.RfmsTicketDAO;
import com.ft.rfms.model.RfmsTicketService;

public class RfmsTicketServiceImpl extends BaseServiceImpl implements
		RfmsTicketService {
	private RfmsTicketDAO rfmsTicketDAO;

	public List<RfmsTicketDetail> getRfmsTicketDetailByStatus(
			Long rfmsTicketId, Long status) {
		return rfmsTicketDAO.getRfmsTicketDetailByStatus(rfmsTicketId, status);
	}

	public List<RfmsTicketDetail> getRfmsTicketDetailByBigThanStatus(
			Long rfmsTicketId, Long status) {
		return rfmsTicketDAO.getRfmsTicketDetailByBigThanStatus(rfmsTicketId,
				status);
	}

	public RfmsTicket getRfmsTicketById(Long rfmsTicketId) {
		return (RfmsTicket) rfmsTicketDAO.getObjectById(RfmsTicket.class,
				rfmsTicketId);
	}

	public RfmsTicketDAO getRfmsTicketDAO() {
		return rfmsTicketDAO;
	}

	public void setRfmsTicketDAO(RfmsTicketDAO rfmsTicketDAO) {
		this.rfmsTicketDAO = rfmsTicketDAO;
	}
	
	public List<PosBindDTO> findPosByTicketId(Long ticketId){
		return this.rfmsTicketDAO.findPosByTicketId(ticketId);
	}
	
	public List<PosBindDTO> searchPos(String merchantName,String branchName,String posCode){
		return this.rfmsTicketDAO.searchPos(merchantName, branchName, posCode);
	}
	
	public void saveBindPos(Long ticketId,String[] posCodes){
		if (posCodes != null && posCodes.length > 0) {
			for (int i = 0; i < posCodes.length; i++) {
				RfmsTicketBind bind = new RfmsTicketBind();
				if(this.rfmsTicketDAO.posExists(posCodes[i], ticketId)){
					continue;
				}
				bind.setCreateDate(new Date());
				bind.setPosCode(posCodes[i]);
				bind.setTicketId(ticketId);
				bind.setUpdateDate(new Date());
				this.rfmsTicketDAO.save(bind);
			}
		}
	}

}
