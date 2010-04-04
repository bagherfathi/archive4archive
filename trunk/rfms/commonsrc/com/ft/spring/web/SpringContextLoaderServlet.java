package com.ft.spring.web;

import javax.servlet.ServletException;

import org.springframework.web.context.ContextLoaderServlet;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * WEBӦ������ʱװ��Spring���á�
 * 
 * @author <a href="mailto:liuliang2@126.com">����</a>
 * @version 1.0
 */
public class SpringContextLoaderServlet extends ContextLoaderServlet {
    private static final long serialVersionUID = 5521255650785034016L;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.context.ContextLoaderServlet#init()
     */
    public void init() throws ServletException {
	super.init();

	WebApplicationContext applicationContext = WebApplicationContextUtils
		.getWebApplicationContext(this.getServletContext());
	SpringContextUtils.setBeanFactory(applicationContext);
    }
}
