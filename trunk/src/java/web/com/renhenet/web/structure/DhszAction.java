package com.renhenet.web.structure;

import java.util.List;

import javax.servlet.ServletException;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.InfoSortService;
import com.renhenet.modules.member.StructureService;
import com.renhenet.po.InfoSort;
import com.renhenet.po.Structure;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.web.DispatchActions;

public class DhszAction extends DispatchActions {
	private static StructureService service = (StructureService) ServiceLocator
			.getService("structureService");
	private static InfoSortService infoSortService = (InfoSortService) ServiceLocator
			.getService("infoSortService");

	@SuppressWarnings("unchecked")
	@Override
	protected Class getActionClass() {
		return Structure.class;
	}

	public String insertProcess(WebContext context) throws ServletException {
		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			
		}
		int infoSortId = context.getIntParameter("infoSortId");
		context.put("infoSortId", infoSortId);

		// 得到底层分类
		List<InfoSort> infoSortList = infoSortService.getInfoSortByType(2);
		context.put("infoSortList", infoSortList);

		// 根据infosortId得到structure里面要用来作为档号的字段
		List<Structure> structureList = service
				.getStructureByInfoSortId(infoSortId);
		context.put("structureList", structureList);

		return super.insertProcess(context);
	}

	@Override
	protected CommonService getService() {
		return service;
	}

	@Override
	public String getWebMenuType(WebContext context) throws ServletException {
		return "file";
	}

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		return null;
	}

}
