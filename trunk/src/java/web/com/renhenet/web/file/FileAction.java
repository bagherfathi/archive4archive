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
		f.setId(null);
		context.put("bizObj", f);

		List<Structure> structureList = structureService
				.getStructureByInfoSortIdAndInStatus(infoSortId, status);
		context.put("structureList", structureList);

		// �õ������������ĵ���
		if (status > 0) {
			// �õ��ϲ�ĵ����ļ�������
			List<File> fileList = service.getFileByParInfoSortId(parInfoSortId);
			context.put("fileList", fileList);
		}

		// �õ�����������,������
		List<Dhsz> dhszList1 = dhszService.getDhszByinfoSortId(infoSortId);
		List<Dhsz> dhszList = new ArrayList();

		String strStructure = "";
		for (Dhsz dhsz : dhszList1) {
			Structure structure = (Structure) service.getObjectById(
					Structure.class, dhsz.getStructureId());
			strStructure += "," + structure.getSerialNumber() + ",";
			// structure.setZnName(structure.getZnName().substring(0,
			// dhsz.getLen()));

			dhsz.setStructure(structure);
			dhszList.add(dhsz);
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

	public String deleteProcess(WebContext context) throws ServletException {
		int infoSortId = context.getSIntParameter("infoSortIds");
		context.put("infoSortId", infoSortId);

		int status = context.getSIntParameter("statuses");
		context.put("status", status);

		int parInfoSortId = context.getSIntParameter("parInfoSortIds");
		context.put("parInfoSortId", parInfoSortId);

		super.deleteProcess(context);

		return "/file/actions.html?method=insert&parInfoSortIds="
				+ VMUtils.encrypt(parInfoSortId) + "&infoSortIds="
				+ VMUtils.encrypt(infoSortId) + "&statuses="
				+ VMUtils.encrypt(status) + "&cm=" + context.getParameter("cm");
	}

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		SearchContext searchContext = new SearchContext();
		int infoSortId = context.getSIntParameter("infoSortId");

		context.put("infoSortId", infoSortId);

		int status = context.getSIntParameter("statuses");
		context.put("status", status);

		List<Structure> structureList = structureService
				.getStructureByInfoSortId(infoSortId, status);

		context.put("structureList", structureList);
		String a5 = context.getParameter("titleA5");
		context.put("titleA5", a5);
		a5 = this.getTitles(a5);

		List<File> fileList = null;
		if (!StringUtils.isEmpty(a5)) {
			fileList = service.getFileByInfoSortIdAnd(infoSortId, a5, 0);
		} else {
			fileList = service.getFileByInfoSortId(infoSortId, 0, context);
		}
		context.put("fileList", fileList);

		InfoSort infoSort = (InfoSort) infoSortService.getObjectById(
				InfoSort.class, infoSortId);
		context.put("infoSort", infoSort);

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
