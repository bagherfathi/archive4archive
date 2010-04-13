/**
 * 
 */
package com.ft.rfms.model;

import java.util.List;

import com.ft.common.busi.BaseService;
import com.ft.rfms.busi.PosBindDTO;
import com.ft.rfms.entity.RfmsTicket;
import com.ft.rfms.entity.RfmsTicketDetail;

public interface RfmsTicketService extends BaseService {
	/**
	 * �õ��ȴ��·��ķ�ȯ���·���Ϣ
	 * 
	 * @param rfmsTicketId
	 * @param Status
	 * @return
	 */
	public List<RfmsTicketDetail> getRfmsTicketDetailByStatus(
			Long rfmsTicketId, Long status);

	/**
	 * �õ��Ѿ��·��ķ�ȯ���·���Ϣ
	 * 
	 * @param rfmsTicketId
	 * @param Status
	 * @return
	 */
	public List<RfmsTicketDetail> getRfmsTicketDetailByBigThanStatus(
			Long rfmsTicketId, Long status);

	public RfmsTicket getRfmsTicketById(Long rfmsTicketId);
	
	public List<PosBindDTO> findPosByTicketId(Long ticketId);
	
	public List<PosBindDTO> searchPos(String merchantName,String branchName,String posCode);
	
	public void saveBindPos(Long ticketId,String[] posCodes);

}
