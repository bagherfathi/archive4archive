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

	public List<File> getFileByInfoSortIdAndParInfoSortIdAndStatus(
			int infoSortId, int parInfoSortId, int status, WebContext context,
			int startNum, int num) {
		String hql = "from File where status=? ";
		if (infoSortId > 0) {
			hql += " and infoSortId =" + infoSortId;
		}
		if (parInfoSortId > 0) {
			hql += "and parInfoSortId=" + parInfoSortId;
		}
		for (int i = 0; i <= 100; i++) {
			String name = "a" + i;
			String value = context.getParameter(name);

			if (!StringUtils.isEmpty(value)) {
				hql += " and " + name + " like '%" + value + "%'";
			}
		}

		hql += " order by id desc";
		return (List<File>) dao.find(hql, new Object[] { status }, startNum,
				num);
	}

	public int getNumByInfoSortIdAndParInfoSortIdAndStatus(int infoSortId,
			int parInfoSortId, int status, WebContext context) {
		String hql = "select count(*)  from File where status=? ";
		if (infoSortId > 0) {
			hql += " and infoSortId =" + infoSortId;
		}
		if (parInfoSortId > 0) {
			hql += " and parInfoSortId=" + parInfoSortId;
		}

		for (int i = 0; i <= 100; i++) {
			String name = "a" + i;
			String value = context.getParameter(name);

			if (!StringUtils.isEmpty(value)) {
				hql += " and " + name + " like '%" + value + "%'";
			}
		}

		hql += " order by id desc";
		int num = dao.getCount(hql, new Object[] { status });

		return num;
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

	/**
	 * 得到提名模糊查询后的值
	 * 
	 * @param infoSortId
	 * @param a5
	 * @param parInfoSortId
	 * @param startNum
	 * @param num
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<File> getFileByInfoSortIdAnd(int infoSortId, String a5,   int statuses,
			int parInfoSortId, int startNum, int num) {
		StringBuffer query = new StringBuffer();
		List args = new ArrayList();

		query.append("SELECT * FROM files WHERE contains(a5,'" + a5
				+ "',1) > 0  " 
//                " and par_info_sort_id=" + parInfoSortId
                +" and status="+statuses
				+ " and info_sort_id =" + infoSortId
				+ " ORDER BY score(1) desc");

		if (startNum > 0) {
			query.append(" limit" + startNum + "," + num);
		}

		List<Object[]> oos = dao.executeQueryBySQL(query.toString(), null, args
				.toArray());

		List<File> vos = null;
		if (oos != null && oos.size() > 0) {
			File file =null;
			vos = new ArrayList(oos.size());
			for (Object[] oo : oos) {
                file = new File();
				file.setId(Integer.parseInt(oo[0] + ""));
				file.setInfoSortId(Integer.parseInt(oo[1] + ""));
				file.setStatus(new Integer(oo[2] + "").intValue());
				file.setParInfoSortId(new Integer(oo[3] + "").intValue());
				file.setType(new Integer(oo[4] + "").intValue());
				file.setTitle(oo[5] + "");
				file.setA1(oo[6] + "");
				file.setA2(oo[7] + "");
				file.setA3(oo[8] + "");
				file.setA4(oo[9] + "");
				file.setA5(oo[10] + "");
				file.setA6(oo[11] + "");
				file.setA7(oo[12] + "");
				file.setA8(oo[13] + "");
				file.setA9(oo[14] + "");
				file.setA10(oo[15] + "");
				file.setA11(oo[16] + "");
				file.setA12(oo[17] + "");
				file.setA13(oo[18] + "");
				file.setA14(oo[19] + "");
				file.setA15(oo[20] + "");
				file.setA16(oo[21] + "");
				file.setA17(oo[22] + "");
				file.setA18(oo[23] + "");
				file.setA19(oo[24] + "");
				file.setA20(oo[25] + "");
				file.setA21(oo[26] + "");
				file.setA22(oo[27] + "");
				file.setA23(oo[28] + "");
				file.setA24(oo[29] + "");
				file.setA25(oo[30] + "");
				file.setA26(oo[31] + "");
				file.setA27(oo[32] + "");
				file.setA28(oo[33] + "");
				file.setA29(oo[34] + "");
				file.setA30(oo[35] + "");
				file.setA31(oo[36] + "");
				file.setA32(oo[37] + "");
				file.setA33(oo[38] + "");
				file.setA34(oo[39] + "");
				file.setA35(oo[40] + "");
				file.setA36(oo[41] + "");
				file.setA37(oo[42] + "");
				file.setA38(oo[43] + "");
				file.setA39(oo[44] + "");
				file.setA40(oo[45] + "");
				file.setA41(oo[46] + "");
				file.setA42(oo[47] + "");
				file.setA43(oo[48] + "");
				file.setA44(oo[49] + "");
				file.setA45(oo[50] + "");
				file.setA46(oo[51] + "");
				file.setA47(oo[52] + "");
				file.setA48(oo[53] + "");
				file.setA49(oo[54] + "");
				file.setA50(oo[55] + "");
				file.setA51(oo[56] + "");
				file.setA52(oo[57] + "");
				file.setA53(oo[58] + "");
				file.setA54(oo[59] + "");
				file.setA55(oo[60] + "");
				file.setA56(oo[61] + "");
				file.setA57(oo[62] + "");
				file.setA58(oo[63] + "");
				file.setA59(oo[64] + "");
				file.setA60(oo[65] + "");
				file.setA61(oo[66] + "");
				file.setA62(oo[67] + "");
				file.setA63(oo[68] + "");
				file.setA64(oo[69] + "");
				file.setA65(oo[70] + "");
				file.setA66(oo[71] + "");
				file.setA67(oo[72] + "");
				file.setA68(oo[73] + "");
				file.setA69(oo[74] + "");
				file.setA70(oo[75] + "");
				file.setA71(oo[76] + "");
				file.setA72(oo[77] + "");
				file.setA73(oo[78] + "");
				file.setA74(oo[79] + "");
				file.setA75(oo[80] + "");
				file.setA76(oo[81] + "");
				file.setA77(oo[82] + "");
				file.setA78(oo[83] + "");
				file.setA79(oo[84] + "");
				file.setA80(oo[85] + "");
				file.setA81(oo[86] + "");
				file.setA82(oo[87] + "");
				file.setA83(oo[88] + "");
				file.setA84(oo[89] + "");
				file.setA85(oo[90] + "");
				file.setA86(oo[91] + "");
				file.setA87(oo[92] + "");
				file.setA88(oo[93] + "");
				file.setA89(oo[94] + "");
				file.setA90(oo[95] + "");
				file.setA91(oo[96] + "");
				file.setA92(oo[97] + "");
				file.setA93(oo[98] + "");
				file.setA94(oo[99] + "");
				file.setA95(oo[100] + "");
				file.setA96(oo[101] + "");
				file.setA97(oo[102] + "");
				file.setA98(oo[103] + "");
				file.setA99(oo[104] + "");
				file.setA100(oo[105] + "");

				vos.add(file);
			}
		}
		return vos;
	}

	public int getNumByInfoSortIdAndA5(int infoSortId, String a5,
			int parInfoSortId,int statuses, int startNum, int num) {
		StringBuffer query = new StringBuffer();
		List args = new ArrayList();

		query.append("SELECT count(*) FROM files WHERE contains(a5,'" + a5
				+ "',1) > 0" 
//                "  and par_info_sort_id=" + parInfoSortId
                +" and status="+statuses
				+ " and info_sort_id =" + infoSortId
				+ " ORDER BY score(1) desc");

		if (startNum > 0) {
			query.append(" limit" + startNum + "," + num);
		}

		List<Object[]> oos = dao.executeQueryBySQL(query.toString(), null, args
				.toArray());

		return Integer.parseInt(oos.get(0)[0].toString());
	}
}
