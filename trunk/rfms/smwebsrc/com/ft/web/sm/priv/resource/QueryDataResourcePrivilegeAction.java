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
 * ҵ��Ȩ�޲�ѯ
 * 
 * @spring.bean id="queryDataResourcePrivilegeAction"
 * 
 * @struts.action path="/queryDRPrivilege" name="queryDataResourcePrivilegeForm"
 *                scope="request" input="sm.query.data.resource.privilege"
 *                parameter="act" validate="false"
 * 
 * @struts.tiles name="sm.query.data.resource.privilege" extends="main.layout"
 * @struts.tiles-put name="body"
 *                   value="/sm/privilege/queryDataResourcePrivilege.jsp"
 * 
 * @struts.action-forward name="dataResourcePrivilegeList"
 *                        path="sm.query.data.resource.privilege.list"
 * 
 * @version 1.0
 */
public class QueryDataResourcePrivilegeAction extends BaseAction {

    /**
     * ����Ա������
     */
    private OperatorManager operatorManager;

    /**
     * @spring.property ref="operatorManager"
     */
    public void setOperatorManager(OperatorManager operatorManager) {
        this.operatorManager = operatorManager;
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * ��ѯҵ��Ȩ�޷������
     * 
     * @struts.tiles name="sm.query.data.resource.privilege.list"
     *               extends="main.layout"
     * @struts.tiles-put name="body"
     *                   value="/sm/privilege/listDataResourcePrivilege.jsp"
     * 
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward queryDataResourcePrivilege(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        QueryDataResourcePrivilegeForm queryForm = (QueryDataResourcePrivilegeForm) form;
        // Long id = queryForm.getDataResource().getResourceId();
        Long entryId = queryForm.getDataResourceEntry().getEntryId();
        // ���ҵ��Ȩ�޷�����Ĳ���Ա
        List opList = operatorManager.findOperatorOfDataResourceEntry(entryId);
        request.setAttribute("operators", opList);
        return mapping.findForward("dataResourcePrivilegeList");
    }

    /**
     * ��ѯ
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward query(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.getInputForward();
    }
}
