package com.renhenet.modules.member;

import java.util.List;

import com.renhenet.modules.CommonService;
import com.renhenet.po.DictionarySort;

public class DictionarySortService extends CommonService {
	@SuppressWarnings("unchecked")
	public List<DictionarySort> getDictionarySortByParentId(int parentId) {
		String hql = "from DictionarySort where parentId =?";

		return (List<DictionarySort>) dao.find(hql, new Object[] { parentId });
	}

}
