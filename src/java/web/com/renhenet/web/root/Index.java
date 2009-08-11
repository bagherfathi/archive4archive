package com.renhenet.web.root;

import javax.servlet.ServletException;

import com.renhenet.fw.waf.WebContext;
import com.renhenet.po.Member;
import com.renhenet.web.WebAction;

public class Index extends WebAction {
	@SuppressWarnings("static-access")
	@Override
	public String webProcess(WebContext context, Member member)
			throws ServletException {
		
		return this.DEFAULT_FORWARD;
	}

}
