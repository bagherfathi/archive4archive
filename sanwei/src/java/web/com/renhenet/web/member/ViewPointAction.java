package com.renhenet.web.member;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.ViewPointService;
import com.renhenet.po.ViewPoint;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.util.searchcontext.SearchOption;
import com.renhenet.web.DispatchActions;

public class ViewPointAction extends DispatchActions {
	
	private static ViewPointService service = (ViewPointService) ServiceLocator.getService("viewPointService");

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		String name = delSearchOption(context, "name");
		String content = delSearchOption(context, "content");
		SearchContext searchContext = new SearchContext();
		if (!StringUtils.isEmpty(name)) {
			searchContext.addOption(new SearchOption("name", "%" + name + "%", SearchOption.Option.like));
		}
		if (!StringUtils.isEmpty(content)) {
			searchContext.addOption(new SearchOption("content", "%" + content + "%", SearchOption.Option.like));
		}
		return searchContext;
	}

	@Override
	protected CommonService getService() {
		return service;
	}

	@Override
	protected Class getActionClass() {
		return ViewPoint.class;
	}

	@Override
	public String getWebMenuType(WebContext context) throws ServletException {
		return "viewpoint";
	}

}
