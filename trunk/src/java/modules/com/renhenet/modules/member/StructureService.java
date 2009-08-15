package com.renhenet.modules.member;

import java.util.List;

import com.renhenet.modules.CommonService;
import com.renhenet.po.Structure;

public class StructureService extends CommonService {
	@SuppressWarnings("unchecked")
	public List<Structure> getStructureByInfoSortId(int infoSortId) {
		String hql = "from Structure where infoSortId =?";
		
		return (List<Structure>) dao.find(hql, new Object[]{infoSortId});
	}

}
