/**
 * 
 */
package com.ft.rfms.busi;

import com.ft.common.busi.BaseServiceImpl;
import com.ft.rfms.entity.dao.RfmsSmsDAO;
import com.ft.rfms.model.RfmsSmsService;

/**
 * @author soler
 * 
 */
public class RfmsSmsServiceImpl extends BaseServiceImpl implements
		RfmsSmsService {

	private RfmsSmsDAO rfmsSmsDAO;

	public RfmsSmsDAO getRfmsSmsDAO() {
		return rfmsSmsDAO;
	}

	public void setRfmsSmsDAO(RfmsSmsDAO rfmsSmsDAO) {
		this.rfmsSmsDAO = rfmsSmsDAO;
	}

}
