package com.ft.web.sm.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 性能日志页面控制类
 * 
 * @version 1.0
 * 
 * @struts.action path="/performanceLog" name="operateLogForm" scope="request"
 *                validate="false" input="sm.performance.log.index"
 * 
 * @struts.tiles name="sm.performance.log.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/log/listPerformanceLog.jsp"
 */

public class PerformanceLogAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }
}
