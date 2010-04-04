package com.ft.spring.web;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * WEB应用启动时装载Spring配置。
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
 * @version 1.0
 */
public class SpringContextLoaderListener extends ContextLoaderListener {

    /**
     * 初始化WEB应用上下文环境。
     * 
     * @see org.springframework.web.context.ContextLoaderListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent contextEvent) {
	super.contextInitialized(contextEvent);
	
	WebApplicationContext applicationContext = WebApplicationContextUtils
		.getWebApplicationContext(contextEvent.getServletContext());
	SpringContextUtils.setBeanFactory(applicationContext);
    }
}
