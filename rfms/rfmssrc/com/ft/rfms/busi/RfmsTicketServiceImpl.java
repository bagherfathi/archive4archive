package com.ft.rfms.busi;

import java.util.List;

import com.ft.common.busi.BaseServiceImpl;
import com.ft.rfms.entity.RfmsTicket;
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

}
