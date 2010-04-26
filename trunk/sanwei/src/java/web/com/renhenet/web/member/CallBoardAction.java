package com.renhenet.web.member;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.renhenet.fw.ServiceLocator;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.modules.CommonService;
import com.renhenet.modules.member.CallBoardService;
import com.renhenet.po.CallBoard;
import com.renhenet.util.searchcontext.SearchContext;
import com.renhenet.util.searchcontext.SearchOption;
import com.renhenet.web.DispatchActions;

public class CallBoardAction extends DispatchActions {
	private static CallBoardService service = (CallBoardService) ServiceLocator.getService("callBoardService");

	@Override
	protected SearchContext getListSearchContext(WebContext context) {
		String subject = delSearchOption(context, "subject");
		String content = delSearchOption(context, "content");
		SearchContext searchContext = new SearchContext();
		if (!StringUtils.isEmpty(subject)) {
			searchContext.addOption(new SearchOption("subject", "%" + subject + "%", SearchOption.Option.like));
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
		return CallBoard.class;
	}

	@Override
	public String getWebMenuType(WebContext context) throws ServletException {
		return "callboard";
	}

}
