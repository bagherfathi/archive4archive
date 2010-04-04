package com.ft.commons.web.context;

import javax.servlet.ServletContext;

/**
 * 初始化WEB应用上下文的接口
 */
public interface Loader {
	/**
	 * 初始化方法
	 * @param context
	 */
	public void initContext(ServletContext context) throws InitializationException;
	
	/**
	 * 销毁方法
	 * @param context
	 */
	public void destroyContext(ServletContext context);
}
