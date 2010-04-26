package com.renhenet.web.member;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.BusService;
import com.renhenet.po.Bus;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.util.searchcontext.SearchOption;
import com.renhenet.web.DispatchActions;

public class BusAction extends DispatchActions {
	
	private static BusService service = (BusService) ServiceLocator.getService("busService");

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		String name = delSearchOption(context, "name");
		SearchContext searchContext = new SearchContext();
		if (!StringUtils.isEmpty(name)) {
			searchContext.addOption(new SearchOption("name", "%" + name + "%", SearchOption.Option.like));
		}
		return searchContext;
	}

	@Override
	protected CommonService getService() {
		return service;
	}

	@Override
	protected Class getActionClass() {
		return Bus.class;
	}

	@Override
	public String getWebMenuType(WebContext context) throws ServletException {
		return "bus";
	}

}
