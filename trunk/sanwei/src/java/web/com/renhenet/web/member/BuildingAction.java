package com.renhenet.web.member;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.BuildingService;
import com.renhenet.po.Building;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.util.searchcontext.SearchOption;
import com.renhenet.web.DispatchActions;

public class BuildingAction extends DispatchActions {
	private static BuildingService service = (BuildingService) ServiceLocator.getService("buildingService");

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		String subject = delSearchOption(context, "subject");
		SearchContext searchContext = new SearchContext();
		if (!StringUtils.isEmpty(subject)) {
			searchContext.addOption(new SearchOption("subject", "%" + subject + "%", SearchOption.Option.like));
		}
		return searchContext;
	}

	@Override
	protected CommonService getService() {
		return service;
	}

	@Override
	protected Class getActionClass() {
		return Building.class;
	}

	@Override
	public String getWebMenuType(WebContext context) throws ServletException {
		return "building";
	}

}
