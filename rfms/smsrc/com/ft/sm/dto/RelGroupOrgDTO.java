package com.ft.sm.dto;

import com.ft.sm.entity.Group;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelGroupOrg;

/**
 * ����Ա��ɷ�����֯��ϵ��װ�ࡣ
 * 
 * @version 1.0
 */
public class RelGroupOrgDTO implements java.io.Serializable {
    private static final long serialVersionUID = -6677969802406643727L;

    private Group group;

    private Organization org;

    private RelGroupOrg relGroupOrg;

    public RelGroupOrgDTO(Group group, Organization org) {
        this.group = group;
        this.org = org;
        this.relGroupOrg = new RelGroupOrg();
        this.relGroupOrg.setGroupId(group.getGroupId());
        this.relGroupOrg.setOrgId(org.getOrgId());
    }

    /**
     * ����Ա�顣
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
     * �ɷ�����֯������
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
     * @return the relGroupOrg
     */
    public RelGroupOrg getRelGroupOrg() {
        return relGroupOrg;
    }
}
