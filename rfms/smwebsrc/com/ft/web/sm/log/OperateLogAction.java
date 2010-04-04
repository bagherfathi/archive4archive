package com.ft.web.sm.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * 操作员日志页面控制类
 * 
 * @version 1.0
 * 
 * @struts.action path="/operateLog" name="operateLogForm" scope="request"
 *                validate="false" parameter="act" input="sm.operator.log.index"
 * 
 * @struts.tiles name="sm.operator.log.index" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/log/listOperateLog.jsp"
 */
public class OperateLogAction extends DispatchAction {

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

}
