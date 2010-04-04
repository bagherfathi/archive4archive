package com.ft.web.sm.priv.group;

import com.ft.sm.dto.OrganizationDTO;
import com.ft.sm.dto.RoleDTO;

/**
 * 组的功能角色
 * 
 * @version 1.0
 * 
 * @struts.form name="groupRoleForm"
 * 
 */
public class GroupRoleForm extends GroupBaseForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RoleDTO[] roles;

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
