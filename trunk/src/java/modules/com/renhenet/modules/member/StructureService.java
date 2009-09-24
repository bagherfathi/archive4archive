package com.renhenet.modules.member;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.renhenet.modules.CommonService;
import com.renhenet.po.Structure;

public class StructureService extends CommonService {
	private static Log logger = LogFactory.getLog(StructureService.class);

	@SuppressWarnings("unchecked")
	public List<Structure> getStructureByInfoSortId(int infoSortId, int status) {
		String hql = "from Structure where infoSortId =? and status =? and isDelete=0 order by taxis asc";

		return (List<Structure>) dao.find(hql, new Object[] { infoSortId,
				status });
	}
	
	public List<Structure> getStructureByInfoSortIdAndNotDelete(int infoSortId) {
		String hql = "from Structure where infoSortId =? and isDelete=0 order by taxis asc";

		return (List<Structure>) dao.find(hql, new Object[] { infoSortId});
	}

	/**
	 * 得到没有说出的所有可以作为档号的字段
	 * 
	 * @param infoSortId
	 * @param status
	 * @return
	 */
	public List<Structure> getStructureByInfoSortId(int infoSortId) {
		String hql = "from Structure where infoSortId =? and isDelete=0 and isDhpz = 1 order by taxis asc";

		return (List<Structure>) dao.find(hql, new Object[] { infoSortId });
	}

	public Structure getStructureByInfoSortIdAndSerialNumber(int infoSortId,
			String serialNumber) {
		String hql = "from Structure where infoSortId=? and serialNumber=?";
		return (Structure) dao.findSingle(hql, new Object[] { infoSortId,
				serialNumber });
	}

	@SuppressWarnings("unchecked")
	public List<Structure> getStructureByInfoSortIdAndInStatus(int infoSortId,
			int status) {

		String hql = "from Structure where infoSortId =?";

		if (status == 0) {
			hql += " and status in(0)";
		} else if (status == 1) {
			hql += " and status in(0,1) and ifTwo =0";
		} else if (status == 2) {
			hql += " and status in(0,1,2) and ifTwo =0 and ifThree=0";
		}

		hql += " and isDelete=0 order by taxis asc";

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
			return new Integer(str.substring(1, str.length())) + 1;
		}

	}

}
