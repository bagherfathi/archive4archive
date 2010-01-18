package com.renhenet.modules.member;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.renhenet.modules.CommonService;
import com.renhenet.po.InfoSortDTO;
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

		return (List<Structure>) dao.find(hql, new Object[] { infoSortId });
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
		String taxis = "taxis";
		if (status == 1) {
			taxis = "taxis2";
		} else if (status == 2) {
			taxis = "taxis3";
		}

		hql += " and isDelete=0 order by " + taxis + " asc";

		return (List<Structure>) dao.find(hql, new Object[] { infoSortId });
	}

	// 根据信息门类得到最后添加的一条Structure记录处理
	public int getStructureByinfoSortId(int infoSortId) {
		List args = new ArrayList();
		String sql = "select substr(serial_number,2,5) from structure where info_sort_id ="+infoSortId
		+" order by to_number(substr(serial_number,2,5)) desc";
		
		List<Object[]> oos = dao.executeQueryBySQL(sql, null, args.toArray());
		
		int number =1;
		if (oos != null && oos.size() > 0) {
			if(oos.get(0)!=null){
				number = new Integer(oos.get(0)[0]+"")+1;
			}else{
				return 1;
			}
		}
		
		return number;
		
	}

}
