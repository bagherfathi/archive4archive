package com.ft.web.sm.priv.op;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ft.busi.sm.model.OperatorManager;
import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.struts.ActionMessagesHelper;
import com.ft.web.sm.BaseAction;

/**
 * @version 1.0
 * @spring.bean id="opSelfAction"
 * 
 * 
 * @struts.action path="/opSelf" name="opSelfForm" scope="request"
 *                validate="false" parameter="act" input="sm.op.self"
 * 
 * 
 * @struts.tiles name="sm.op.self" extends="main.layout"
 * @struts.tiles-put name="body" value="/sm/op/opSelf.jsp"
 */
public class OpSelfAction extends BaseAction {

    private OperatorManager opManager;
   // protected SyncProxy syncProxy;

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.DispatchAction#unspecified(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.getInputForward();
    }

    /**
     * 保存修改的信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        OpSelfForm opSelfForm = (OpSelfForm) form;
        OperatorDTO op = opSelfForm.getOpDTO();
        
        if (StringUtils.isNotEmpty(opSelfForm.getOldPassword())) {
            
            boolean result = opManager.changePassword(op, opSelfForm
                    .getOldPassword(), opSelfForm.getNewPassword());
            if (!result) {
                ActionMessagesHelper.saveRequestMessage(request,
                        "errors.op.self.wrong.password");
                return mapping.getInputForward();
            }
        }
        //add by yyh for over befor update password
        OperatorDTO tempOp=opManager.findOperatorById(op.getOperatorId());
        OperatorSessionHelper.getLoginOp(request).setPassword(tempOp.getPassword());
        tempOp.getContact().setMobilePhone(op.getContact().getMobilePhone());
        tempOp.getContact().setName(op.getContact().getName());
        tempOp.getContact().setTelephone(op.getContact().getTelephone());
        tempOp.getContact().setAddress(op.getContact().getAddress());
        tempOp.getContact().setPostCode(op.getContact().getPostCode());
        tempOp.setEmail(op.getEmail());
        tempOp.setMsn(op.getMsn());
        tempOp.setMemo(op.getMemo());
        tempOp.setOrg(op.getOrg());
        
        // 和SSO同步
        //this.syncProxy.updateOperator(tempOp,new OrganizationDTO(tempOp.getOrg()));
        this.opManager.saveOrUpdateOperator(tempOp,new OrganizationDTO(op.getOrg()));
            
        //end by yyh.
        return mapping.getInputForward();

    }
    
    /**
     * @spring.property ref="operatorManager"
     * @param opManager
     *            the opManager to set
     */
    public void setOpManager(OperatorManager opManager) {
        this.opManager = opManager;
    }
    
    /**
     * @spring.property ref="syncProxy"
     * @param syncProxy
     *                The syncProxy to set.
     */
    //public void setSyncProxy(SyncProxy syncProxy) {
        //this.syncProxy = syncProxy;
    //}
}
