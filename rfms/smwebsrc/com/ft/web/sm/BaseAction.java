package com.ft.web.sm;

import org.apache.struts.action.ActionForward;
import org.apache.struts.actions.DispatchAction;

import com.ft.busi.model.BusiAppService;

/**
 * ϵͳ���������п�����Ļ���
 * 
 * @version 1.0
 */
public class BaseAction extends DispatchAction {

	protected BusiAppService appService;

	/**
	 * @spring.property ref="busiAppService"
	 * @param appService
	 *            the appService to set
	 */
	public void setAppService(BusiAppService appService) {
		this.appService = appService;
	}

	public ActionForward getRedirectForwardAction(String name) {
		ActionForward result = new ActionForward();
		result.setPath(name);
		// result.setContextRelative(true);
		result.setRedirect(true);
		return result;
	}
}
