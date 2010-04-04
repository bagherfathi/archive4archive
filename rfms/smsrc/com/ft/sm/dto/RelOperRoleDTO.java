package com.ft.sm.dto;

import com.ft.sm.entity.Operator;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelOperRole;
import com.ft.sm.entity.Role;

/**
 * ����Ա��ɫ��ϵ��װ�ࡣ
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
     * ��Ӧ����Ա��
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
     * ��Ӧ��֯��
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
     * ��Ӧ��ɫ��
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
