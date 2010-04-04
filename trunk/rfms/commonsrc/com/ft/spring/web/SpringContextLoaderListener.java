package com.ft.spring.web;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * WEBӦ������ʱװ��Spring���á�
 * @author <a href="mailto:liuliang2@126.com">����</a>
 * @version 1.0
 */
public class SpringContextLoaderListener extends ContextLoaderListener {

    /**
     * ��ʼ��WEBӦ�������Ļ�����
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
