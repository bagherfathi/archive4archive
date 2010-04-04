package com.ft.commons.web;

import org.springframework.context.ApplicationContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * 在WEB应用中得到Spring配中的BEAN对象
 * 
 */
public class SpringWebUtils {
	/**
	 * 得到Web应用的ApplicationContext
	 * 
	 * @param context
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext(
			ServletContext context) {
		WebApplicationContext appContext = WebApplicationContextUtils
				.getWebApplicationContext(context);

		return appContext;
	}

	/**
	 * 得到Web应用的ApplicationContext
	 * 
	 * @param context
	 *            ServletContext
	 * 
	 * @return Spring 中的ApplicationContext
	 */
	public static ApplicationContext getApplicationContext(
			HttpServletRequest request) {
		return getApplicationContext(request.getSession().getServletContext());
	}

	/**
	 * 根据ServletContext 得到ApplicationContext中的一个BEAN
	 * 
	 * @param context
	 *            ServletContext
	 * @param name
	 *            bean名称
	 * 
	 * @return Spring 中对应名称的BEAN
	 */
	public static Object getBean(ServletContext context, String name) {
		return getApplicationContext(context).getBean(name);
	}

	/**
	 * 从Request中得到一个Bean
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param name
	 *            bean名称
	 * 
	 * @return
	 */
	public static Object getBean(HttpServletRequest request, String name) {
		return getApplicationContext(request).getBean(name);
	}

	/**
	 * 从PageContext中得到Bean
	 * 
	 * @param pageContext
	 *            PageContext
	 * @param name
	 *            bean名称
	 * 
	 * @return Spring 中对应名称的BEAN
	 */
	public static Object getBean(PageContext pageContext, String name) {
		return getApplicationContext(pageContext.getServletContext()).getBean(
				name);
	}

	/**
	 * 从Request根据类名 得到一个Bean
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param name
	 *            类名
	 * 
	 * @return Spring 中对应名称的BEAN
	 */
	public static Object getBeanByClassName(HttpServletRequest request,
			String name) {
		WebApplicationContext appContext = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		Class clazz;

		try {
			clazz = Thread.currentThread().getContextClassLoader().loadClass(
					name);

			String[] names = appContext.getBeanNamesForType(clazz);

			if (names.length > 0) {
				return appContext.getBean(names[0]);
			}
		} catch (ClassNotFoundException e) {
			return null;
		}

		return null;
	}
}
