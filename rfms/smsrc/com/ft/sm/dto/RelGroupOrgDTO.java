package com.ft.sm.dto;

import com.ft.sm.entity.Group;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelGroupOrg;

/**
 * 操作员组可访问组织关系封装类。
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
     * 操作员组。
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
     * 可访问组织机构。
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
