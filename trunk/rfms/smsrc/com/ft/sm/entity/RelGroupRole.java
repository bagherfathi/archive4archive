/**
 * @{#} RelGroupRole.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_REL_GROUP_ROLE table.
 * Do not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_REL_GROUP_ROLE"
 */

public class RelGroupRole implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "RelGroupRole";

    public static String PROP_GROUP_ID = "groupId";

    public static String PROP_ORG_ID = "orgId";

    public static String PROP_REL_ID = "relId";

    public static String PROP_ROLE_ID = "roleId";

    public RelGroupRole() {

    }

    // primary key
    private long relId;

    // fields
    private long roleId;

    private long groupId;

    private long orgId;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="REL_ID"
     */
    public long getRelId() {
        return relId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param relId
     *                the new ID
     */
    public void setRelId(long relId) {
        this.relId = relId;
    }

    /**
     * Return the value associated with the column: ROLE_ID
     */
    public long getRoleId() {
        return roleId;
    }

    /**
     * Set the value related to the column: ROLE_ID
     * 
     * @param roleId
     *                the ROLE_ID value
     */
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    /**
     * Return the value associated with the column: GROUP_ID
     */
    public long getGroupId() {
        return groupId;
    }

    /**
     * Set the value related to the column: GROUP_ID
     * 
     * @param groupId
     *                the GROUP_ID value
     */
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    /**
     * Return the value associated with the column: ORG_ID
     */
    public long getOrgId() {
        return orgId;
    }

    /**
     * Set the value related to the column: ORG_ID
     * 
     * @param orgId
     *                the ORG_ID value
     */
    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String toString() {
        return super.toString();
    }

}