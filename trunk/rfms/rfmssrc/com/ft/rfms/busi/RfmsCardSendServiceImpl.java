/**
 * 
 */
package com.ft.rfms.busi;

import com.ft.common.busi.BaseServiceImpl;
import com.ft.rfms.entity.dao.RfmsCardSendDAO;
import com.ft.rfms.model.RfmsCardSendService;

/**
 * @author soler
 * 
 */
public class RfmsCardSendServiceImpl extends BaseServiceImpl implements
		RfmsCardSendService {

	private RfmsCardSendDAO rfmsCardSendDAO;

	public RfmsCardSendDAO getRfmsCardSendDAO() {
		return rfmsCardSendDAO;
	}

	public void setRfmsCardSendDAO(RfmsCardSendDAO rfmsCardSendDAO) {
		this.rfmsCardSendDAO = rfmsCardSendDAO;
	}

}
