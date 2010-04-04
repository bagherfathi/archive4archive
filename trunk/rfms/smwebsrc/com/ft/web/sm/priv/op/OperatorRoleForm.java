package com.ft.web.sm.priv.op;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.RoleDTO;

/**
 * 操作员的角色页面数据类
 * 
 * @version 1.0
 * @struts.form name="operatorRoleForm"
 */
public class OperatorRoleForm extends OperatorBaseForm {

    private static final long serialVersionUID = 1L;

    private RoleDTO[] roles;

    private int roleType;

    private OrganizationDTO org;

    /**
     * @struts.entity-field initial="roleIds"
     * @return
     */
    public RoleDTO[] getRoles() {
        return roles;
    }

    public void setRoles(RoleDTO[] roles) {
        this.roles = roles;
    }

    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
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

    /* (non-Javadoc)
     * @see com.ft.web.sm.BaseValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        if(org == null){
            org = new OrganizationDTO();
        }
    }
    
    

}
