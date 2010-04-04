package com.ft.webui.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * @web.listener
 * @author Coffee
 *
 */
public class WebUIContextLoaderListener implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent event) {
    }

    public void contextInitialized(ServletContextEvent event) {
        new WebUIContextImpl(event.getServletContext());
    }
}
