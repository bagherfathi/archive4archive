package com.renhenet.modules.member;

import java.util.List;

import com.renhenet.modules.CommonService;
import com.renhenet.po.Dhsz;

public class DhszService extends CommonService {
	/**
	 * 根据分类号得到对应的档号信息
	 * 
	 * @param infoSortId
	 * @return
	 */
	public List<Dhsz> getDhszByinfoSortId(int infoSortId) {
		String hql = "from Dhsz where infoSortId =? order by taxis asc";

		return dao.find(hql, new Object[] { infoSortId });
	}
}
