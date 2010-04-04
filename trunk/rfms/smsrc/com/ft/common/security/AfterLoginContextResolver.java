/*
package com.ft.common.security;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ft.common.session.OperatorSessionHandler;
import com.ft.sso.api.app.OperatorAuthHandler;
import com.ft.sso.commons.token.SSOTokenContext;

*//**
 * 实现SSO Agent OperatorAuthHandler接口.
 * 
 * @spring.bean id="afterLoginContextResolver"
 * 
 * @author <a href="mailto:libf@chances.com.cn">libf</a>
 * @version 1.0
 *//*
public class AfterLoginContextResolver implements OperatorAuthHandler {
    private static final Log logger = LogFactory
            .getLog(AfterLoginContextResolver.class);

    private OperatorSessionHandler userSessionHandler;

    
     * (non-Javadoc)
     * 
     * @see com.ft.sso.api.app.OperatorAuthHandler#resolverContext(com.ft.sso.commons.token.SSOTokenContext,
     *      javax.servlet.http.HttpServletRequest)
     
    public void resolverContext(SSOTokenContext context,
            HttpServletRequest httpServletRquest) {
        String ssoCode = context.getOperator().getSsoCode();
        try {
            userSessionHandler.init(httpServletRquest, ssoCode);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    
     * (non-Javadoc)
     * 
     * @see com.ft.sso.api.app.OperatorAuthHandler#unloadContext(com.ft.sso.commons.token.SSOTokenContext)
     
    public void unloadContext(SSOTokenContext context) {
        // TODO Auto-generated method stub

    }

    
     * (non-Javadoc)
     * 
     * @see com.ft.sso.api.app.OperatorAuthHandler#isLogin(javax.servlet.http.HttpServletRequest)
     
    public boolean isLogin(HttpServletRequest httpServletRquest) {
        // TODO Auto-generated method stub
        return false;
    }

    *//**
     * @spring.property ref="opSessionHandler"
     * @param userSessionHandler
     *                The userSessionHandler to set.
     *//*
    public void setUserSessionHandler(OperatorSessionHandler userSessionHandler) {
        this.userSessionHandler = userSessionHandler;
    }

    
     * (non-Javadoc)
     * 
     * @see com.ft.sso.api.app.OperatorAuthHandler#getAppOperatorFilter(javax.servlet.ServletContext)
     
    public Filter getAppOperatorFilter(ServletContext servletContext) {
        ApplicationContext applicationContext = WebApplicationContextUtils
                .getWebApplicationContext(servletContext);
        Filter loginFilter = (Filter) applicationContext
                .getBean("loginCheckerFilter");

        return loginFilter;
    }

}
*/