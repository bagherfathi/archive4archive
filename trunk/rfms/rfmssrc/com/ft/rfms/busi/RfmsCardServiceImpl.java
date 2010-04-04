/**
 * 
 */
package com.ft.rfms.busi;

import com.ft.common.busi.BaseServiceImpl;
import com.ft.rfms.entity.dao.RfmsCardDAO;
import com.ft.rfms.model.RfmsCardService;

/**
 * @author soler
 * 
 */
public class RfmsCardServiceImpl extends BaseServiceImpl implements
		RfmsCardService {

	private RfmsCardDAO rfmsCardDAO;

	public RfmsCardDAO getRfmsCardDAO() {
		return rfmsCardDAO;
	}

	public void setRfmsCardDAO(RfmsCardDAO rfmsCardDAO) {
		this.rfmsCardDAO = rfmsCardDAO;
	}

}
