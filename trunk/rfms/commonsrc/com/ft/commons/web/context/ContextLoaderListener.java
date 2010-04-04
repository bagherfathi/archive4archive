package com.ft.commons.web.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * WEB应用启动时初始化其上下文
 */
public class ContextLoaderListener implements ServletContextListener{
	private ContextLoader contextLoader;
	
	/**
	 * Initialize the web application context.
	 */
	public void contextInitialized(ServletContextEvent event) {
		contextLoader = createContextLoader();
		contextLoader.initAppContext(event.getServletContext());
	}

	/**
	 * Close the web application context.
	 */
	public void contextDestroyed(ServletContextEvent event) {
		if (this.contextLoader != null) {
			this.contextLoader.destroyAppContext(event.getServletContext());
		}
	}
	
	/**
	 * Create the ContextLoader to use. Can be overridden in subclasses.
	 * @return the new ContextLoader
	 */
	protected ContextLoader createContextLoader() {
		return new ContextLoader();
	}

}
