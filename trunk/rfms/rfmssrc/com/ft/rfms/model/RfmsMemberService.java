/**
 * 
 */
package com.ft.rfms.model;

import java.util.List;

import com.ft.common.busi.BaseService;
import com.ft.rfms.entity.RfmsMember;

public interface RfmsMemberService extends BaseService {
	/**
	 * �ϴ���Ա�ļ�
	 * 
	 * @param fileName
	 * @param operatorId
	 * @return
	 */
	public List<RfmsMember> importMember(String fileName, Long operatorId);

	public List<RfmsMember> getRfmsMemberByStatus(String status);
}
