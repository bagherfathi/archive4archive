/**
 * @{#} Organization.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_ORGANIZATION table. Do
 * not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_ORGANIZATION"
 */

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "Organization";

    public static String PROP_STATUS = "status";

    public static String PROP_TELE_PHONE = "telePhone";

    public static String PROP_ORG_PATH = "orgPath";

    public static String PROP_ORG_ID = "orgId";

    public static String PROP_LINK_NAME = "linkName";

    public static String PROP_MOBILE_PHONE = "mobilePhone";

    public static String PROP_CREATE_TIME = "createTime";

    public static String PROP_ORG_DESC = "orgDesc";

    public static String PROP_POSTCODE = "postcode";

    public static String PROP_ORG_TYPE = "orgType";

    public static String PROP_ORG_CODE = "orgCode";

    public static String PROP_REGION_ID = "regionId";

    public static String PROP_SSO_CODE = "ssoCode";

    public static String PROP_PARENT_ID = "parentId";

    public static String PROP_PARENT_SSO_CODE = "parentSsoCode";

    public static String PROP_SELF_BALANCE = "selfBalance";

    public static String PROP_ADDRESS = "address";

    public static String PROP_MANAGER_ID = "managerId";

    public static String PROP_ORG_NAME = "orgName";

    public static String PROP_MODIFY_TIME = "modifyTime";

    public Organization() {

    }

    // primary key
    private long orgId;

    // fields
    private long orgType;

    private java.lang.String orgName;

    private java.lang.String orgCode;

    private long parentId;

    private long regionId;

    private java.lang.String orgPath;

    private java.util.Date modifyTime;

    private java.util.Date createTime;

    private java.lang.String orgDesc;

    private long status;

    private java.lang.String linkName;

    private java.lang.String address;

    private java.lang.String mobilePhone;

    private java.lang.String telePhone;

    private java.lang.String postcode;

    private long managerId;

    private long selfBalance;

    private java.lang.String ssoCode;

    private java.lang.String parentSsoCode;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="ORG_ID"
     */
    public long getOrgId() {
        return orgId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param orgId
     *                the new ID
     */
    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    /**
     * Return the value associated with the column: ORG_TYPE
     */
    public long getOrgType() {
        return orgType;
    }

    /**
     * Set the value related to the column: ORG_TYPE
     * 
     * @param orgType
     *                the ORG_TYPE value
     */
    public void setOrgType(long orgType) {
        this.orgType = orgType;
    }

    /**
     * Return the value associated with the column: ORG_NAME
     */
    public java.lang.String getOrgName() {
        return orgName;
    }

    /**
     * Set the value related to the column: ORG_NAME
     * 
     * @param orgName
     *                the ORG_NAME value
     */
    public void setOrgName(java.lang.String orgName) {
        this.orgName = orgName;
    }

    /**
     * Return the value associated with the column: ORG_CODE
     */
    public java.lang.String getOrgCode() {
        return orgCode;
    }

    /**
     * Set the value related to the column: ORG_CODE
     * 
     * @param orgCode
     *                the ORG_CODE value
     */
    public void setOrgCode(java.lang.String orgCode) {
        this.orgCode = orgCode;
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
     * Return the value associated with the column: REGION_ID
     */
    public long getRegionId() {
        return regionId;
    }

    /**
     * Set the value related to the column: REGION_ID
     * 
     * @param regionId
     *                the REGION_ID value
     */
    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }

    /**
     * Return the value associated with the column: ORG_PATH
     */
    public java.lang.String getOrgPath() {
        return orgPath;
    }

    /**
     * Set the value related to the column: ORG_PATH
     * 
     * @param orgPath
     *                the ORG_PATH value
     */
    public void setOrgPath(java.lang.String orgPath) {
        this.orgPath = orgPath;
    }

    /**
     * Return the value associated with the column: MODIFY_TIME
     */
    public java.util.Date getModifyTime() {
        return modifyTime;
    }

    /**
     * Set the value related to the column: MODIFY_TIME
     * 
     * @param modifyTime
     *                the MODIFY_TIME value
     */
    public void setModifyTime(java.util.Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * Return the value associated with the column: CREATE_TIME
     */
    public java.util.Date getCreateTime() {
        return createTime;
    }

    /**
     * Set the value related to the column: CREATE_TIME
     * 
     * @param createTime
     *                the CREATE_TIME value
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * Return the value associated with the column: ORG_DESC
     */
    public java.lang.String getOrgDesc() {
        return orgDesc;
    }

    /**
     * Set the value related to the column: ORG_DESC
     * 
     * @param orgDesc
     *                the ORG_DESC value
     */
    public void setOrgDesc(java.lang.String orgDesc) {
        this.orgDesc = orgDesc;
    }

    /**
     * Return the value associated with the column: STATUS
     */
    public long getStatus() {
        return status;
    }

    /**
     * Set the value related to the column: STATUS
     * 
     * @param status
     *                the STATUS value
     */
    public void setStatus(long status) {
        this.status = status;
    }

    /**
     * Return the value associated with the column: LINK_NAME
     */
    public java.lang.String getLinkName() {
        return linkName;
    }

    /**
     * Set the value related to the column: LINK_NAME
     * 
     * @param linkName
     *                the LINK_NAME value
     */
    public void setLinkName(java.lang.String linkName) {
        this.linkName = linkName;
    }

    /**
     * Return the value associated with the column: ADDRESS
     */
    public java.lang.String getAddress() {
        return address;
    }

    /**
     * Set the value related to the column: ADDRESS
     * 
     * @param address
     *                the ADDRESS value
     */
    public void setAddress(java.lang.String address) {
        this.address = address;
    }

    /**
     * Return the value associated with the column: MOBILE_PHONE
     */
    public java.lang.String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * Set the value related to the column: MOBILE_PHONE
     * 
     * @param mobilePhone
     *                the MOBILE_PHONE value
     */
    public void setMobilePhone(java.lang.String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * Return the value associated with the column: TELE_PHONE
     */
    public java.lang.String getTelePhone() {
        return telePhone;
    }

    /**
     * Set the value related to the column: TELE_PHONE
     * 
     * @param telePhone
     *                the TELE_PHONE value
     */
    public void setTelePhone(java.lang.String telePhone) {
        this.telePhone = telePhone;
    }

    /**
     * Return the value associated with the column: POSTCODE
     */
    public java.lang.String getPostcode() {
        return postcode;
    }

    /**
     * Set the value related to the column: POSTCODE
     * 
     * @param postcode
     *                the POSTCODE value
     */
    public void setPostcode(java.lang.String postcode) {
        this.postcode = postcode;
    }

    /**
     * Return the value associated with the column: MANAGER_ID
     */
    public long getManagerId() {
        return managerId;
    }

    /**
     * Set the value related to the column: MANAGER_ID
     * 
     * @param managerId
     *                the MANAGER_ID value
     */
    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    /**
     * Return the value associated with the column: SELF_BALANCE
     */
    public long getSelfBalance() {
        return selfBalance;
    }

    /**
     * Set the value related to the column: SELF_BALANCE
     * 
     * @param selfBalance
     *                the SELF_BALANCE value
     */
    public void setSelfBalance(long selfBalance) {
        this.selfBalance = selfBalance;
    }

    /**
     * Return the value associated with the column: SSO_CODE
     */
    public java.lang.String getSsoCode() {
        return ssoCode;
    }

    /**
     * Set the value related to the column: SSO_CODE
     * 
     * @param ssoCode
     *                the SSO_CODE value
     */
    public void setSsoCode(java.lang.String ssoCode) {
        this.ssoCode = ssoCode;
    }

    /**
     * Return the value associated with the column: PARENT_SSO_CODE
     */
    public java.lang.String getParentSsoCode() {
        return parentSsoCode;
    }

    /**
     * Set the value related to the column: PARENT_SSO_CODE
     * 
     * @param parentSsoCode
     *                the PARENT_SSO_CODE value
     */
    public void setParentSsoCode(java.lang.String parentSsoCode) {
        this.parentSsoCode = parentSsoCode;
    }

    public String toString() {
        return super.toString();
    }

}