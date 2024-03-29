package com.renhenet.fw;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ServiceLocator {
	private static ApplicationContext context;

	private static Log log = LogFactory.getLog(ServiceLocator.class);

	static {
		long startTime = System.currentTimeMillis();
		String[] configs;
		if(System.getProperty("spring.configfile") != null){
			configs = StringUtils.split(System.getProperty("spring.configfile"), ",");
		}else{
			configs = Config.getStringArray("sys.spring.configfile");	
		}
		
		context = new ClassPathXmlApplicationContext(configs);
		long elapsedTime = System.currentTimeMillis() - startTime;
		log.info("Spring initialization completed in " + elapsedTime + " ms");
	}

	public static void init() {
		// 已经在static中初始化了
	}

	public static ApplicationContext getContext() {
		return context;
	}

	public static void setContext(ApplicationContext context) {
		ServiceLocator.context = context;
	}

	public static void destroy() {
		context = null;
	}

	public static Object getService(String beanName) {
		return context.getBean(beanName);
	}
}