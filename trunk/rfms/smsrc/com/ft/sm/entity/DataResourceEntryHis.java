/**
 * @{#} DataResourceEntryHis.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the
 * SM_DATA_RESOURCE_ENTRY_HIS table. Do not modify this class because it will be
 * overwritten if the configuration file related to this class is modified.
 * 
 * @hibernate.class table="SM_DATA_RESOURCE_ENTRY_HIS"
 */

public class DataResourceEntryHis implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "DataResourceEntryHis";

    public static String PROP_NEW_APP_ID = "newAppId";

    public static String PROP_MAX_VALUE = "maxValue";

    public static String PROP_ORG_ID = "orgId";

    public static String PROP_MIN_VALUE = "minValue";

    public static String PROP_ENTRY_ID = "entryId";

    public static String PROP_CREATE_DATE = "createDate";

    public static String PROP_HISTORY_ID = "historyId";

    public static String PROP_VALID_DATE = "validDate";

    public static String PROP_UPDATE_DATE = "updateDate";

    public static String PROP_OPERATOR_ID = "operatorId";

    public static String PROP_APP_ID = "appId";

    public static String PROP_ENTRY_NOTES = "entryNotes";

    public static String PROP_ENTRY_NAME = "entryName";

    public static String PROP_EXPIRE_DATE = "expireDate";

    public static String PROP_LOGIN_ORG_ID = "loginOrgId";

    public static String PROP_RESOURCE_ID = "resourceId";

    public DataResourceEntryHis() {

    }

    // primary key
    private long historyId;

    // fields
    private long entryId;

    private long resourceId;

    private java.lang.String entryName;

    private java.lang.String entryNotes;

    private java.lang.String maxValue;

    private java.lang.String minValue;

    private long appId;

    private java.util.Date createDate;

    private java.util.Date validDate;

    private java.util.Date expireDate;

    private java.util.Date updateDate;

    private long operatorId;

    private long orgId;

    private long loginOrgId;

    private long newAppId;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="HISTORY_ID"
     */
    public long getHistoryId() {
        return historyId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param historyId
     *                the new ID
     */
    public void setHistoryId(long historyId) {
        this.historyId = historyId;
    }

    /**
     * Return the value associated with the column: ENTRY_ID
     */
    public long getEntryId() {
        return entryId;
    }

    /**
     * Set the value related to the column: ENTRY_ID
     * 
     * @param entryId
     *                the ENTRY_ID value
     */
    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }

    /**
     * Return the value associated with the column: RESOURCE_ID
     */
    public long getResourceId() {
        return resourceId;
    }

    /**
     * Set the value related to the column: RESOURCE_ID
     * 
     * @param resourceId
     *                the RESOURCE_ID value
     */
    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * Return the value associated with the column: ENTRY_NAME
     */
    public java.lang.String getEntryName() {
        return entryName;
    }

    /**
     * Set the value related to the column: ENTRY_NAME
     * 
     * @param entryName
     *                the ENTRY_NAME value
     */
    public void setEntryName(java.lang.String entryName) {
        this.entryName = entryName;
    }

    /**
     * Return the value associated with the column: ENTRY_NOTES
     */
    public java.lang.String getEntryNotes() {
        return entryNotes;
    }

    /**
     * Set the value related to the column: ENTRY_NOTES
     * 
     * @param entryNotes
     *                the ENTRY_NOTES value
     */
    public void setEntryNotes(java.lang.String entryNotes) {
        this.entryNotes = entryNotes;
    }

    /**
     * Return the value associated with the column: MAX_VALUE
     */
    public java.lang.String getMaxValue() {
        return maxValue;
    }

    /**
     * Set the value related to the column: MAX_VALUE
     * 
     * @param maxValue
     *                the MAX_VALUE value
     */
    public void setMaxValue(java.lang.String maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Return the value associated with the column: MIN_VALUE
     */
    public java.lang.String getMinValue() {
        return minValue;
    }

    /**
     * Set the value related to the column: MIN_VALUE
     * 
     * @param minValue
     *                the MIN_VALUE value
     */
    public void setMinValue(java.lang.String minValue) {
        this.minValue = minValue;
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

    /**
     * Return the value associated with the column: NEW_APP_ID
     */
    public long getNewAppId() {
        return newAppId;
    }

    /**
     * Set the value related to the column: NEW_APP_ID
     * 
     * @param newAppId
     *                the NEW_APP_ID value
     */
    public void setNewAppId(long newAppId) {
        this.newAppId = newAppId;
    }

    public String toString() {
        return super.toString();
    }

}