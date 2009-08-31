package com.renhenet.modules.member;

import java.util.List;

import com.renhenet.modules.CommonService;
import com.renhenet.po.FilePigeonhole;

public class FilePigeonholeService extends CommonService {
	@SuppressWarnings("unchecked")
	public List<FilePigeonhole> getFilePigeonholeByInfoSortId(int infoSortId) {
		String hql = "from FilePigeonhole where infoSortId=? order by id desc ";
		return dao.find(hql, new Object[] { infoSortId });
	}

}
