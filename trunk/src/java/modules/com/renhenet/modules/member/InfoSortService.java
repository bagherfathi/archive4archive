package com.renhenet.modules.member;

import java.util.ArrayList;
import java.util.List;

import com.renhenet.modules.CommonService;
import com.renhenet.po.InfoSort;
import com.renhenet.po.InfoSortDTO;

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

	public List<InfoSortDTO> getInfoSortNameByInfoSortId(int infoSortId) {
		List args = new ArrayList();
		String sql = "select name from info_sort t start with id= "
				+ infoSortId + " connect by id = prior parent_id order by id asc";
		List<Object[]> oos = dao.executeQueryBySQL(sql, null, args.toArray());

		List<InfoSortDTO> vos = null;
		if (oos != null && oos.size() > 0) {
			vos = new ArrayList(oos.size());
			for (Object[] oo : oos) {
				InfoSortDTO infoSortDTO = new InfoSortDTO();
				infoSortDTO.setName(oo[0] + "");

				vos.add(infoSortDTO);
			}
		}
		return vos;
	}
}
