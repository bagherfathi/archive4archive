package com.ft.struts;

import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.ModuleException;

/**
 * Struts Action异常处理类.
 * 
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
 * @version 1.0
 */
public class StrutsExceptionHandler extends ExceptionHandler {
    public final Log logger = LogFactory.getLog(StrutsExceptionHandler.class);

    /**
     * 错误处理
     */
    public ActionForward execute(Exception ex, ExceptionConfig ae,
            ActionMapping mapping, ActionForm formInstance,
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String value = this.printStackTrace(ex);
        request.setAttribute("exceptionStack", value);
        ActionForward forward = null;
        ActionMessage error = null;
        String property = null;
        if (ae.getPath() != null) {
            forward = new ActionForward(ae.getPath());
        } else {
            forward = mapping.getInputForward();
        }
        if (ex instanceof ModuleException) {
            error = ((ModuleException) ex).getActionMessage();
            property = ((ModuleException) ex).getProperty();
        } else {
            if (hasExceptionMsg(ex, request, null, mapping.getModuleConfig())) {
                error = new ActionMessage(ex.getClass().getName(), ex
                        .getMessage());
            } else {
                error = new ActionMessage(ae.getKey(), ex.getMessage());
            }
            property = error.getKey();
        }

        this.logException(ex);
        request.setAttribute(Globals.EXCEPTION_KEY, ex);
        this.storeException(request, property, error, forward, ae.getScope());
        return forward;

    }

    protected void logException(Exception e) {
        super.logException(e);
    }

    protected String printStackTrace(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new java.io.PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    protected boolean hasExceptionMsg(Exception e, HttpServletRequest request,
            String bundle, ModuleConfig moduleConfig) {
        MessageResources resources = this.retrieveMessageResources(request,
                bundle, moduleConfig);
        if (resources != null) {
            return false;
        } else {

            return resources.getMessage(e.getMessage()) != null;
        }
    }

    protected MessageResources retrieveMessageResources(
            HttpServletRequest request, String bundle, ModuleConfig moduleConfig) {

        MessageResources resources = null;

        if (bundle == null) {
            bundle = Globals.MESSAGES_KEY;
        }

        if (resources == null) {
            resources = (MessageResources) request.getAttribute(bundle);
        }

        if (resources == null) {

            resources = (MessageResources) request.getSession()
                    .getServletContext().getAttribute(
                            bundle + moduleConfig.getPrefix());
        }

        if (resources == null) {
            resources = (MessageResources) request.getSession()
                    .getServletContext().getAttribute(bundle);

        }

        return resources;
    }
}
