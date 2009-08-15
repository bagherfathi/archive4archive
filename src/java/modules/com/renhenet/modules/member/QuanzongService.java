package com.renhenet.modules.member;

import java.util.List;

import com.renhenet.modules.CommonService;
import com.renhenet.po.Quanzong;

public class QuanzongService extends CommonService {
	@SuppressWarnings("unchecked")
	public List<Quanzong> getQuanzong() {
		String hql = "from Quanzong";
		return (List<Quanzong>) dao.find(hql, null);
	}
	
	@SuppressWarnings("unchecked")
	public List<Quanzong> getQuanzongByType(int type) {
		String hql = "from Quanzong where type=?";
		return (List<Quanzong>) dao.find(hql, new Object[]{type});
	}
}
