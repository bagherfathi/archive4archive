/**
 * @{#} ParameterFilterHttpServletRequestProxy.java Create on 2006-7-23 10:57:10
 *
 * Copyright (c) 2006 by Onewave Inc.
 */
package com.ft.struts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 过滤POJO中不能修改的属性，主要重载了getParameters,getParameterMap,getParameters的方法
 * 
 * @author Coffee
 * 
 */
class ParameterFilterHttpServletRequestProxy implements HttpServletRequest {

	public static final String ESC_SPACE_PARAMETER = "ESC_SPACE_DISABLE";

	HttpServletRequest proxyRequest;

	// 要过滤的参数
	List filterParamNames;

	/**
	 * 构造方法
	 * 
	 * @param proxyRequest
	 *            原始请求对象
	 * @param filterParamNames
	 *            要过滤的参数
	 */
	public ParameterFilterHttpServletRequestProxy(
			HttpServletRequest proxyRequest, List filterParamNames) {
		this.proxyRequest = proxyRequest;
		this.filterParamNames = filterParamNames;
	}
	/**
	 * 是否要去空格
	 * @return
	 */
	protected boolean isEnableESC() {
		String value = this.proxyRequest.getParameter(ESC_SPACE_PARAMETER);

		return !"false".equalsIgnoreCase(value);

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getAttribute(java.lang.String)
	 */
	public Object getAttribute(String arg0) {
		return proxyRequest.getAttribute(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getAttributeNames()
	 */
	public Enumeration getAttributeNames() {
		return proxyRequest.getAttributeNames();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getAuthType()
	 */
	public String getAuthType() {
		return proxyRequest.getAuthType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getCharacterEncoding()
	 */
	public String getCharacterEncoding() {
		return proxyRequest.getCharacterEncoding();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getContentLength()
	 */
	public int getContentLength() {
		return proxyRequest.getContentLength();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getContentType()
	 */
	public String getContentType() {
		return proxyRequest.getContentType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getContextPath()
	 */
	public String getContextPath() {
		return proxyRequest.getContextPath();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getCookies()
	 */
	public Cookie[] getCookies() {
		return proxyRequest.getCookies();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getDateHeader(java.lang.String)
	 */
	public long getDateHeader(String arg0) {
		return proxyRequest.getDateHeader(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getHeader(java.lang.String)
	 */
	public String getHeader(String arg0) {
		return proxyRequest.getHeader(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getHeaderNames()
	 */
	public Enumeration getHeaderNames() {
		return proxyRequest.getHeaderNames();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getHeaders(java.lang.String)
	 */
	public Enumeration getHeaders(String arg0) {
		return proxyRequest.getHeaders(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getInputStream()
	 */
	public ServletInputStream getInputStream() throws IOException {
		return proxyRequest.getInputStream();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getIntHeader(java.lang.String)
	 */
	public int getIntHeader(String arg0) {
		return proxyRequest.getIntHeader(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getLocale()
	 */
	public Locale getLocale() {
		return proxyRequest.getLocale();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getLocales()
	 */
	public Enumeration getLocales() {
		return proxyRequest.getLocales();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getMethod()
	 */
	public String getMethod() {
		return proxyRequest.getMethod();
	}

	/**
	 * 处理参数,去空格
	 * 
	 * @param arg0
	 *            得到要处理的参数
	 */
	public String getParameter(String arg0) {
		String result = proxyRequest.getParameter(arg0);
		if (result != null && this.isEnableESC()) {
			result = result.trim();
		}
		return result;
	}

	/**
	 * 处理请求的参数，过滤不要处理的参数
	 */
	public Map getParameterMap() {
		Map map = proxyRequest.getParameterMap();
		List params = filterParamNames;
		if (params != null) {
			// 过不处理的参数
			for (Iterator iter = params.iterator(); iter.hasNext();) {
				String element = (String) iter.next();
				map.remove(element);

			}
		}
		if(this.isEnableESC()){
		// 除去参数中的空格

			for (Iterator values = map.entrySet().iterator(); values.hasNext();) {
				Map.Entry element = (Map.Entry) values.next();
				if (element.getValue() != null
						&& element.getValue() instanceof String) {
					String str = (String) element.getValue();
					element.setValue(str.trim());
				}
			}
		}
		return map;
	}

	/**
	 * 根据要过滤字段列表，过滤参数名称
	 */
	public Enumeration getParameterNames() {
		Enumeration result = proxyRequest.getParameterNames();

		if (filterParamNames == null || filterParamNames.size() == 0) {
			return result;
		} else {
			List params = new ArrayList();
			for (; result.hasMoreElements();) {
				String element = (String) result.nextElement();
				if (!this.filterParamNames.contains(element)) {
					params.add(element);
				}
			}
			return Collections.enumeration(params);
		}

	}

	/**
	 * 处理空格，将用户提交的参数去空格
	 * 
	 * @param arg0
	 *            参数名
	 */
	public String[] getParameterValues(String arg0) {
		
		String[] result = proxyRequest.getParameterValues(arg0);
		if(this.isEnableESC()){
			for (int i = 0; i < result.length; i++) {
				result[i] = result[i].trim();
			}
		
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getPathInfo()
	 */
	public String getPathInfo() {
		return proxyRequest.getPathInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getPathTranslated()
	 */
	public String getPathTranslated() {
		return proxyRequest.getPathTranslated();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getProtocol()
	 */
	public String getProtocol() {
		return proxyRequest.getProtocol();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getQueryString()
	 */
	public String getQueryString() {
		return proxyRequest.getQueryString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getReader()
	 */
	public BufferedReader getReader() throws IOException {
		return proxyRequest.getReader();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getRealPath(java.lang.String)
	 */
	public String getRealPath(String arg0) {
		return proxyRequest.getRealPath(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getRemoteAddr()
	 */
	public String getRemoteAddr() {
		return proxyRequest.getRemoteAddr();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getRemoteHost()
	 */
	public String getRemoteHost() {
		return proxyRequest.getRemoteHost();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getRemoteUser()
	 */
	public String getRemoteUser() {
		return proxyRequest.getRemoteUser();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getRequestDispatcher(java.lang.String)
	 */
	public RequestDispatcher getRequestDispatcher(String arg0) {
		return proxyRequest.getRequestDispatcher(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getRequestedSessionId()
	 */
	public String getRequestedSessionId() {
		return proxyRequest.getRequestedSessionId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getRequestURI()
	 */
	public String getRequestURI() {
		return proxyRequest.getRequestURI();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getRequestURL()
	 */
	public StringBuffer getRequestURL() {
		return proxyRequest.getRequestURL();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getScheme()
	 */
	public String getScheme() {
		return proxyRequest.getScheme();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getServerName()
	 */
	public String getServerName() {
		return proxyRequest.getServerName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#getServerPort()
	 */
	public int getServerPort() {
		return proxyRequest.getServerPort();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getServletPath()
	 */
	public String getServletPath() {
		return proxyRequest.getServletPath();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getSession()
	 */
	public HttpSession getSession() {
		return proxyRequest.getSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getSession(boolean)
	 */
	public HttpSession getSession(boolean arg0) {
		return proxyRequest.getSession(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#getUserPrincipal()
	 */
	public Principal getUserPrincipal() {
		return proxyRequest.getUserPrincipal();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromCookie()
	 */
	public boolean isRequestedSessionIdFromCookie() {
		return proxyRequest.isRequestedSessionIdFromCookie();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromUrl()
	 */
	public boolean isRequestedSessionIdFromUrl() {
		return proxyRequest.isRequestedSessionIdFromUrl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromURL()
	 */
	public boolean isRequestedSessionIdFromURL() {
		return proxyRequest.isRequestedSessionIdFromURL();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdValid()
	 */
	public boolean isRequestedSessionIdValid() {
		return proxyRequest.isRequestedSessionIdValid();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#isSecure()
	 */
	public boolean isSecure() {
		return proxyRequest.isSecure();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletRequest#isUserInRole(java.lang.String)
	 */
	public boolean isUserInRole(String arg0) {
		return proxyRequest.isUserInRole(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#removeAttribute(java.lang.String)
	 */
	public void removeAttribute(String arg0) {
		proxyRequest.removeAttribute(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#setAttribute(java.lang.String,
	 *      java.lang.Object)
	 */
	public void setAttribute(String arg0, Object arg1) {
		proxyRequest.setAttribute(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequest#setCharacterEncoding(java.lang.String)
	 */
	public void setCharacterEncoding(String arg0)
			throws UnsupportedEncodingException {
		proxyRequest.setCharacterEncoding(arg0);
	}
	
	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return proxyRequest.getLocalAddr();
	}
	public String getLocalName() {
		// TODO Auto-generated method stub
		return proxyRequest.getLocalName();
	}
	public int getLocalPort() {
		// TODO Auto-generated method stub
		return proxyRequest.getLocalPort();
	}
	public int getRemotePort() {
		// TODO Auto-generated method stub
		return proxyRequest.getRemotePort();
	}

	

}
