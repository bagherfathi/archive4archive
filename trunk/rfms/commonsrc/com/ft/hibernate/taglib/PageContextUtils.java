package com.ft.hibernate.taglib;

import org.apache.struts.taglib.TagUtils;

import org.springframework.context.ApplicationContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;


/**
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PageContextUtils {
    public static String getContextPath(PageContext context) {
        HttpServletRequest request =
            (HttpServletRequest) context.getRequest();

        return request.getContextPath();
    }

    public static Object lookup(
        PageContext pageContext, Object collection, String beanName,
        String beanProperty, String scope) throws JspException {
        Object coll = collection;

        if (coll == null) {
            if ((beanName != null) && (beanProperty != null)) {
                coll = TagUtils.getInstance()
                               .lookup(
                        pageContext, beanName, beanProperty, scope);
            } else if (beanName != null) {
                coll = TagUtils.getInstance()
                               .lookup(pageContext, beanName, scope);
            }
        }

        if (coll == null) {
            coll = new ArrayList();
        }

        return coll;
    }

    public static ApplicationContext getApplicationContext(
        PageContext pageContext) {
        HttpServletRequest request =
            (HttpServletRequest) pageContext.getRequest();
        ServletContext context = request.getSession().getServletContext();
        WebApplicationContext appContext =
            WebApplicationContextUtils.getWebApplicationContext(context);

        return appContext;
    }

    public static int getScope(String scope) {
        int inScope = PageContext.REQUEST_SCOPE;

        try {
            if (scope != null) {
                inScope = TagUtils.getInstance().getScope(scope);
            }
        } catch (JspException e) {
            //  toScope was invalid name so we default to PAGE_SCOPE
        }

        return inScope;
    }

    public static void setAttribute(
        PageContext context, String scope, String var, Object value) {
        context.setAttribute(var, value, getScope(scope));
    }

    public static String encodeURL(PageContext pageContext, String url) {
        HttpServletResponse response =
            (HttpServletResponse) pageContext.getResponse();

        return response.encodeURL(url);
    }
}
