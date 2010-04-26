package com.renhenet.web.member;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.MarkService;
import com.renhenet.po.Mark;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.util.searchcontext.SearchOption;
import com.renhenet.web.DispatchActions;

public class MarkAction extends DispatchActions {
	
	private static MarkService service = (MarkService) ServiceLocator.getService("markService");

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
		return Mark.class;
	}

	@Override
	public String getWebMenuType(WebContext context) throws ServletException {
		return "mark";
	}

}
