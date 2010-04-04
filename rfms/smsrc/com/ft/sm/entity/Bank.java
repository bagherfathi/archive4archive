/**
 * @{#} Bank.java Create on 2006-12-18 21:52:47
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SM_BANK table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="SM_BANK"
 */


public class Bank implements Serializable {
        private static final long serialVersionUID = 1L;

	public static String REF = "Bank";
	public static String PROP_STATUS = "status";
	public static String PROP_ORG_ID = "orgId";
	public static String PROP_BANK_TYPE = "bankType";
	public static String PROP_CREATE_DATE = "createDate";
	public static String PROP_BANK_CODE = "bankCode";
	public static String PROP_VALID_DATE = "validDate";
	public static String PROP_UPDATE_DATE = "updateDate";
	public static String PROP_OPERATOR_ID = "operatorId";
	public static String PROP_BANK_ID = "bankId";
	public static String PROP_APP_ID = "appId";
	public static String PROP_BANK_NAME = "bankName";
	public static String PROP_BANK_DESC = "bankDesc";
	public static String PROP_EXPIRE_DATE = "expireDate";
	public static String PROP_LOGIN_ORG_ID = "loginOrgId";


	public Bank () {
	    
	}


	// primary key
	private long bankId;

	// fields
	private java.lang.String bankName;
	private java.lang.String bankCode;
	private java.lang.String bankDesc;
	private long bankType;
	private long status;
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
     *  column="bank_id"
     */
	public long getBankId () {
		return bankId;
	}

	/**
	 * Set the unique identifier of this class
	 * @param bankId the new ID
	 */
	public void setBankId (long bankId) {
		this.bankId = bankId;
	}




	/**
	 * Return the value associated with the column: bank_name
	 */
	public java.lang.String getBankName () {
		return bankName;
	}

	/**
	 * Set the value related to the column: bank_name
	 * @param bankName the bank_name value
	 */
	public void setBankName (java.lang.String bankName) {
		this.bankName = bankName;
	}



	/**
	 * Return the value associated with the column: bank_code
	 */
	public java.lang.String getBankCode () {
		return bankCode;
	}

	/**
	 * Set the value related to the column: bank_code
	 * @param bankCode the bank_code value
	 */
	public void setBankCode (java.lang.String bankCode) {
		this.bankCode = bankCode;
	}



	/**
	 * Return the value associated with the column: bank_desc
	 */
	public java.lang.String getBankDesc () {
		return bankDesc;
	}

	/**
	 * Set the value related to the column: bank_desc
	 * @param bankDesc the bank_desc value
	 */
	public void setBankDesc (java.lang.String bankDesc) {
		this.bankDesc = bankDesc;
	}



	/**
	 * Return the value associated with the column: bank_type
	 */
	public long getBankType () {
		return bankType;
	}

	/**
	 * Set the value related to the column: bank_type
	 * @param bankType the bank_type value
	 */
	public void setBankType (long bankType) {
		this.bankType = bankType;
	}



	/**
	 * Return the value associated with the column: status
	 */
	public long getStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: status
	 * @param status the status value
	 */
	public void setStatus (long status) {
		this.status = status;
	}



	/**
	 * Return the value associated with the column: app_id
	 */
	public long getAppId () {
		return appId;
	}

	/**
	 * Set the value related to the column: app_id
	 * @param appId the app_id value
	 */
	public void setAppId (long appId) {
		this.appId = appId;
	}



	/**
	 * Return the value associated with the column: create_date
	 */
	public java.util.Date getCreateDate () {
		return createDate;
	}

	/**
	 * Set the value related to the column: create_date
	 * @param createDate the create_date value
	 */
	public void setCreateDate (java.util.Date createDate) {
		this.createDate = createDate;
	}



	/**
	 * Return the value associated with the column: valid_date
	 */
	public java.util.Date getValidDate () {
		return validDate;
	}

	/**
	 * Set the value related to the column: valid_date
	 * @param validDate the valid_date value
	 */
	public void setValidDate (java.util.Date validDate) {
		this.validDate = validDate;
	}



	/**
	 * Return the value associated with the column: expire_date
	 */
	public java.util.Date getExpireDate () {
		return expireDate;
	}

	/**
	 * Set the value related to the column: expire_date
	 * @param expireDate the expire_date value
	 */
	public void setExpireDate (java.util.Date expireDate) {
		this.expireDate = expireDate;
	}



	/**
	 * Return the value associated with the column: update_date
	 */
	public java.util.Date getUpdateDate () {
		return updateDate;
	}

	/**
	 * Set the value related to the column: update_date
	 * @param updateDate the update_date value
	 */
	public void setUpdateDate (java.util.Date updateDate) {
		this.updateDate = updateDate;
	}



	/**
	 * Return the value associated with the column: operator_id
	 */
	public long getOperatorId () {
		return operatorId;
	}

	/**
	 * Set the value related to the column: operator_id
	 * @param operatorId the operator_id value
	 */
	public void setOperatorId (long operatorId) {
		this.operatorId = operatorId;
	}



	/**
	 * Return the value associated with the column: org_id
	 */
	public long getOrgId () {
		return orgId;
	}

	/**
	 * Set the value related to the column: org_id
	 * @param orgId the org_id value
	 */
	public void setOrgId (long orgId) {
		this.orgId = orgId;
	}



	/**
	 * Return the value associated with the column: login_org_id
	 */
	public long getLoginOrgId () {
		return loginOrgId;
	}

	/**
	 * Set the value related to the column: login_org_id
	 * @param loginOrgId the login_org_id value
	 */
	public void setLoginOrgId (long loginOrgId) {
		this.loginOrgId = loginOrgId;
	}







	public String toString () {
		return super.toString();
	}


}