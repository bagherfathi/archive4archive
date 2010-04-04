package com.ft.web.sm.priv.op;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.OperatorDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 操作员基础维护类
 * 
 * @version 1.0
 */
public class OperatorBaseForm extends BaseValidatorForm {

	private static final long serialVersionUID = 1L;

	private OperatorDTO op;
    
    private OperatorDTO[] ops;
    
    private Long orgId_s;
    
    private String loginName;
    
    private String name;
    
    private String listOp_p;
    
    /**
     * @return the listOp_p
     */
    public String getListOp_p() {
        return listOp_p;
    }

    /**
     * @param listOp_p the listOp_p to set
     */
    public void setListOp_p(String listOp_p) {
        this.listOp_p = listOp_p;
    }

    /**
     * @return the loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName the loginName to set
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public Long getOrgId_s() {
        return orgId_s;
    }

    /**
     * @param orgId the orgId to set
     */
    public void setOrgId_s(Long orgId) {
        this.orgId_s = orgId;
    }

    /**
     * @struts.entity-field initial="opId"
     * @return
     */
    public OperatorDTO getOp() {
        return op;
    }

    public void setOp(OperatorDTO op) {
        this.op = op;
    }
    
    /**
     * @struts.entity-field initial="opIds"
     * @return
     */
    public OperatorDTO[] getOps() {
        return ops;
    }

    public void setOps(OperatorDTO[] ops) {
        this.ops = ops;
    }
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        if(op == null){
            op = new OperatorDTO();
        }
    }

}
