package com.renhenet.modules.member;

import java.util.List;
import com.renhenet.po.Accessory;
import com.renhenet.modules.CommonService;

public class AccessoryService extends CommonService {
	public List<Accessory> getAccessoryByFileId(int fileId) {
		String hql = "from Accessory where fileId =?";
		return dao.find(hql, new Object[] {fileId});
	}
}
