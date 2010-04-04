package com.ft.sm.dto;

import com.ft.sm.entity.Operator;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelOperRole;
import com.ft.sm.entity.Role;

/**
 * 操作员角色关系封装类。
 * 
 */
public class RelOperRoleDTO implements java.io.Serializable {
    private static final long serialVersionUID = -9119708510518828352L;

    private Role role;

    private Organization org;

    private Operator operator;

    private RelOperRole relOperRole;

    public RelOperRoleDTO(Role role, Organization org, Operator operator) {
        this.role = role;
        this.org = org;
        this.operator = operator;

        relOperRole = new RelOperRole();
        relOperRole.setOperatorId(operator.getOperatorId());
        relOperRole.setOrgId(org.getOrgId());
        relOperRole.setRoleId(role.getRoleId());

    }

    /**
     * 对应操作员。
     * 
     * @return
     */
    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * 对应组织。
     * 
     * @return
     */
    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    /**
     * 对应角色。
     * 
     * @return
     */
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * @return the relOperRole
     */
    public RelOperRole getRelOperRole() {
        return relOperRole;
    }
}
