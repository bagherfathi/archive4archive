/**
 * @{#} Operator.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_OPERATOR table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * 
 * @hibernate.class table="SM_OPERATOR"
 */

public class Operator implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "Operator";

    public static String PROP_STATUS = "status";

    public static String PROP_TYPE = "type";

    public static String PROP_SSO_ACCESSED = "ssoAccessed";

    public static String PROP_LOCK_STATUS = "lockStatus";

    public static String PROP_ORG_ID = "orgId";

    public static String PROP_REG_TYPE = "regType";

    public static String PROP_CREATE_TIME = "createTime";

    public static String PROP_POSTCODE = "postcode";

    public static String PROP_JOB_NUMBER = "jobNumber";

    public static String PROP_SSO_STATUS = "ssoStatus";

    public static String PROP_PASSWORD = "password";

    public static String PROP_ORG_SSO_CODE = "orgSsoCode";

    public static String PROP_VALID_TIME = "validTime";

    public static String PROP_ADDRESS = "address";

    public static String PROP_MODIFY_TIME = "modifyTime";

    public static String PROP_TELE_PHONE = "telePhone";

    public static String PROP_REG_NUMBER = "regNumber";

    public static String PROP_MSN = "msn";

    public static String PROP_LOGIN_NAME = "loginName";

    public static String PROP_EXPIRE_TIME = "expireTime";

    public static String PROP_MOBILE_PHONE = "mobilePhone";

    public static String PROP_MEMO = "memo";

    public static String PROP_REGION_ID = "regionId";

    public static String PROP_SSO_CODE = "ssoCode";

    public static String PROP_EMAIL = "email";

    public static String PROP_OPERATOR_ID = "operatorId";

    public static String PROP_OP_NAME = "opName";
    
    public static String PROP_MERCHANT_CODE = "merchantCode";
    

    public Operator() {

    }

    // primary key
    private long operatorId;

    // fields
    private long orgId;

    private java.lang.String opName;

    private java.lang.String loginName;

    private long type;

    private long lockStatus;

    private java.lang.String memo;

    private long regionId;

    private java.lang.String email;

    private java.lang.String msn;

    private java.lang.String password;

    private long status;

    private java.lang.String address;

    private java.lang.String mobilePhone;

    private java.lang.String telePhone;

    private java.lang.String postcode;

    private long regType;

    private java.lang.String regNumber;

    private java.util.Date createTime;

    private java.util.Date modifyTime;

    private java.util.Date validTime;

    private java.util.Date expireTime;

    private java.lang.String ssoCode;

    private java.lang.String jobNumber;

    private java.lang.String orgSsoCode;

    private long ssoStatus;

    private long ssoAccessed;
    
    private java.lang.String merchantCode;
    
    
    /**
	 * @return the merchantCode
	 */
	public java.lang.String getMerchantCode() {
		return merchantCode;
	}

	/**
	 * @param merchantCode the merchantCode to set
	 */
	public void setMerchantCode(java.lang.String merchantCode) {
		this.merchantCode = merchantCode;
	}

	/**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="OPERATOR_ID"
     */
    public long getOperatorId() {
        return operatorId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param operatorId
     *                the new ID
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
     * Return the value associated with the column: OP_NAME
     */
    public java.lang.String getOpName() {
        return opName;
    }

    /**
     * Set the value related to the column: OP_NAME
     * 
     * @param opName
     *                the OP_NAME value
     */
    public void setOpName(java.lang.String opName) {
        this.opName = opName;
    }

    /**
     * Return the value associated with the column: LOGIN_NAME
     */
    public java.lang.String getLoginName() {
        return loginName;
    }

    /**
     * Set the value related to the column: LOGIN_NAME
     * 
     * @param loginName
     *                the LOGIN_NAME value
     */
    public void setLoginName(java.lang.String loginName) {
        this.loginName = loginName;
    }

    /**
     * Return the value associated with the column: TYPE
     */
    public long getType() {
        return type;
    }

    /**
     * Set the value related to the column: TYPE
     * 
     * @param type
     *                the TYPE value
     */
    public void setType(long type) {
        this.type = type;
    }

    /**
     * Return the value associated with the column: LOCK_STATUS
     */
    public long getLockStatus() {
        return lockStatus;
    }

    /**
     * Set the value related to the column: LOCK_STATUS
     * 
     * @param lockStatus
     *                the LOCK_STATUS value
     */
    public void setLockStatus(long lockStatus) {
        this.lockStatus = lockStatus;
    }

    /**
     * Return the value associated with the column: MEMO
     */
    public java.lang.String getMemo() {
        return memo;
    }

    /**
     * Set the value related to the column: MEMO
     * 
     * @param memo
     *                the MEMO value
     */
    public void setMemo(java.lang.String memo) {
        this.memo = memo;
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
     * Return the value associated with the column: EMAIL
     */
    public java.lang.String getEmail() {
        return email;
    }

    /**
     * Set the value related to the column: EMAIL
     * 
     * @param email
     *                the EMAIL value
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    /**
     * Return the value associated with the column: MSN
     */
    public java.lang.String getMsn() {
        return msn;
    }

    /**
     * Set the value related to the column: MSN
     * 
     * @param msn
     *                the MSN value
     */
    public void setMsn(java.lang.String msn) {
        this.msn = msn;
    }

    /**
     * Return the value associated with the column: PASSWORD
     */
    public java.lang.String getPassword() {
        return password;
    }

    /**
     * Set the value related to the column: PASSWORD
     * 
     * @param password
     *                the PASSWORD value
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
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
     * Return the value associated with the column: REG_TYPE
     */
    public long getRegType() {
        return regType;
    }

    /**
     * Set the value related to the column: REG_TYPE
     * 
     * @param regType
     *                the REG_TYPE value
     */
    public void setRegType(long regType) {
        this.regType = regType;
    }

    /**
     * Return the value associated with the column: REG_NUMBER
     */
    public java.lang.String getRegNumber() {
        return regNumber;
    }

    /**
     * Set the value related to the column: REG_NUMBER
     * 
     * @param regNumber
     *                the REG_NUMBER value
     */
    public void setRegNumber(java.lang.String regNumber) {
        this.regNumber = regNumber;
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
     * Return the value associated with the column: VALID_TIME
     */
    public java.util.Date getValidTime() {
        return validTime;
    }

    /**
     * Set the value related to the column: VALID_TIME
     * 
     * @param validTime
     *                the VALID_TIME value
     */
    public void setValidTime(java.util.Date validTime) {
        this.validTime = validTime;
    }

    /**
     * Return the value associated with the column: EXPIRE_TIME
     */
    public java.util.Date getExpireTime() {
        return expireTime;
    }

    /**
     * Set the value related to the column: EXPIRE_TIME
     * 
     * @param expireTime
     *                the EXPIRE_TIME value
     */
    public void setExpireTime(java.util.Date expireTime) {
        this.expireTime = expireTime;
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
     * Return the value associated with the column: JOB_NUMBER
     */
    public java.lang.String getJobNumber() {
        return jobNumber;
    }

    /**
     * Set the value related to the column: JOB_NUMBER
     * 
     * @param jobNumber
     *                the JOB_NUMBER value
     */
    public void setJobNumber(java.lang.String jobNumber) {
        this.jobNumber = jobNumber;
    }

    /**
     * Return the value associated with the column: ORG_SSO_CODE
     */
    public java.lang.String getOrgSsoCode() {
        return orgSsoCode;
    }

    /**
     * Set the value related to the column: ORG_SSO_CODE
     * 
     * @param orgSsoCode
     *                the ORG_SSO_CODE value
     */
    public void setOrgSsoCode(java.lang.String orgSsoCode) {
        this.orgSsoCode = orgSsoCode;
    }

    /**
     * Return the value associated with the column: SSO_STATUS
     */
    public long getSsoStatus() {
        return ssoStatus;
    }

    /**
     * Set the value related to the column: SSO_STATUS
     * 
     * @param ssoStatus
     *                the SSO_STATUS value
     */
    public void setSsoStatus(long ssoStatus) {
        this.ssoStatus = ssoStatus;
    }

    /**
     * Return the value associated with the column: SSO_ACCESSED
     */
    public long getSsoAccessed() {
        return ssoAccessed;
    }

    /**
     * Set the value related to the column: SSO_ACCESSED
     * 
     * @param ssoAccessed
     *                the SSO_ACCESSED value
     */
    public void setSsoAccessed(long ssoAccessed) {
        this.ssoAccessed = ssoAccessed;
    }

    public String toString() {
        return super.toString();
    }

}