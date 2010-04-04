package com.ft.sm.dto;

import com.ft.sm.entity.Group;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelGroupRole;
import com.ft.sm.entity.Role;

/**
 * ����Ա���ɫ��ϵ��װ�ࡣ
 * 
 * @version 1.0
 */
public class RelGroupRoleDTO implements java.io.Serializable {
    private static final long serialVersionUID = -1443606495494469118L;

    private Group group;

    private Role role;

    private Organization org;

    private RelGroupRole relGroupRole;

    public RelGroupRoleDTO(Group group, Role role, Organization org) {
        this.group = group;
        this.role = role;
        this.org = org;

        relGroupRole = new RelGroupRole();
        relGroupRole.setGroupId(group.getGroupId());
        relGroupRole.setOrgId(org.getOrgId());
        relGroupRole.setRoleId(role.getRoleId());
    }

    /**
     * ��Ӧ����Ա�顣
     * 
     * @return
     */
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
     * @return the relGroupRole
     */
    public RelGroupRole getRelGroupRole() {
        return relGroupRole;
    }
}
