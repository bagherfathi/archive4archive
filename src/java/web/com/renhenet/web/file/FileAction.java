package com.renhenet.web.file;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.DhszService;
import com.renhenet.modules.member.FileService;
import com.renhenet.modules.member.InfoSortService;
import com.renhenet.modules.member.StructureService;
import com.renhenet.po.Dhsz;
import com.renhenet.po.File;
import com.renhenet.po.InfoSort;
import com.renhenet.po.Structure;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.web.DispatchActions;
import com.renhenet.web.Pagination;
import com.renhenet.web.VMUtils;
import com.renhenet.web.form.FileForm;

public class FileAction extends DispatchActions {
	private static FileService service = (FileService) ServiceLocator
			.getService("fileService");
	private static StructureService structureService = (StructureService) ServiceLocator
			.getService("structureService");

	private static InfoSortService infoSortService = (InfoSortService) ServiceLocator
			.getService("infoSortService");

	private static DhszService dhszService = (DhszService) ServiceLocator
			.getService("dhszService");

	@SuppressWarnings("unchecked")
	@Override
	protected Class getActionClass() {
		return File.class;
	}

	public String insertProcess(WebContext context) throws ServletException {
		int infoSortId = context.getSIntParameter("infoSortIds");
		context.put("infoSortId", infoSortId);

		InfoSort infoSort = (InfoSort) infoSortService.getObjectById(
				InfoSort.class, infoSortId);
		context.put("infoSort", infoSort);

		int type = context.getIntParameter("types");
		context.put("type", type);

		int parInfoSortId = context.getSIntParameter("parInfoSortIds");
		context.put("parInfoSortId", parInfoSortId);

		int status = context.getSIntParameter("statuses");
		context.put("status", status);

		File f = (File) service.getFileByInfoSortId(infoSortId);
		if (f != null) {
			f.setId(null);
			context.put("bizObj", f);
		}
		List<Structure> structureList = structureService
				.getStructureByInfoSortIdAndInStatus(infoSortId, status);
		context.put("structureList", structureList);

		// 得到二层或者三层的档案
		if (status > 0) {
			// 得到上层的档案文件的内容
			List<File> fileList = service.getFileByParInfoSortId(parInfoSortId);
			context.put("fileList", fileList);
		}

		// 得到档号设置项,处理档号
		List<Dhsz> dhszList1 = dhszService.getDhszByinfoSortId(infoSortId);
		List<Dhsz> dhszList = new ArrayList();

		String strStructure = "";
		for (Dhsz dhsz : dhszList1) {
			Structure structure = (Structure) service.getObjectById(
					Structure.class, dhsz.getStructureId());
			if (structure != null) {
				strStructure += "," + structure.getSerialNumber() + ",";

				dhsz.setStructure(structure);
				dhszList.add(dhsz);
			}
		}
		context.put("strStructure", strStructure);
		context.put("dhszList", dhszList);

		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			FileForm form = (FileForm) context.getForm();
			super.insertProcess(context);

			return "/file/actions.html?method=insert&parInfoSortIds="
					+ VMUtils.encrypt(parInfoSortId) + "&infoSortIds="
					+ VMUtils.encrypt(form.getInfoSortId()) + "&statuses="
					+ VMUtils.encrypt(status) + "&cm="
					+ context.getParameter("cm");
		}
		return super.insertProcess(context);
	}

	public String updateProcess(WebContext context) throws ServletException {
		int infoSortId = context.getSIntParameter("infoSortIds");
		context.put("infoSortId", infoSortId);

		// 得到档号设置项,处理档号
		List<Dhsz> dhszList1 = dhszService.getDhszByinfoSortId(infoSortId);
		List<Dhsz> dhszList = new ArrayList();

		String strStructure = "";
		for (Dhsz dhsz : dhszList1) {
			Structure structure = (Structure) service.getObjectById(
					Structure.class, dhsz.getStructureId());
			if (structure != null) {
				strStructure += "," + structure.getSerialNumber() + ",";
				// structure.setZnName(structure.getZnName().substring(0,
				// dhsz.getLen()));

				dhsz.setStructure(structure);
				dhszList.add(dhsz);
			}
		}
		context.put("strStructure", strStructure);
		context.put("dhszList", dhszList);

		InfoSort infoSort = (InfoSort) infoSortService.getObjectById(
				InfoSort.class, infoSortId);
		context.put("infoSort", infoSort);

		int status = context.getSIntParameter("statuses");
		context.put("status", status);

		List<Structure> structureList = structureService
				.getStructureByInfoSortIdAndInStatus(infoSortId, status);
		context.put("structureList", structureList);

		return super.updateProcess(context);
	}

	public String detailProcess(WebContext context) throws ServletException {
		int infoSortId = context.getSIntParameter("infoSortIds");
		context.put("infoSortId", infoSortId);

		InfoSort infoSort = (InfoSort) infoSortService.getObjectById(
				InfoSort.class, infoSortId);
		context.put("infoSort", infoSort);

		int status = context.getSIntParameter("statuses");
		context.put("status", status);

		List<Structure> structureList = structureService
				.getStructureByInfoSortIdAndInStatus(infoSortId, status);
		context.put("structureList", structureList);

		return super.detailProcess(context);
	}

	public String deleteProcess(WebContext context) throws ServletException {
		int infoSortId = context.getSIntParameter("infoSortIds");
		context.put("infoSortId", infoSortId);

		int status = context.getSIntParameter("statuses");
		context.put("status", status);

		int parInfoSortId = context.getSIntParameter("parInfoSortIds");
		context.put("parInfoSortId", parInfoSortId);

		super.deleteProcess(context);

		return "/file/actions.html?method=list&parInfoSortIds="
				+ VMUtils.encrypt(parInfoSortId) + "&infoSortIds="
				+ VMUtils.encrypt(infoSortId) + "&statuses="
				+ VMUtils.encrypt(status) + "&cm=" + context.getParameter("cm");
	}

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		SearchContext searchContext = new SearchContext();
		int infoSortId = context.getSIntParameter("infoSortIds");
		int startNum = context.getIntParameter("start");
		context.put("startNum", startNum);
		boolean ajax = false;
		if ("true".equals(context.getParameter("ajax"))) {
			ajax = true;
		}
		context.put("ajax", ajax);

		if (startNum < 0) {
			startNum = 0;
		}
		int pageSize = context.getIntParameter("pageSize");
		context.put("pageSize", pageSize);
		if (pageSize <= 0) {
			pageSize = 10;
		}
		context.put("pageSize", pageSize);

		int typeses = context.getIntParameter("typeses");
		if (typeses != 0) {
			typeses = 1;
		}
		context.put("typeses", typeses);

		int parInfoSortId = context.getSIntParameter("parInfoSortIds");
		context.put("parInfoSortId", parInfoSortId);

		int parparInfoSortId = context.getSIntParameter("parparInfoSortIds");
		context.put("parparInfoSortId", parparInfoSortId);

		context.put("infoSortId", infoSortId);

		int status = context.getSIntParameter("statuses");
		context.put("status", status);

		int statuses = context.getIntParameter("statuses1");
		context.put("statuses", statuses);
		int statuses2 = context.getIntParameter("statuses2");
		context.put("statuses2", statuses2);

		InfoSort infoSort = (InfoSort) infoSortService.getObjectById(
				InfoSort.class, infoSortId);
		context.put("infoSort", infoSort);

		// 得到分类有几层

		// 第一层
		List<Structure> structureList = structureService
				.getStructureByInfoSortIdAndInStatus(infoSortId, 0);
		context.put("structureList", structureList);

		if (infoSort.getStatus() > 0) {
			// 第二层
			List<Structure> structureList1 = structureService
					.getStructureByInfoSortIdAndInStatus(infoSortId, 1);
			context.put("structureList1", structureList1);
		}
		if (infoSort.getStatus() == 2) {
			// 第三层
			List<Structure> structureList2 = structureService
					.getStructureByInfoSortIdAndInStatus(infoSortId, 2);
			context.put("structureList2", structureList2);
		}

		String a5 = context.getParameter("titleA5");
		context.put("titleA5", a5);
		a5 = this.getTitles(a5);

		List<File> fileList = null;
		if (!StringUtils.isEmpty(a5)) {
			fileList = service.getFileByInfoSortIdAnd(infoSortId, a5, statuses,
					0, startNum, startNum + 10);
			int num = service.getNumByInfoSortIdAndA5(infoSortId, a5, statuses,
					0, 0, 0);
			Pagination pagination = new Pagination(num, startNum, pageSize);

			context.put("pagination1", pagination);
		} else {
			for (int i = 1; i <= 100; i++) {
				if (!StringUtils.isEmpty(context.getParameter("a" + i))) {
					context.put("a" + i, context.getParameter("a" + i));
				}
			}

			String strA22 = "";
			// 判断是否是人事档案
			if (infoSortId == 1048) {
				// 根据身份证编号查询 a16身份证号 a22内码
				String sfz = context.getParameter("a16");
				if (!StringUtils.isEmpty(sfz)) {
					List<File> fileLists = service.getFileByInfoSortIdAndSfz(
							infoSortId, infoSortId, sfz);
					if (fileLists != null) {
						for (int i = 0; i < fileLists.size(); i++) {
							File file = fileLists.get(i);
							if (i == fileLists.size() - 1) {
								strA22 += file.getA22();
							} else {
								strA22 += file.getA22() + ",";
							}
						}
					}
					// //put进context
					// if (!StringUtils.isEmpty(strA22)) {
					// context.put("a22", strA22);
					// }
				}
			}

			// 第1层
			if (statuses2 != 1 && statuses2 != 2) {
				int num1 = service.getNumByInfoSortIdAndParInfoSortIdAndStatus(
						infoSortId, 0, 0, context, strA22);
				fileList = service
						.getFileByInfoSortIdAndParInfoSortIdAndStatus(
								infoSortId, 0, 0, context, startNum, pageSize);
				Pagination pagination = new Pagination(num1, startNum, pageSize);
				context.put("pagination1", pagination);
			}
			// 第2层
			// if (parInfoSortId > 0) {
			if (statuses2 == 1) {
				int num2 = service.getNumByInfoSortIdAndParInfoSortIdAndStatus(
						infoSortId, parInfoSortId, 1, context, strA22);
				if (parInfoSortId > 0
						|| "y".equals(context.getParameter("search"))) {
					pageSize = 0;
				}
				List<File> fileList2 = service
						.getFileByInfoSortIdAndParInfoSortIdAndStatus(
								infoSortId, parInfoSortId, 1, context,
								startNum, pageSize);
				context.put("fileList2", fileList2);
			}
			// Pagination pagination2 = new Pagination(num2, startNum,
			// pageSize);
			// context.put("pagination2", pagination2);
			// }

			// 第3层
			// if (parparInfoSortId > 0) {
			if (statuses2 == 2) {
				int num3 = service.getNumByInfoSortIdAndParInfoSortIdAndStatus(
						infoSortId, parparInfoSortId, 2, context, strA22);
				List<File> fileList3 = service
						.getFileByInfoSortIdAndParInfoSortIdAndStatus(
								infoSortId, parparInfoSortId, 2, context,
								startNum, pageSize);
				context.put("fileList3", fileList3);

			}

			// Pagination pagination3 = new Pagination(num3, startNum,
			// pageSize);
			// context.put("pagination3", pagination3);
			// }
		}
		context.put("fileList", fileList);

		return searchContext;
	}

	@Override
	protected CommonService getService() {
		return service;
	}

	@Override
	public String getWebMenuType(WebContext context) throws ServletException {
		return "file";
	}

	public String getTitles(String title) {
		if (title == null) {
			return "";
		}
		String a5 = title;
		String strA5 = "";
		String tempString = "";
		if (a5.length() < 2) {
			return a5;
		}

		tempString = a5.substring(0, 2);
		strA5 = tempString;
		a5 = a5.substring(2, a5.length());
		for (int i = a5.length(); i > 1; i--) {
			if (i % 2 != 0) {
				tempString = a5.substring(0, 2);
				strA5 = strA5 + "," + tempString;
				a5 = a5.substring(2, a5.length());
			}
		}
		if (a5.length() != 0) {
			strA5 = strA5 + "," + a5;
		}
		return strA5;
	}

}
