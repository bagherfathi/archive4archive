package com.renhenet.web.structure;

import javax.servlet.ServletException;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.StructureService;
import com.renhenet.po.Structure;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.web.DispatchActions;

public class StructureAction extends DispatchActions {
	private static StructureService service = (StructureService) ServiceLocator
			.getService("structureService");

	@Override
	protected Class getActionClass() {
		return Structure.class;
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
