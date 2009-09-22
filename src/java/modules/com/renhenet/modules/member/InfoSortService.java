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

	/**
	 * 得到可以copy的底层分类
	 * 
	 * @param type
	 * @return
	 */
	public List<InfoSort> getInfoSortByTypeAndCopy(int type) {
		String hql = "from InfoSort where type =? and copy=0 order by id desc";

		return (List<InfoSort>) dao.find(hql, new Object[] { type });
	}

	public List<InfoSort> getInfoSortByNotTypeAndCopy(int type) {
		String hql = "from InfoSort where type <>? order by id desc";

		return (List<InfoSort>) dao.find(hql, new Object[] { type });
	}

	/**
	 * 得到所有底层分类
	 * 
	 * @param type
	 * @return
	 */
	public List<InfoSort> getInfoSortByType(int type) {
		String hql = "from InfoSort where type =? order by id desc";

		return (List<InfoSort>) dao.find(hql, new Object[] { type });
	}

}
