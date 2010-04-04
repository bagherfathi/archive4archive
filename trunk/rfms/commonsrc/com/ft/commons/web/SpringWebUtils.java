package com.ft.commons.web;

import org.springframework.context.ApplicationContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * ��WEBӦ���еõ�Spring���е�BEAN����
 * 
 */
public class SpringWebUtils {
	/**
	 * �õ�WebӦ�õ�ApplicationContext
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
	 * �õ�WebӦ�õ�ApplicationContext
	 * 
	 * @param context
	 *            ServletContext
	 * 
	 * @return Spring �е�ApplicationContext
	 */
	public static ApplicationContext getApplicationContext(
			HttpServletRequest request) {
		return getApplicationContext(request.getSession().getServletContext());
	}

	/**
	 * ����ServletContext �õ�ApplicationContext�е�һ��BEAN
	 * 
	 * @param context
	 *            ServletContext
	 * @param name
	 *            bean����
	 * 
	 * @return Spring �ж�Ӧ���Ƶ�BEAN
	 */
	public static Object getBean(ServletContext context, String name) {
		return getApplicationContext(context).getBean(name);
	}

	/**
	 * ��Request�еõ�һ��Bean
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param name
	 *            bean����
	 * 
	 * @return
	 */
	public static Object getBean(HttpServletRequest request, String name) {
		return getApplicationContext(request).getBean(name);
	}

	/**
	 * ��PageContext�еõ�Bean
	 * 
	 * @param pageContext
	 *            PageContext
	 * @param name
	 *            bean����
	 * 
	 * @return Spring �ж�Ӧ���Ƶ�BEAN
	 */
	public static Object getBean(PageContext pageContext, String name) {
		return getApplicationContext(pageContext.getServletContext()).getBean(
				name);
	}

	/**
	 * ��Request�������� �õ�һ��Bean
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param name
	 *            ����
	 * 
	 * @return Spring �ж�Ӧ���Ƶ�BEAN
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
