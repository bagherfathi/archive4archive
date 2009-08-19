package com.renhenet.modules.member;

import java.util.List;

import com.renhenet.modules.CommonService;
import com.renhenet.po.File;

public class FileService extends CommonService {
	public List<File> getFileByInfoSortId(int infoSortId) {
		String hql = "from File where  infoSortId =?";
		return (List<File>) dao.find(hql, new Object[] { infoSortId });
	}

}
