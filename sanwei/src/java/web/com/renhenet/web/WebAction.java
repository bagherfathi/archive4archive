package com.renhenet.web;

import javax.servlet.ServletException;

import com.renhenet.fw.waf.BaseAction;
import com.renhenet.fw.waf.WebContext;
import com.renhenet.po.Member;

/**
 * 
 * Title:
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2005
 * 
 * @author luoxn
 */
public abstract class WebAction extends BaseAction implements WebConstants {
	/**
	 * @see com.renhenet.fw.waf.BaseAction#process(com.renhenet.fw.waf.WebContext)
	 */
	public String process(WebContext context) throws ServletException {
		Member member = WebHelper.getMemberContext(context);

		if (context.getParameter("rmsg") != null) {
			context.put("rmsg", context.getParameter("rmsg"));
		}
		if (context.getParameter("emsg") != null) {
			context.put("emsg", context.getParameter("emsg"));
		}
		if (context.getParameter("wmsg") != null) {
			context.put("wmsg", context.getParameter("wmsg"));
		}

		return webProcess(context, member);
	}

	/**
	 * ·½·¨µÄÃèÊö.
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param response
	 * @return
	 * @throws
	 * @throws ServletException
	 */
	public abstract String webProcess(WebContext context, Member member)
			throws ServletException;

}