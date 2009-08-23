package com.renhenet.modules.member;

import java.util.List;

import com.renhenet.modules.CommonService;
import com.renhenet.po.InfoSort;

public class InfoSortService extends CommonService {
	@SuppressWarnings("unchecked")
	public List<InfoSort> getInfoSortByParentId(int parentId) {
		String hql = "from InfoSort where parentId =? order by seq asc";

		return (List<InfoSort>) dao.find(hql, new Object[] { parentId });
	}

}
