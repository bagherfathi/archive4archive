package com.renhenet.web;

import javax.servlet.ServletException;

import com.renhenet.fw.waf.BaseAction;
import com.renhenet.fw.waf.WebContext;

public abstract class AdminAction extends BaseAction implements WebConstants {
	public String process(WebContext context) throws ServletException {
		if (!WebHelper.isSigned(context)) {
			return LOGIN_FORWARD;
		} else {
			return adminProcess(context);
		}
	}

	public abstract String adminProcess(WebContext context)
			throws ServletException;

}