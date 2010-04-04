package com.ft.commons.web.context;

import javax.servlet.ServletContext;

/**
 * ��ʼ��WEBӦ�������ĵĽӿ�
 */
public interface Loader {
	/**
	 * ��ʼ������
	 * @param context
	 */
	public void initContext(ServletContext context) throws InitializationException;
	
	/**
	 * ���ٷ���
	 * @param context
	 */
	public void destroyContext(ServletContext context);
}
