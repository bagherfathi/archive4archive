/**
 * @{#} Resource.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_RESOURCE table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * 
 * @hibernate.class table="SM_RESOURCE"
 */

public class Resource implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "Resource";

    public static String PROP_MENU_ORDER = "menuOrder";

    public static String PROP_ORG_ID = "orgId";

    public static String PROP_MENU_VISIBLE = "menuVisible";

    public static String PROP_CREATE_DATE = "createDate";

    public static String PROP_VALID_DATE = "validDate";

    public static String PROP_PARENT_ID = "parentId";

    public static String PROP_RESOURCE_TYPE = "resourceType";

    public static String PROP_OPERATOR_ID = "operatorId";

    public static String PROP_UPDATE_DATE = "updateDate";

    public static String PROP_APP_ID = "appId";

    public static String PROP_MENU_URL = "menuUrl";

    public static String PROP_RESOURCE_PATH = "resourcePath";

    public static String PROP_EXPIRE_DATE = "expireDate";

    public static String PROP_LOGIN_ORG_ID = "loginOrgId";

    public static String PROP_RESOURCE_TITLE = "resourceTitle";

    public static String PROP_RESOURCE_ID = "resourceId";

    public static String PROP_MENU_IMAGE = "menuImage";

    public static String PROP_RESOURCE_CODE = "resourceCode";

    public Resource() {

    }

    // primary key
    private long resourceId;

    // fields
    private long parentId;

    private java.lang.String resourceTitle;

    private long resourceType;

    private java.lang.String resourceCode;

    private java.lang.String resourcePath;

    private java.lang.String menuUrl;

    private java.lang.String menuImage;

    private long menuOrder;

    private long menuVisible;

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
     * Return the value associated with the column: PARENT_ID
     */
    public long getParentId() {
        return parentId;
    }

    /**
     * Set the value related to the column: PARENT_ID
     * 
     * @param parentId
     *                the PARENT_ID value
     */
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    /**
     * Return the value associated with the column: RESOURCE_TITLE
     */
    public java.lang.String getResourceTitle() {
        return resourceTitle;
    }

    /**
     * Set the value related to the column: RESOURCE_TITLE
     * 
     * @param resourceTitle
     *                the RESOURCE_TITLE value
     */
    public void setResourceTitle(java.lang.String resourceTitle) {
        this.resourceTitle = resourceTitle;
    }

    /**
     * Return the value associated with the column: RESOURCE_TYPE
     */
    public long getResourceType() {
        return resourceType;
    }

    /**
     * Set the value related to the column: RESOURCE_TYPE
     * 
     * @param resourceType
     *                the RESOURCE_TYPE value
     */
    public void setResourceType(long resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * Return the value associated with the column: RESOURCE_CODE
     */
    public java.lang.String getResourceCode() {
        return resourceCode;
    }

    /**
     * Set the value related to the column: RESOURCE_CODE
     * 
     * @param resourceCode
     *                the RESOURCE_CODE value
     */
    public void setResourceCode(java.lang.String resourceCode) {
        this.resourceCode = resourceCode;
    }

    /**
     * Return the value associated with the column: RESOURCE_PATH
     */
    public java.lang.String getResourcePath() {
        return resourcePath;
    }

    /**
     * Set the value related to the column: RESOURCE_PATH
     * 
     * @param resourcePath
     *                the RESOURCE_PATH value
     */
    public void setResourcePath(java.lang.String resourcePath) {
        this.resourcePath = resourcePath;
    }

    /**
     * Return the value associated with the column: MENU_URL
     */
    public java.lang.String getMenuUrl() {
        return menuUrl;
    }

    /**
     * Set the value related to the column: MENU_URL
     * 
     * @param menuUrl
     *                the MENU_URL value
     */
    public void setMenuUrl(java.lang.String menuUrl) {
        this.menuUrl = menuUrl;
    }

    /**
     * Return the value associated with the column: MENU_IMAGE
     */
    public java.lang.String getMenuImage() {
        return menuImage;
    }

    /**
     * Set the value related to the column: MENU_IMAGE
     * 
     * @param menuImage
     *                the MENU_IMAGE value
     */
    public void setMenuImage(java.lang.String menuImage) {
        this.menuImage = menuImage;
    }

    /**
     * Return the value associated with the column: MENU_ORDER
     */
    public long getMenuOrder() {
        return menuOrder;
    }

    /**
     * Set the value related to the column: MENU_ORDER
     * 
     * @param menuOrder
     *                the MENU_ORDER value
     */
    public void setMenuOrder(long menuOrder) {
        this.menuOrder = menuOrder;
    }

    /**
     * Return the value associated with the column: MENU_VISIBLE
     */
    public long getMenuVisible() {
        return menuVisible;
    }

    /**
     * Set the value related to the column: MENU_VISIBLE
     * 
     * @param menuVisible
     *                the MENU_VISIBLE value
     */
    public void setMenuVisible(long menuVisible) {
        this.menuVisible = menuVisible;
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