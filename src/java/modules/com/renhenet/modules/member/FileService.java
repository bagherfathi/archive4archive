package com.renhenet.modules.member;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.po.File;

public class FileService extends CommonService {
	@SuppressWarnings("unchecked")
	public List<File> getFileByInfoSortId(int infoSortId, int parInfoSortId) {
		String hql = "from File where  infoSortId =? and parInfoSortId=? order by id desc";
		return (List<File>) dao.find(hql, new Object[] { infoSortId,
				parInfoSortId });
	}

	/**
	 * 得到最近添加的一条该类别文件数据
	 * 
	 * @param infoSortId
	 * @return
	 */
	public File getFileByInfoSortId(int infoSortId) {
		String hql = "from File where  infoSortId =?order by id desc";
		return (File) dao.findSingle(hql, new Object[] { infoSortId });
	}

	public List<File> getFileByInfoSortId(int infoSortId, int parInfoSortId,
			WebContext context) {
		String hql = "from File where  infoSortId =? and parInfoSortId=? ";
		for (int i = 0; i <= 100; i++) {
			String name = "a" + i;
			String value = context.getParameter(name);

			if (!StringUtils.isEmpty(value)) {
				hql += " and " + name + " like '%" + value + "%'";
			}
		}

		hql += " order by id desc";
		return (List<File>) dao.find(hql, new Object[] { infoSortId,
				parInfoSortId });
	}

	/**
	 * 把需要归档的文件取出来
	 * 
	 * @param infoSortId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<File> getFileByInfoSortIdAndType(int infoSortId, int type) {
		String hql = "from File where  infoSortId =? and type=? order by id desc";
		return (List<File>) dao.find(hql, new Object[] { infoSortId, type });
	}

	@SuppressWarnings("unchecked")
	public List<File> getFileByParInfoSortId(int parInfoSortId) {
		String hql = "from File where  parInfoSortId =? order by id desc";
		return (List<File>) dao.find(hql, new Object[] { parInfoSortId });
	}

	public List<File> getFileByParInfoSortId(String parInfoSortId) {
		String hql = "from File where  parInfoSortId in(?) order by id desc";
		return (List<File>) dao.find(hql, new Object[] { parInfoSortId });
	}

	@SuppressWarnings("unchecked")
	public List<File> getFileByInfoSortIdAnd(int infoSortId, String a5,
			int parInfoSortId) {
		StringBuffer query = new StringBuffer();
		List args = new ArrayList();
		query.append("SELECT * FROM files WHERE contains(a5,'" + a5
				+ "',1) > 0  and parInfoSortId=" + parInfoSortId
				+ " and info_sort_id =" + infoSortId
				+ " ORDER BY score(1) desc");

		List<Object[]> oos = dao.executeQueryBySQL(query.toString(), null, args
				.toArray());

		List<File> vos = null;
		if (oos != null && oos.size() > 0) {
			File file = new File();
			vos = new ArrayList(oos.size());
			for (Object[] oo : oos) {
				file.setId(Integer.parseInt(oo[0] + ""));
				file.setInfoSortId(Integer.parseInt(oo[1] + ""));
				file.setTitle(oo[2] + "");
				file.setA1(oo[3] + "");
				file.setA2(oo[4] + "");
				file.setA3(oo[5] + "");
				file.setA4(oo[6] + "");
				file.setA5(oo[7] + "");
				file.setA6(oo[8] + "");
				file.setA7(oo[9] + "");
				file.setA8(oo[10] + "");
				file.setA9(oo[11] + "");
				file.setA10(oo[12] + "");
				file.setA11(oo[13] + "");
				file.setA12(oo[14] + "");
				file.setA13(oo[15] + "");
				file.setA14(oo[16] + "");
				file.setA15(oo[17] + "");
				file.setA16(oo[18] + "");
				file.setA17(oo[19] + "");
				file.setA18(oo[20] + "");
				file.setA19(oo[21] + "");
				file.setA20(oo[22] + "");
				file.setA21(oo[23] + "");
				file.setA22(oo[24] + "");
				file.setA23(oo[25] + "");
				file.setA24(oo[26] + "");
				file.setA25(oo[27] + "");
				file.setA26(oo[28] + "");
				file.setA27(oo[29] + "");
				file.setA28(oo[30] + "");
				file.setA29(oo[31] + "");
				file.setA30(oo[32] + "");
				file.setA31(oo[33] + "");
				file.setA32(oo[34] + "");
				file.setA33(oo[35] + "");
				file.setA34(oo[36] + "");
				file.setA35(oo[37] + "");
				file.setA36(oo[38] + "");
				file.setA37(oo[39] + "");
				file.setA38(oo[40] + "");
				file.setA39(oo[41] + "");
				file.setA40(oo[42] + "");
				file.setA41(oo[43] + "");
				file.setA42(oo[44] + "");
				file.setA43(oo[45] + "");
				file.setA44(oo[46] + "");
				file.setA45(oo[47] + "");
				file.setA46(oo[48] + "");
				file.setA47(oo[49] + "");
				file.setA48(oo[50] + "");
				file.setA49(oo[51] + "");
				file.setA50(oo[52] + "");

				vos.add(file);
			}
		}
		return vos;

	}

}
