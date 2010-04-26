package com.renhenet.web.member;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.SpecialPathService;
import com.renhenet.po.SpecialPath;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.util.searchcontext.SearchOption;
import com.renhenet.web.DispatchActions;

public class SpecialPathAction extends DispatchActions {
	private static SpecialPathService service = (SpecialPathService) ServiceLocator.getService("specialPathService");

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		String fileName = delSearchOption(context, "fileName");
		String content = delSearchOption(context, "content");
		SearchContext searchContext = new SearchContext();
		if (!StringUtils.isEmpty(fileName)) {
			searchContext.addOption(new SearchOption("fileName", "%" + fileName + "%", SearchOption.Option.like));
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
		return SpecialPath.class;
	}

	@Override
	public String getWebMenuType(WebContext context) throws ServletException {
		return "specialpath";
	}

}
