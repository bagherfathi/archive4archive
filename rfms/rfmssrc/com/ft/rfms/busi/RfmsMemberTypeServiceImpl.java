/**
 * 
 */
package com.ft.rfms.busi;

import com.ft.common.busi.BaseServiceImpl;
import com.ft.rfms.entity.dao.RfmsMemberTypeDAO;
import com.ft.rfms.model.RfmsMemberTypeService;

/**
 * @author soler
 * 
 */
public class RfmsMemberTypeServiceImpl extends BaseServiceImpl implements
		RfmsMemberTypeService {

	private RfmsMemberTypeDAO rfmsMemberTypeDAO;

	public RfmsMemberTypeDAO getRfmsMemberTypeDAO() {
		return rfmsMemberTypeDAO;
	}

	public void setRfmsMemberTypeDAO(RfmsMemberTypeDAO rfmsMemberTypeDAO) {
		this.rfmsMemberTypeDAO = rfmsMemberTypeDAO;
	}

}
