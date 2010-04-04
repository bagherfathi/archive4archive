/**
 * @{#} DataResource.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_DATA_RESOURCE table.
 * Do not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_DATA_RESOURCE"
 */

public class DataResource implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "DataResource";

    public static String PROP_DATA_TYPE = "dataType";

    public static String PROP_ORG_ID = "orgId";

    public static String PROP_PRIV_CODE = "privCode";

    public static String PROP_PRIV_NAME = "privName";

    public static String PROP_CREATE_DATE = "createDate";

    public static String PROP_VALID_DATE = "validDate";

    public static String PROP_RESOURCE_DESC = "resourceDesc";

    public static String PROP_UPDATE_DATE = "updateDate";

    public static String PROP_OPERATOR_ID = "operatorId";

    public static String PROP_APP_ID = "appId";

    public static String PROP_ASSIGN_TYPE = "assignType";

    public static String PROP_EXPIRE_DATE = "expireDate";

    public static String PROP_LOGIN_ORG_ID = "loginOrgId";

    public static String PROP_RESOURCE_ID = "resourceId";

    public DataResource() {

    }

    // primary key
    private long resourceId;

    // fields
    private java.lang.String privName;

    private java.lang.String privCode;

    private java.lang.String resourceDesc;

    private long dataType;

    private long assignType;

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
     * @hibernate.id generator-class="sequence" column="RESOURCE_ID"
     */
    public long getResourceId() {
        return resourceId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param resourceId
     *                the new ID
     */
    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * Return the value associated with the column: PRIV_NAME
     */
    public java.lang.String getPrivName() {
        return privName;
    }

    /**
     * Set the value related to the column: PRIV_NAME
     * 
     * @param privName
     *                the PRIV_NAME value
     */
    public void setPrivName(java.lang.String privName) {
        this.privName = privName;
    }

    /**
     * Return the value associated with the column: PRIV_CODE
     */
    public java.lang.String getPrivCode() {
        return privCode;
    }

    /**
     * Set the value related to the column: PRIV_CODE
     * 
     * @param privCode
     *                the PRIV_CODE value
     */
    public void setPrivCode(java.lang.String privCode) {
        this.privCode = privCode;
    }

    /**
     * Return the value associated with the column: RESOURCE_DESC
     */
    public java.lang.String getResourceDesc() {
        return resourceDesc;
    }

    /**
     * Set the value related to the column: RESOURCE_DESC
     * 
     * @param resourceDesc
     *                the RESOURCE_DESC value
     */
    public void setResourceDesc(java.lang.String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    /**
     * Return the value associated with the column: DATA_TYPE
     */
    public long getDataType() {
        return dataType;
    }

    /**
     * Set the value related to the column: DATA_TYPE
     * 
     * @param dataType
     *                the DATA_TYPE value
     */
    public void setDataType(long dataType) {
        this.dataType = dataType;
    }

    /**
     * Return the value associated with the column: ASSIGN_TYPE
     */
    public long getAssignType() {
        return assignType;
    }

    /**
     * Set the value related to the column: ASSIGN_TYPE
     * 
     * @param assignType
     *                the ASSIGN_TYPE value
     */
    public void setAssignType(long assignType) {
        this.assignType = assignType;
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