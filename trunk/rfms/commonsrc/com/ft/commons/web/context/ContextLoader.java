package com.ft.commons.web.context;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 从Spring配置文件中查找所有实现Loader接口的bean，在初始化时调用各自的初始化方法初始化
 * WEB应用上下文；在销毁时调用各自销毁方法销毁WEB应用上下文。
 */
public class ContextLoader {
	private final Log logger = LogFactory.getLog(ContextLoader.class);
	
	private String[] loaderBeanNames;
	
	/**
	 * 初始化应用上下文
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
	 * 应用关系时，销毁上下文中的内容
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
