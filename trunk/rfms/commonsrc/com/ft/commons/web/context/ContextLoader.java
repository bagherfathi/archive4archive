package com.ft.commons.web.context;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * ��Spring�����ļ��в�������ʵ��Loader�ӿڵ�bean���ڳ�ʼ��ʱ���ø��Եĳ�ʼ��������ʼ��
 * WEBӦ�������ģ�������ʱ���ø������ٷ�������WEBӦ�������ġ�
 */
public class ContextLoader {
	private final Log logger = LogFactory.getLog(ContextLoader.class);
	
	private String[] loaderBeanNames;
	
	/**
	 * ��ʼ��Ӧ��������
	 * @param context
	 */
	public void initAppContext(ServletContext context){
		long startTime = System.currentTimeMillis();
		
		if (logger.isInfoEnabled()) {
			logger.info("Web application context Initialization started");
		}
		
		try {
			WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(context);
			
			loaderBeanNames = appContext.getBeanNamesForType(Loader.class);
			
			for (int i = 0; i < loaderBeanNames.length; i++) {
				Loader loader = (Loader)appContext.getBean(loaderBeanNames[i]);
				loader.initContext(context);
			}
			
			if(logger.isInfoEnabled()){
				long total = System.currentTimeMillis() - startTime;
				logger.info("Initialize web application context elapsed times:" + total + " ms.");
			}
		}
		catch (RuntimeException ex) {
			logger.error("Context initialization failed", ex);
			throw ex;
		}
		catch (Error err) {
			logger.error("Context initialization failed", err);
			throw err;
		}
	}
	
	/**
	 * Ӧ�ù�ϵʱ�������������е�����
	 * @param context
	 */
	public void destroyAppContext(ServletContext context){
		if(loaderBeanNames != null && loaderBeanNames.length >0){
			WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(context);
			for (int i = 0; i < loaderBeanNames.length; i++) {
				Loader loader = (Loader)appContext.getBean(loaderBeanNames[i]);
				loader.destroyContext(context);
			}
		}
	}
}
