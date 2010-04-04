/**
 * @{#} Role.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_ROLE table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * 
 * @hibernate.class table="SM_ROLE"
 */

public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "Role";

    public static String PROP_VALID_DATE = "validDate";

    public static String PROP_ROLE_DESC = "roleDesc";

    public static String PROP_OPERATOR_ID = "operatorId";

    public static String PROP_UPDATE_DATE = "updateDate";

    public static String PROP_ORG_ID = "orgId";

    public static String PROP_APP_ID = "appId";

    public static String PROP_ROLE_TYPE = "roleType";

    public static String PROP_EXPIRE_DATE = "expireDate";

    public static String PROP_LOGIN_ORG_ID = "loginOrgId";

    public static String PROP_ROLE_NAME = "roleName";

    public static String PROP_CREATE_DATE = "createDate";

    public static String PROP_ROLE_ID = "roleId";

    public Role() {

    }

    // primary key
    private long roleId;

    // fields
    private java.lang.String roleName;

    private java.lang.String roleDesc;

    private long roleType;

    private long appId;

    private java.util.Date createDate;

    private java.util.Date validDate;

    private java.util.Date expireDate;

    private java.util.Date updateDate;

    private long operatorId;

    private long orgId;

    private long loginOrgId;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="ROLE_ID"
     */
    public long getRoleId() {
        return roleId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param roleId
     *                the new ID
     */
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    /**
     * Return the value associated with the column: ROLE_NAME
     */
    public java.lang.String getRoleName() {
        return roleName;
    }

    /**
     * Set the value related to the column: ROLE_NAME
     * 
     * @param roleName
     *                the ROLE_NAME value
     */
    public void setRoleName(java.lang.String roleName) {
        this.roleName = roleName;
    }

    /**
     * Return the value associated with the column: ROLE_DESC
     */
    public java.lang.String getRoleDesc() {
        return roleDesc;
    }

    /**
     * Set the value related to the column: ROLE_DESC
     * 
     * @param roleDesc
     *                the ROLE_DESC value
     */
    public void setRoleDesc(java.lang.String roleDesc) {
        this.roleDesc = roleDesc;
    }

    /**
     * Return the value associated with the column: ROLE_TYPE
     */
    public long getRoleType() {
        return roleType;
    }

    /**
     * Set the value related to the column: ROLE_TYPE
     * 
     * @param roleType
     *                the ROLE_TYPE value
     */
    public void setRoleType(long roleType) {
        this.roleType = roleType;
    }

    /**
     * Return the value associated with the column: APP_ID
     */
    public long getAppId() {
        return appId;
    }

    /**
     * Set the value related to the column: APP_ID
     * 
     * @param appId
     *                the APP_ID value
     */
    public void setAppId(long appId) {
        this.appId = appId;
    }

    /**
     * Return the value associated with the column: CREATE_DATE
     */
    public java.util.Date getCreateDate() {
        return createDate;
    }

    /**
     * Set the value related to the column: CREATE_DATE
     * 
     * @param createDate
     *                the CREATE_DATE value
     */
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Return the value associated with the column: VALID_DATE
     */
    public java.util.Date getValidDate() {
        return validDate;
    }

    /**
     * Set the value related to the column: VALID_DATE
     * 
     * @param validDate
     *                the VALID_DATE value
     */
    public void setValidDate(java.util.Date validDate) {
        this.validDate = validDate;
    }

    /**
     * Return the value associated with the column: EXPIRE_DATE
     */
    public java.util.Date getExpireDate() {
        return expireDate;
    }

    /**
     * Set the value related to the column: EXPIRE_DATE
     * 
     * @param expireDate
     *                the EXPIRE_DATE value
     */
    public void setExpireDate(java.util.Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * Return the value associated with the column: UPDATE_DATE
     */
    public java.util.Date getUpdateDate() {
        return updateDate;
    }

    /**
     * Set the value related to the column: UPDATE_DATE
     * 
     * @param updateDate
     *                the UPDATE_DATE value
     */
    public void setUpdateDate(java.util.Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * Return the value associated with the column: OPERATOR_ID
     */
    public long getOperatorId() {
        return operatorId;
    }

    /**
     * Set the value related to the column: OPERATOR_ID
     * 
     * @param operatorId
     *                the OPERATOR_ID value
     */
    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
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
     * Return the value associated with the column: LOGIN_ORG_ID
     */
    public long getLoginOrgId() {
        return loginOrgId;
    }

    /**
     * Set the value related to the column: LOGIN_ORG_ID
     * 
     * @param loginOrgId
     *                the LOGIN_ORG_ID value
     */
    public void setLoginOrgId(long loginOrgId) {
        this.loginOrgId = loginOrgId;
    }

    public String toString() {
        return super.toString();
    }

}