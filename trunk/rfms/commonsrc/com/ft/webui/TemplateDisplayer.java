package com.ft.webui;

import com.ft.commons.web.SpringWebUtils;

import net.sf.navigator.displayer.MenuDisplayerMapping;
import net.sf.navigator.displayer.MessageResourcesMenuDisplayer;
import net.sf.navigator.menu.MenuComponent;

import org.apache.commons.lang.StringUtils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.app.tools.VelocityFormatter;

import java.io.IOException;
import java.io.StringWriter;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;


public class TemplateDisplayer extends MessageResourcesMenuDisplayer {
    protected VelocityEngine velocityEngine;
    protected PageContext pageContext;
    protected String userSessionName = "";

    public void init(PageContext arg0, MenuDisplayerMapping arg1) {
        super.init(arg0, arg1);
        this.pageContext = arg0;
        this.velocityEngine = (VelocityEngine) SpringWebUtils.getBean(
                arg0, "velocityEngine");
    }

    protected Template getTemplate() throws JspException {
        Template t = null;

        try {
            String template = getConfig();

            if (template == null) {
                throw new JspException(
                    "You must specify a template using the 'config' attribute.");
            } else {
                log.debug("using template: " + template);
            }

            t = velocityEngine.getTemplate(template);
        } catch (Exception e) {
            String msg = "Error initializing Velocity: " + e.toString();
            log.error(msg, e);
            throw new JspException(msg, e);
        }

        return t;
    }

    protected VelocityContext createContext() {
        VelocityContext context = new VelocityContext();
        HttpServletRequest request =
            (HttpServletRequest) pageContext.getRequest();
        context.put("formatter", new VelocityFormatter(context));
        context.put("now", Calendar.getInstance().getTime());
        context.put("ctxPath", request.getContextPath());
        // add a helper class for string manipulation
        context.put("stringUtils", new StringUtils());

        if (userSessionName != null) {
            Object userSession =
                this.pageContext.getAttribute(userSessionName);

            if (userSession != null) {
                context.put("userSession", userSession);
            }
        }

        // add a Map for use by the Explandable List Menu
        context.put("map", new HashMap());

        // see if a name and property were passed in
        if (!StringUtils.isEmpty(name)) {
            Object val1 = pageContext.findAttribute(name);

            if (val1 != null) {
                context.put(name, val1);
            }
        }

        // request-scope attributes
        Enumeration enumer = request.getAttributeNames();

        while (enumer.hasMoreElements()) {
            String name = (String) enumer.nextElement();
            Object value = request.getAttribute(name);
            context.put(name, value);
        }

        context.put("request", request);
        context.put("session", request.getSession());

        return context;
    }

    public void display(MenuComponent menu) throws JspException, IOException {
        if (isAllowed(menu)) {
            displayComponents(menu);
        }
    }

    protected void getOther(VelocityContext context, MenuComponent menu) {
    }

    protected void displayComponents(MenuComponent menu)
        throws JspException, IOException {
        Template t = this.getTemplate();
        StringWriter sw = new StringWriter();
        VelocityContext context = this.createContext();
        context.put("menu", menu);
        getOther(context, menu);

        try {
            t.merge(context, sw);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JspException(e);
        }

        String result = sw.getBuffer().toString();
        pageContext.getOut().print(result);
    }

    public void end(PageContext context) {
        this.pageContext = null;
    }
}
