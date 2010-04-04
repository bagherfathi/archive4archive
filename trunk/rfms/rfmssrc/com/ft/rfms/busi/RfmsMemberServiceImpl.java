/**
 * 
 */
package com.ft.rfms.busi;

import com.ft.common.busi.BaseServiceImpl;
import com.ft.rfms.entity.dao.RfmsMemberDAO;
import com.ft.rfms.model.RfmsMemberService;

/**
 * @author soler
 * 
 */
public class RfmsMemberServiceImpl extends BaseServiceImpl implements
		RfmsMemberService {

	private RfmsMemberDAO rfmsMemberDAO;

	public RfmsMemberDAO getRfmsMemberDAO() {
		return rfmsMemberDAO;
	}

	public void setRfmsMemberDAO(RfmsMemberDAO rfmsMemberDAO) {
		this.rfmsMemberDAO = rfmsMemberDAO;
	}

}
