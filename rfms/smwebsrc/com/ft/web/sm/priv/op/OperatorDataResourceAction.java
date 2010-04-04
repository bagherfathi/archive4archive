package com.ft.web.sm.priv.op;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ft.busi.sm.model.OperatorManager;
import com.ft.sm.dto.OperatorDTO;
import com.ft.web.sm.BaseAction;

/**
 * 设置操作员业务权限页面控制类。
 * 
 * @version 1.0
 * @spring.bean id="opDataResourceAction"
 * 
 * 
 * @struts.action path="/opDataResource" name="opDataResourceForm"
 *                scope="request" validate="false" parameter="act"
 *                input="sm.op.dataresource.config"
 * 
 * @struts.tiles name="sm.op.dataresource.config" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/op/opDataResourceConfig.jsp"
 */
public class OperatorDataResourceAction extends BaseAction {
    @SuppressWarnings("unused")
	private OperatorManager opManager;

    /**
     * 设置业务权限
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward configDataResource(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return mapping.getInputForward();
    }

    /**
     * 保存设置
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveConfig(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OperatorDataResourceForm opDataResForm = (OperatorDataResourceForm) form;
        OperatorDTO op = opDataResForm.getOp();

        return this
                .getRedirectForwardAction("op.do?act=view&selectedPane=dataRole&opId="
                        + op.getOperatorId());
    }

    /**
     * @spring.property ref="operatorManager"
     * @param opManager
     *                the opManager to set
     */
    public void setOpManager(OperatorManager opManager) {
        this.opManager = opManager;
    }

}
