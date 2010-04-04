package com.ft.commons.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ft.commons.expr.ExpressionEvaluator;


/**
 *
 *
 * @web.listener
 */
public class HSQLDBServletContextListener implements ServletContextListener {
    public final static String HSQLDB_PARAMETER = "HSQLDB_PARAMETER";
    private Log logger = LogFactory.getLog(this.getClass());
    private org.hsqldb.Server server;

    /**
     * 上下文的初始化
     * 
     */
    public void contextInitialized(ServletContextEvent evt) {
        try {
            String paramString =
                evt.getServletContext().getInitParameter(HSQLDB_PARAMETER);
            paramString = ExpressionEvaluator.evaluate(
                    paramString, "web.root" ,evt.getServletContext().getRealPath("/"));
            server = new org.hsqldb.Server();
            server.putPropertiesFromString(paramString);
            server.setLogWriter(null);
            server.setErrWriter(null);
            server.start();
            logger.info("start HSQLDB with parameter " + paramString);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void contextDestroyed(ServletContextEvent evt) {
        if (server != null) {
            server.shutdown();
            logger.info("shutdown HSQLDB ");
        }
    }
}
