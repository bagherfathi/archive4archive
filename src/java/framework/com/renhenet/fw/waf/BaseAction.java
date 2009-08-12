package com.renhenet.fw.waf;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.renhenet.web.WebHelper;

public abstract class BaseAction extends Action {

	protected static String DEFAULT_FORWARD = "show";

	/**
	 * ���Ƿ��� (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WebContext context = new WebContext(getServlet().getServletContext(),
				request, response, form);
		WebHelper.putCommonValues(context);
		String af = process(context);
		context.saveCookie();
		ActionForward forword;
		if (af != null && af.startsWith("/")) {
			forword = new ActionForward(af, true);
		} else {
			forword = mapping.findForward(af);
		}
		return forword;
	}

	/**
	 * ����������.
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param response
	 * @return
	 * @throws
	 * @throws ServletException
	 */
	public abstract String process(WebContext context) throws ServletException;

}