package com.ft.web.sm.priv.op;

import com.ft.sm.dto.OperatorDTO;
import com.ft.sm.dto.OrganizationDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 设置操作员功能权限页面Form Bean。
 * 
 * @version 1.0
 * 
 * @struts.form name="opResourceForm"
 */
public class OperatorResourceForm extends BaseValidatorForm{
    private static final long serialVersionUID = -3291969426012351018L;
    
    private OperatorDTO op;
    private OrganizationDTO org;
    
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

    /**
     * @return the orgId_s
     */
    public Long getOrgId_s() {
        return orgId_s;
    }

    /**
     * @param orgId_s the orgId_s to set
     */
    public void setOrgId_s(Long orgId_s) {
        this.orgId_s = orgId_s;
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
     * @struts.entity-field initial="orgId"
     * @return Returns the org.
     */
    public OrganizationDTO getOrg() {
        return org;
    }

    /**
     * @param org
     *                The org to set.
     */
    public void setOrg(OrganizationDTO org) {
        this.org = org;
    }
}
