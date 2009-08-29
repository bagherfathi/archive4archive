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

	public Structure getStructureByInfoSortIdAndSerialNumber(int infoSortId,
			String serialNumber) {
		String hql = "from Structure where infoSortId=? and serialNumber=?";
		return (Structure) dao.findSingle(hql, new Object[] { infoSortId,
				serialNumber });
	}

	public List<Structure> getStructureByInfoSortIdAndInStatus(int infoSortId,
			int status) {
		String hql = "from Structure where infoSortId =?";
		if (status == 0) {
			hql += " and status in(0)";
		} else if (status == 1) {
			hql += " and status in(0,1)";
		} else if (status == 2) {
			hql += " and status in(0,1,2)";
		}

		hql += " and isDelete=0 order by taxis asc";

		return (List<Structure>) dao.find(hql, new Object[] { infoSortId });
	}

	// 根据信息门类得到最后添加的一条Structure记录处理
	public int getStructureByinfoSortId(int infoSortId, int status) {
		String hql = "from Structure where infoSortId =? and status=? order by id desc";
		logger.info(infoSortId + "___" + status);
		Structure structure = (Structure) dao.findSingle(hql, new Object[] {
				infoSortId, status });
		if (structure == null) {
			return 1;
		} else {
			String str = structure.getSerialNumber();
			return new Integer(str.substring(1, str.length())) + 1;
		}

	}

}
