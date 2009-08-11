package com.renhenet.modules.member;

import java.util.List;

import com.renhenet.modules.CommonService;
import com.renhenet.po.InfoSort;

public class InfoSortService extends CommonService {
	public List<InfoSort> getInfoSortByParentId(int parentId) {
		String hql = "from InfoSort where parentId =?";

		return (List<InfoSort>) dao.find(hql, new Object[] { parentId });
	}

}
