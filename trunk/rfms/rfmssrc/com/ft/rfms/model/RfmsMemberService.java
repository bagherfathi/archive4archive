/**
 * 
 */
package com.ft.rfms.model;

import java.util.List;

import com.ft.common.busi.BaseService;
import com.ft.rfms.entity.RfmsMember;

public interface RfmsMemberService extends BaseService {
	public int importMember(String fileName, Long merchantId);

	public List<RfmsMember> getRfmsMemberByStatus(String status);
}
