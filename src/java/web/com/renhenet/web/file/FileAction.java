package com.renhenet.web.file;

import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.FileService;
import com.renhenet.modules.member.InfoSortService;
import com.renhenet.modules.member.StructureService;
import com.renhenet.po.File;
import com.renhenet.po.InfoSort;
import com.renhenet.po.Structure;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.util.searchcontext.SearchOption;
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

	@SuppressWarnings("unchecked")
	@Override
	protected Class getActionClass() {
		return File.class;
	}

	public String insertProcess(WebContext context) throws ServletException {
		int infoSortId = context.getSIntParameter("infoSortIds");
		context.put("infoSortId", infoSortId);

		List<Structure> structureList = structureService
				.getStructureByInfoSortId(infoSortId);
		context.put("structureList", structureList);

		String cm = context.getParameter("cm");
		context.put("cm", cm);

		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			FileForm form = (FileForm) context.getForm();
			super.insertProcess(context);

			return "/file/actions.html?method=list&oc=all&infoSortId="
					+ VMUtils.encrypt(form.getInfoSortId()) + "&cm=" + cm;
		}
		return super.insertProcess(context);
	}

	public String updateProcess(WebContext context) throws ServletException {
		int infoSortId = context.getSIntParameter("infoSortIds");
		context.put("infoSortId", infoSortId);

		String cm = context.getParameter("cm");
		context.put("cm", cm);

		List<Structure> structureList = structureService
				.getStructureByInfoSortId(infoSortId);
		context.put("structureList", structureList);

		return super.updateProcess(context);
	}

	public String deleteProcess(WebContext context) throws ServletException {
		int infoSortId = context.getSIntParameter("infoSortIds");
		context.put("infoSortId", infoSortId);

		String cm = context.getParameter("cm");
		context.put("cm", cm);
		super.deleteProcess(context);

		return "/file/actions.html?method=list&oc=all&infoSortId="
				+ VMUtils.encrypt(infoSortId) + "&cm=" + cm;
	}

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		SearchContext searchContext = new SearchContext();

		int infoSortId = context.getSIntParameter("infoSortId");

		if (infoSortId >= 0) {
			searchContext.addOption(new SearchOption("infoSortId", infoSortId,
					SearchOption.Option.eqaul));
		}
		context.put("infoSortId", infoSortId);

		List<Structure> structureList = structureService
				.getStructureByInfoSortId(infoSortId);
		context.put("structureList", structureList);
		String a5 = context.getParameter("a5");
		context.put("a5", a5);
		a5 = this.getTitles(a5);

		List<File> fileList = null;
		if (!StringUtils.isEmpty(a5)) {
			fileList = service.getFileByInfoSortIdAnd(infoSortId, a5);
		} else {
			fileList = service.getFileByInfoSortId(infoSortId);
		}
		context.put("fileList", fileList);

		InfoSort infoSort = (InfoSort) infoSortService.getObjectById(
				InfoSort.class, infoSortId);
		context.put("infoSort", infoSort);

		String cm = context.getParameter("cm");
		context.put("cm", cm);

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
