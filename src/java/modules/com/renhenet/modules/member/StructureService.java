package com.renhenet.modules.member;

import java.util.List;

import com.renhenet.modules.CommonService;
import com.renhenet.po.Structure;

public class StructureService extends CommonService {
	@SuppressWarnings("unchecked")
	public List<Structure> getStructureByInfoSortId(int infoSortId) {
		String hql = "from Structure where infoSortId =? and isDelete=0 order by taxis asc";

		return (List<Structure>) dao.find(hql, new Object[] { infoSortId });
	}

	// 根据信息门类得到最后添加的一条Structure记录处理
	public int getStructureByinfoSortId(int infoSortId) {
		String hql = "from Structure where infoSortId =? order by id desc";

		Structure structure = (Structure) dao.findSingle(hql,
				new Object[] { infoSortId });
		if (structure == null) {
			return 1;
		} else {
			String str = structure.getSerialNumber();
			return new Integer(str.substring(1, str.length()))+1;
		}

	}

	// public List<Structure> getStructureByInfoSortIdAnd(int infoSortId,
	// int isList) {
	// String hql = "from Structure where infoSortId =? ";
	// if (isList > 0) {
	// hql += " and isList =" + isList;
	// }
	// hql += " order by taxis asc";
	//
	// return (List<Structure>) dao.find(hql, new Object[] { infoSortId });
	// }
}
