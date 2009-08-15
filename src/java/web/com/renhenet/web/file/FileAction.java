package com.renhenet.web.file;

import java.util.List;

import javax.servlet.ServletException;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.FileService;
import com.renhenet.modules.member.StructureService;
import com.renhenet.po.File;
import com.renhenet.po.Structure;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.util.searchcontext.SearchOption;
import com.renhenet.web.DispatchActions;

public class FileAction extends DispatchActions {
	private static FileService service = (FileService) ServiceLocator
			.getService("fileService");
	private static StructureService structureService = (StructureService) ServiceLocator
			.getService("structureService");

	@SuppressWarnings("unchecked")
	@Override
	protected Class getActionClass() {
		return File.class;
	}

	public String insertProcess(WebContext context) throws ServletException {
		int infoSortId = context.getSIntParameter("infoSortId");
		context.put("infoSortId", infoSortId);

		List<Structure> structureList = structureService
				.getStructureByInfoSortId(infoSortId);
		context.put("structureList", structureList);

		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {

		}
		return super.insertProcess(context);
	}

	public String updateProcess(WebContext context) throws ServletException {

		return super.updateProcess(context);
	}

	public String deleteProcess(WebContext context) throws ServletException {

		return super.deleteProcess(context);
	}

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		SearchContext searchContext = new SearchContext();

		int infoSortId = context.getSIntParameter("infoSortId");

		if (infoSortId >= 0) {
			searchContext.addOption(new SearchOption("infoSortId", infoSortId,
					SearchOption.Option.eqaul));
		}

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

}
