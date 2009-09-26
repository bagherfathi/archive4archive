package com.renhenet.modules.member;

import java.util.List;

import com.renhenet.modules.CommonService;
import com.renhenet.po.Dhsz;

public class DhszService extends CommonService {
	/**
	 * ���ݷ���ŵõ���Ӧ�ĵ�����Ϣ
	 * 
	 * @param infoSortId
	 * @return
	 */
	public List<Dhsz> getDhszByinfoSortId(int infoSortId) {
		String hql = "from Dhsz where infoSortId =? order by taxis asc";

		return dao.find(hql, new Object[] { infoSortId });
	}
}
