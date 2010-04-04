package com.ft.singleTable.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.velocity.context.Context;

 

 
public class WebContext implements Context {
	private HttpServletRequest request;

	private HttpServletResponse response;

	private ActionForm form;

	//private ActionMapping mapping; 

	public WebContext(HttpServletRequest newRequest,
			HttpServletResponse newResponse, ActionForm newForm,
			ActionMapping newMapping) {
		this.request = newRequest;
		this.response = newResponse;
		this.form = newForm;
		//this.mapping = newMapping; 
	}

	private WebContext() {

	}

	/**
	 * @return Returns the request.
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @param request
	 *            The request to set.
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * @return Returns the response.
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @param response
	 *            The response to set.
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * @return Returns the form.
	 */
	public ActionForm getForm() {
		return form;
	}

	public Object put(String key, Object value) {
		request.setAttribute(key, value);
		return value;
	}
 
	public Object get(String key) {
		if (StringUtils.isEmpty(key)) {
			return null;
		}
		return request.getAttribute(key);
	}

	/**
	 * 得到表单中的参数
	 * 
	 * @param response
	 *            The String
	 */
	public String getParameter(String key) {
		return StringUtils.trim(request.getParameter(key));
	}

	/**
	 * 得到表单中的参数
	 * 
	 * @param response
	 *            The String
	 */
	public int getIntParameter(String key) {

		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(getParameter(key))) {
			return -1;
		}
		return NumberUtils.toInt((String) getParameter(key));
	}

 
	
	/**
	 * 得到表单中的参数
	 * 
	 * @param response
	 *            The String
	 */
	public double getDoubleParameter(String key) {

		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(getParameter(key))) {
			return -1;
		}
		return NumberUtils.toDouble((String) getParameter(key));
	}

 

	
	/**
	 * 得到表单中的参数
	 * 
	 * @param response
	 *            The String[]
	 */
	public String[] getParameterValues(String key) {
		return request.getParameterValues(key);
	}

	/**
	 * 得到表单中的参数
	 * 
	 * @param response
	 *            The String[]
	 */
	public int[] getIntParameterValues(String key) {
		String[] strings = request.getParameterValues(key);
		if (strings != null && strings.length > 0) {
			int l = strings.length;
			int[] ints = new int[l];
			for (int i = 0; i < l; i++) {
				ints[i] = NumberUtils.toInt(strings[i]);
			}
			return ints;
		} else {
			return null;
		}

	}

	/**
	 * 得到Form中Integer值
	 * 
	 * @param String
	 *            当前参数名
	 * @return int 返回参数值
	 */
	public int getInt(String name) {
		if (StringUtils.isEmpty(name)) {
			return 0;
		}
		return NumberUtils.toInt((String) get(name));
	}

 
	public Object[] getKeys() {
		return null;
	}

	public boolean containsKey(Object a) {
		return false;
	}

	public Object remove(Object key) {
		return null;
	}

}