package com.renhenet.web.structure;

import java.util.List;

import javax.servlet.ServletException;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.StructureService;
import com.renhenet.po.Structure;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.web.DispatchActions;
import com.renhenet.web.VMUtils;

public class StructureAction extends DispatchActions {
	private static StructureService service = (StructureService) ServiceLocator
			.getService("structureService");

	@SuppressWarnings("unchecked")
	@Override
	protected Class getActionClass() {
		return Structure.class;
	}

	public String insertProcess(WebContext context) throws ServletException {
		// 根据infoSortId得到所有表结构
		int infoSortId = context.getSIntParameter("infoSortId");
		context.put("infoSortId",infoSortId);
		
		
		List<Structure> structureList = service
				.getStructureByInfoSortId(infoSortId);
		context.put("structureList", structureList);

		if (context.getParameter("insert") != null
				|| context.getParameter("insert2") != null) {
			super.insertProcess(context);
	
			return "/structure/actions.html?method=insert&infoSortId="
					+ VMUtils.encrypt(infoSortId);
		}

		return super.insertProcess(context);
	}

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		SearchContext searchContext = new SearchContext();

		return searchContext;
	}

	@Override
	protected CommonService getService() {
		return service;
	}

	@Override
	public String getWebMenuType(WebContext context) throws ServletException {
		return "structure";
	}

}
