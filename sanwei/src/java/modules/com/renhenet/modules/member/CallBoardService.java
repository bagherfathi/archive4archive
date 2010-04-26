package com.renhenet.modules.member;

import java.util.List;

import com.renhenet.modules.CommonService;
import com.renhenet.po.CallBoard;

public class CallBoardService extends CommonService {

	@SuppressWarnings("unchecked")
	public List<CallBoard> getCallBoardList(int companyId, int rows) {
		String hql = "from CallBoard as t where t.companyId = ? order by t.timeModified desc";
		return dao.find(hql, new Object[] { companyId });
	}
}
