/*
package com.ft.common.sso;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ft.sso.api.app.ApplicationAuthHandler;
import com.ft.sso.api.app.DataEventHandler;
import com.ft.sso.api.app.OperatorAuthHandler;
import com.ft.sso.api.app.PluginWebLoader;

*//**
 * 用于装载SSO插件.
 * 
 * @version 1.0
 *//*
public class SSOPluginWebLoader implements PluginWebLoader {

    
     * (non-Javadoc)
     * 
     * @see com.ft.sso.api.app.PluginWebLoader#loadOperatorAuthHandler(javax.servlet.ServletContext)
     
    public OperatorAuthHandler loadOperatorAuthHandler(
            ServletContext servletContext) {
        ApplicationContext applicationContext = WebApplicationContextUtils
                .getWebApplicationContext(servletContext);
        OperatorAuthHandler result = (OperatorAuthHandler) applicationContext
                .getBean("afterLoginContextResolver");
        return result;
    }

    
     * (non-Javadoc)
     * 
     * @see com.ft.sso.api.app.PluginWebLoader#loadDataEventHandler(javax.servlet.ServletContext)
     
    public DataEventHandler loadDataEventHandler(ServletContext servletContext) {
        ApplicationContext applicationContext = WebApplicationContextUtils
                .getWebApplicationContext(servletContext);
        DataEventHandler dataEventHandler = (DataEventHandler) applicationContext
                .getBean("ssoDataEventHandler");
        return dataEventHandler;
    }

    
     * (non-Javadoc)
     * 
     * @see com.ft.sso.api.app.PluginWebLoader#loadApplicationAuthHandler(javax.servlet.ServletContext)
     
    public ApplicationAuthHandler loadApplicationAuthHandler(
            ServletContext servletContext) {
        // TODO Auto-generated method stub
        return null;
    }
}
*/