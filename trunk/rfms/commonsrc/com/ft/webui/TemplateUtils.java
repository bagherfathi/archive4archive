package com.ft.webui;

import com.ft.commons.web.SpringWebUtils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.io.StringWriter;
import java.io.Writer;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

public class TemplateUtils {
    private static TemplateUtils util;
    private VelocityEngine velocityEngine;
    private String path;

    protected TemplateUtils() {
        //velocityEngine = ServiceLocator.getVelocityEngine();
    }

    public static TemplateUtils getInstance(ServletContext context) {
        if (util == null) {
            util = new TemplateUtils();
            util.velocityEngine = (VelocityEngine) SpringWebUtils.getBean(
                    context, "velocityEngine");
        }

        return util;
    }

    public Template getTemplate(String name)
        throws ResourceNotFoundException, ParseErrorException, Exception {
        name = name.replaceAll("\\.", "/");

        return velocityEngine.getTemplate(name + ".vm");
    }

    public void merge(String name, String var, Object obj, Writer writer)
        throws ResourceNotFoundException, ParseErrorException, Exception {
        Template template = getTemplate(name);
        VelocityContext context = new VelocityContext();
        context.put(var, obj);
        context.put("contextPath", path);
        template.merge(context, writer);
    }

    public void merge(String name, Map map, Writer writer)
        throws ResourceNotFoundException, ParseErrorException, Exception {
        Template template = getTemplate(name);
        VelocityContext context = new VelocityContext();

        for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
            String key = (String) iter.next();
            context.put(key, map.get(key));
        }

        if (path.equals("/")) {
            context.put("contextPath", "");
        } else {
            context.put("contextPath", path);
        }

        template.merge(context, writer);
    }

    public String merge(String name, Map map)
        throws ResourceNotFoundException, ParseErrorException, Exception {
        StringWriter writer = new StringWriter();
        merge(name, map, writer);

        return writer.toString();
    }

    /**
     * @param context
     */
    public static TemplateUtils getInstance(PageContext context) {
        TemplateUtils result = getInstance(context.getServletContext());
        String path =
            ((HttpServletRequest) context.getRequest()).getContextPath();
        result.path = "/".equals(path) ? "" : path;

        return result;
    }
}
