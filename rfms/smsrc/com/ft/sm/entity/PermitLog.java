/**
 * @{#} PermitLog.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_PERMIT_LOG table. Do
 * not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_PERMIT_LOG"
 */

public class PermitLog implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "PermitLog";

    public static String PROP_GRANTOR_ORG_ID = "grantorOrgId";

    public static String PROP_LOG_TIME = "logTime";

    public static String PROP_GROUP_ID = "groupId";

    public static String PROP_LOG_ID = "logId";

    public static String PROP_ORG_ID = "orgId";

    public static String PROP_GRANTEE_ID = "granteeId";

    public static String PROP_PERMIT_ID = "permitId";

    public static String PROP_GRANTEE_TYPE = "granteeType";

    public static String PROP_GRANTOR_ID = "grantorId";

    public static String PROP_PERMIT_TYPE = "permitType";

    public static String PROP_ROLE_ID = "roleId";

    public static String PROP_OP_TYPE = "opType";

    public PermitLog() {

    }

    // primary key
    private long logId;

    // fields
    private java.util.Date logTime;

    private long grantorId;

    private long grantorOrgId;

    private long granteeId;

    private long granteeType;

    private long opType;

    private long roleId;

    private long groupId;

    private long orgId;

    private long permitId;

    private long permitType;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="LOG_ID"
     */
    public long getLogId() {
        return logId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param logId
     *                the new ID
     */
    public void setLogId(long logId) {
        this.logId = logId;
    }

    /**
     * Return the value associated with the column: LOG_TIME
     */
    public java.util.Date getLogTime() {
        return logTime;
    }

    /**
     * Set the value related to the column: LOG_TIME
     * 
     * @param logTime
     *                the LOG_TIME value
     */
    public void setLogTime(java.util.Date logTime) {
        this.logTime = logTime;
    }

    /**
     * Return the value associated with the column: GRANTOR_ID
     */
    public long getGrantorId() {
        return grantorId;
    }

    /**
     * Set the value related to the column: GRANTOR_ID
     * 
     * @param grantorId
     *                the GRANTOR_ID value
     */
    public void setGrantorId(long grantorId) {
        this.grantorId = grantorId;
    }

    /**
     * Return the value associated with the column: GRANTOR_ORG_ID
     */
    public long getGrantorOrgId() {
        return grantorOrgId;
    }

    /**
     * Set the value related to the column: GRANTOR_ORG_ID
     * 
     * @param grantorOrgId
     *                the GRANTOR_ORG_ID value
     */
    public void setGrantorOrgId(long grantorOrgId) {
        this.grantorOrgId = grantorOrgId;
    }

    /**
     * Return the value associated with the column: GRANTEE_ID
     */
    public long getGranteeId() {
        return granteeId;
    }

    /**
     * Set the value related to the column: GRANTEE_ID
     * 
     * @param granteeId
     *                the GRANTEE_ID value
     */
    public void setGranteeId(long granteeId) {
        this.granteeId = granteeId;
    }

    /**
     * Return the value associated with the column: GRANTEE_TYPE
     */
    public long getGranteeType() {
        return granteeType;
    }

    /**
     * Set the value related to the column: GRANTEE_TYPE
     * 
     * @param granteeType
     *                the GRANTEE_TYPE value
     */
    public void setGranteeType(long granteeType) {
        this.granteeType = granteeType;
    }

    /**
     * Return the value associated with the column: OP_TYPE
     */
    public long getOpType() {
        return opType;
    }

    /**
     * Set the value related to the column: OP_TYPE
     * 
     * @param opType
     *                the OP_TYPE value
     */
    public void setOpType(long opType) {
        this.opType = opType;
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

    /**
     * Return the value associated with the column: PERMIT_ID
     */
    public long getPermitId() {
        return permitId;
    }

    /**
     * Set the value related to the column: PERMIT_ID
     * 
     * @param permitId
     *                the PERMIT_ID value
     */
    public void setPermitId(long permitId) {
        this.permitId = permitId;
    }

    /**
     * Return the value associated with the column: PERMIT_TYPE
     */
    public long getPermitType() {
        return permitType;
    }

    /**
     * Set the value related to the column: PERMIT_TYPE
     * 
     * @param permitType
     *                the PERMIT_TYPE value
     */
    public void setPermitType(long permitType) {
        this.permitType = permitType;
    }

    public String toString() {
        return super.toString();
    }

}