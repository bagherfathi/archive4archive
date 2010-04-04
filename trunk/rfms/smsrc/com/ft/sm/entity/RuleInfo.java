/**
 * @{#} RuleInfo.java Create on 2006-12-28 22:42:20
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SM_RULE_INFO table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="SM_RULE_INFO"
 */


public class RuleInfo implements Serializable {
        private static final long serialVersionUID = 1L;

	public static String REF = "RuleInfo";
	public static String PROP_TYPE = "type";
	public static String PROP_ORG_ID = "orgId";
	public static String PROP_PUBLISH_VERSION = "publishVersion";
	public static String PROP_CREATE_DATE = "createDate";
	public static String PROP_RULE_ID = "ruleId";
	public static String PROP_RULE_NAME = "ruleName";
	public static String PROP_VALID_DATE = "validDate";
	public static String PROP_UPDATE_DATE = "updateDate";
	public static String PROP_OPERATOR_ID = "operatorId";
	public static String PROP_APP_ID = "appId";
	public static String PROP_CATEGORY_ID = "categoryId";
	public static String PROP_RULE_DESC = "ruleDesc";
	public static String PROP_EXPIRE_DATE = "expireDate";
	public static String PROP_LOGIN_ORG_ID = "loginOrgId";
	public static String PROP_RULE_CODE = "ruleCode";
	public static String PROP_RULE_FILE_ID = "ruleFileId";


	public RuleInfo () {
	    
	}


	// primary key
	private long ruleId;

	// fields
	private long categoryId;
	private java.lang.String ruleName;
	private java.lang.String ruleCode;
	private java.lang.String ruleDesc;
	private long publishVersion;
	private long type;
	private long ruleFileId;
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
     * @hibernate.id
     *  generator-class="sequence"
     *  column="RULE_ID"
     */
	public long getRuleId () {
		return ruleId;
	}

	/**
	 * Set the unique identifier of this class
	 * @param ruleId the new ID
	 */
	public void setRuleId (long ruleId) {
		this.ruleId = ruleId;
	}




	/**
	 * Return the value associated with the column: CATEGORY_ID
	 */
	public long getCategoryId () {
		return categoryId;
	}

	/**
	 * Set the value related to the column: CATEGORY_ID
	 * @param categoryId the CATEGORY_ID value
	 */
	public void setCategoryId (long categoryId) {
		this.categoryId = categoryId;
	}



	/**
	 * Return the value associated with the column: RULE_NAME
	 */
	public java.lang.String getRuleName () {
		return ruleName;
	}

	/**
	 * Set the value related to the column: RULE_NAME
	 * @param ruleName the RULE_NAME value
	 */
	public void setRuleName (java.lang.String ruleName) {
		this.ruleName = ruleName;
	}



	/**
	 * Return the value associated with the column: RULE_CODE
	 */
	public java.lang.String getRuleCode () {
		return ruleCode;
	}

	/**
	 * Set the value related to the column: RULE_CODE
	 * @param ruleCode the RULE_CODE value
	 */
	public void setRuleCode (java.lang.String ruleCode) {
		this.ruleCode = ruleCode;
	}



	/**
	 * Return the value associated with the column: RULE_DESC
	 */
	public java.lang.String getRuleDesc () {
		return ruleDesc;
	}

	/**
	 * Set the value related to the column: RULE_DESC
	 * @param ruleDesc the RULE_DESC value
	 */
	public void setRuleDesc (java.lang.String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}



	/**
	 * Return the value associated with the column: PUBLISH_VERSION
	 */
	public long getPublishVersion () {
		return publishVersion;
	}

	/**
	 * Set the value related to the column: PUBLISH_VERSION
	 * @param publishVersion the PUBLISH_VERSION value
	 */
	public void setPublishVersion (long publishVersion) {
		this.publishVersion = publishVersion;
	}



	/**
	 * Return the value associated with the column: TYPE
	 */
	public long getType () {
		return type;
	}

	/**
	 * Set the value related to the column: TYPE
	 * @param type the TYPE value
	 */
	public void setType (long type) {
		this.type = type;
	}



	/**
	 * Return the value associated with the column: RULE_FILE_ID
	 */
	public long getRuleFileId () {
		return ruleFileId;
	}

	/**
	 * Set the value related to the column: RULE_FILE_ID
	 * @param ruleFileId the RULE_FILE_ID value
	 */
	public void setRuleFileId (long ruleFileId) {
		this.ruleFileId = ruleFileId;
	}



	/**
	 * Return the value associated with the column: APP_ID
	 */
	public long getAppId () {
		return appId;
	}

	/**
	 * Set the value related to the column: APP_ID
	 * @param appId the APP_ID value
	 */
	public void setAppId (long appId) {
		this.appId = appId;
	}



	/**
	 * Return the value associated with the column: CREATE_DATE
	 */
	public java.util.Date getCreateDate () {
		return createDate;
	}

	/**
	 * Set the value related to the column: CREATE_DATE
	 * @param createDate the CREATE_DATE value
	 */
	public void setCreateDate (java.util.Date createDate) {
		this.createDate = createDate;
	}



	/**
	 * Return the value associated with the column: VALID_DATE
	 */
	public java.util.Date getValidDate () {
		return validDate;
	}

	/**
	 * Set the value related to the column: VALID_DATE
	 * @param validDate the VALID_DATE value
	 */
	public void setValidDate (java.util.Date validDate) {
		this.validDate = validDate;
	}



	/**
	 * Return the value associated with the column: EXPIRE_DATE
	 */
	public java.util.Date getExpireDate () {
		return expireDate;
	}

	/**
	 * Set the value related to the column: EXPIRE_DATE
	 * @param expireDate the EXPIRE_DATE value
	 */
	public void setExpireDate (java.util.Date expireDate) {
		this.expireDate = expireDate;
	}



	/**
	 * Return the value associated with the column: UPDATE_DATE
	 */
	public java.util.Date getUpdateDate () {
		return updateDate;
	}

	/**
	 * Set the value related to the column: UPDATE_DATE
	 * @param updateDate the UPDATE_DATE value
	 */
	public void setUpdateDate (java.util.Date updateDate) {
		this.updateDate = updateDate;
	}



	/**
	 * Return the value associated with the column: OPERATOR_ID
	 */
	public long getOperatorId () {
		return operatorId;
	}

	/**
	 * Set the value related to the column: OPERATOR_ID
	 * @param operatorId the OPERATOR_ID value
	 */
	public void setOperatorId (long operatorId) {
		this.operatorId = operatorId;
	}



	/**
	 * Return the value associated with the column: ORG_ID
	 */
	public long getOrgId () {
		return orgId;
	}

	/**
	 * Set the value related to the column: ORG_ID
	 * @param orgId the ORG_ID value
	 */
	public void setOrgId (long orgId) {
		this.orgId = orgId;
	}



	/**
	 * Return the value associated with the column: LOGIN_ORG_ID
	 */
	public long getLoginOrgId () {
		return loginOrgId;
	}

	/**
	 * Set the value related to the column: LOGIN_ORG_ID
	 * @param loginOrgId the LOGIN_ORG_ID value
	 */
	public void setLoginOrgId (long loginOrgId) {
		this.loginOrgId = loginOrgId;
	}







	public String toString () {
		return super.toString();
	}


}