 
package com.ft.common.exception;

import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;

/**
 * 对于系统web页面抛出的RuntimeException处理类。
 * 
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
 * @version 1.0
 */
public class CommonExceptionHandler extends ExceptionHandler {
    public final Log logger = LogFactory.getLog(CommonExceptionHandler.class);

    public static final String DEFAUL_ERROR_CODE = "default.error";

    public static final String ATTR_ERROR_CODE = "errorCode";

    public static final String ATTR_EX_TRACE = "exceptionStack";

    public static final String ATTR_ERROR_PARAMS = "exceptionParams";


    public ActionForward execute(Exception ex, ExceptionConfig ae,
            ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        if (ex instanceof CommonRuntimeException) {
            CommonRuntimeException exception = (CommonRuntimeException) ex;

            if (StringUtils.isNotEmpty(exception.getErrorCode())) {
                request.setAttribute(ATTR_ERROR_CODE, exception.getErrorCode());
            } else {
                request.setAttribute(ATTR_ERROR_CODE, DEFAUL_ERROR_CODE);
            }

            request.setAttribute(ATTR_ERROR_PARAMS, exception.getParams());
        } else {
            request.setAttribute(ATTR_ERROR_CODE, DEFAUL_ERROR_CODE);
        }

        request.setAttribute(ATTR_EX_TRACE, this.getExceptionStackTrace(ex));

        ActionForward forward = null;
        if (null != ae.getPath()) {
            forward = new ActionForward(ae.getPath());
        } else {
            forward = mapping.getInputForward();
        }

        return forward;
    }

    /**
     * 获取异常的StackTrace信息。
     * 
     * @param ex
     * @return
     */
    protected String getExceptionStackTrace(Exception ex) {
        StringWriter writer = new StringWriter();
        ex.printStackTrace(new java.io.PrintWriter(writer));
        return writer.toString();
    }

    /**
     * 获取消息资源。
     * 
     * @param request
     * @param bundle
     * @param moduleConfig
     * @return
     */
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
