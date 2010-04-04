package com.ft.web.sm.priv.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ft.busi.sm.model.OperatorManager;
import com.ft.web.sm.BaseAction;

/**
 * 功能权限查询页面操作逻辑类。
 * 
 * @spring.bean id="dataResourceQuenryAction"
 * 
 * @struts.action path="/dataResourceQuery" name="resQueryForm"
 *                scope="request" input="sm.dataresource.query.index"
 *                parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.dataresource.query.index" extends="main.layout"
 * @struts.tiles-put name="body"
 *                   value="/sm/privilege/dataResourceQuery.jsp"
 * @version 1.0
 */
public class DataResourceQueryAction extends BaseAction{
    private OperatorManager opManager;
    
    /**
     * @spring.property ref="operatorManager"
     * @param opManager the opManager to set
     */
    public void setOpManager(OperatorManager opManager) {
        this.opManager = opManager;
    }

    /* (non-Javadoc)
     * @see org.apache.struts.actions.DispatchAction#unspecified(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }
    
    public ActionForward search(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QueryForm queryForm = (QueryForm)form;
//        Long resourceId = queryForm.getResourceId();
//        List operatorList = this.opManager.findOperatorOfDataResourceEntry(resourceId);
        List operatorList = this.opManager.findOperatorOfDataResourceEntry(queryForm.getResourceIds());
        request.setAttribute("opList", operatorList);
        return mapping.getInputForward();
    }
}
