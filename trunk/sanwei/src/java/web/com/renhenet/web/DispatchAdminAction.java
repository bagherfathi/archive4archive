package com.renhenet.web;

import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.renhenet.fw.waf.WebContext;
@SuppressWarnings("unchecked")
public abstract class DispatchAdminAction extends DispatchAction implements
		WebConstants {

	/**
	 * The Class instance of this <code>DispatchAction</code> class.
	 */
	protected Class clazz = this.getClass();

	protected Class[] types = { WebContext.class };

	/**
	 * 覆盖方法 (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward action(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String methodName, String contextAction) throws Exception {
		WebContext context = new WebContext(getServlet().getServletContext(),
				request, response, form);

		WebHelper.putCommonValues(context);

		context.put("action", contextAction);
		context.put("curMenu", context.getParameter("cm"));

		String temp = getWebMenuType(context);
		if (temp != null) {
			context.put("webMenuType", temp);
		}

		ActionForward forword;
		if (!WebHelper.isSigned(context)) {
			forword = mapping.findForward(LOGIN_FORWARD);
		} else {

			Method method = clazz.getMethod(methodName, types);
			Object args[] = new Object[] { context };
			String af = (String) method.invoke(this, args);
			putArgs(context);
			context.saveCookie();

			if (af.startsWith("/")) {
				forword = new ActionForward(af, true);
			} else {
				forword = mapping.findForward(af);
			}

		}
		return forword;
	}

	/**
	 * 放一些变量，参数在context中，子类可以重载这个方法
	 * 
	 * @param context
	 */
	public void putArgs(WebContext context) {

	}

	/**
	 * 覆盖方法 (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return this.action(mapping, form, request, response, "listProcess",
				"list");
	}

	public abstract String listProcess(WebContext context)
			throws ServletException;

	/**
	 * 覆盖方法 (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.action(mapping, form, request, response, "updateProcess",
				"update");
	}

	public abstract String updateProcess(WebContext context)
			throws ServletException;

	/**
	 * 覆盖方法 (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.action(mapping, form, request, response, "detailProcess",
				"detail");
	}

	public abstract String detailProcess(WebContext context)
			throws ServletException;

	/**
	 * 覆盖方法 (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.action(mapping, form, request, response, "insertProcess",
				"insert");
	}

	public abstract String insertProcess(WebContext context)
			throws ServletException;

	/**
	 * 覆盖方法 (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.action(mapping, form, request, response, "deleteProcess",
				"delete");
	}

	public abstract String deleteProcess(WebContext context)
			throws ServletException;

	/**
	 * 覆盖方法 (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward resume(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.action(mapping, form, request, response, "resumeProcess",
				"resume");
	}

	/**
	 * 覆盖方法 用户自定义的一些行为
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward customActions(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return this.action(mapping, form, request, response,
				"customActionsProcess", "customActions");
	}

	public abstract String customActionsProcess(WebContext context)
			throws ServletException;

	public abstract String getWebMenuType(WebContext context)
			throws ServletException;

}
