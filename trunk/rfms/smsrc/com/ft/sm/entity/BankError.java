/**
 * @{#} BankError.java Create on 2006-12-18 21:52:47
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SM_BANK_ERROR table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="SM_BANK_ERROR"
 */


public class BankError implements Serializable {
        private static final long serialVersionUID = 1L;

	public static String REF = "BankError";
	public static String PROP_STATUS = "status";
	public static String PROP_BANK_ERROR_DESC = "bankErrorDesc";
	public static String PROP_ORG_ID = "orgId";
	public static String PROP_CREATE_DATE = "createDate";
	public static String PROP_BANK_ERROR_CODE = "bankErrorCode";
	public static String PROP_VALID_DATE = "validDate";
	public static String PROP_UPDATE_DATE = "updateDate";
	public static String PROP_OPERATOR_ID = "operatorId";
	public static String PROP_BANK_ID = "bankId";
	public static String PROP_APP_ID = "appId";
	public static String PROP_BANK_ERROR_ID = "bankErrorId";
	public static String PROP_EXPIRE_DATE = "expireDate";
	public static String PROP_LOGIN_ORG_ID = "loginOrgId";


	public BankError () {
	    
	}


	// primary key
	private long bankErrorId;

	// fields
	private java.lang.String bankErrorCode;
	private java.lang.String bankErrorDesc;
	private long bankId;
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
     *  column="bank_error_id"
     */
	public long getBankErrorId () {
		return bankErrorId;
	}

	/**
	 * Set the unique identifier of this class
	 * @param bankErrorId the new ID
	 */
	public void setBankErrorId (long bankErrorId) {
		this.bankErrorId = bankErrorId;
	}




	/**
	 * Return the value associated with the column: bank_error_code
	 */
	public java.lang.String getBankErrorCode () {
		return bankErrorCode;
	}

	/**
	 * Set the value related to the column: bank_error_code
	 * @param bankErrorCode the bank_error_code value
	 */
	public void setBankErrorCode (java.lang.String bankErrorCode) {
		this.bankErrorCode = bankErrorCode;
	}



	/**
	 * Return the value associated with the column: bank_error_desc
	 */
	public java.lang.String getBankErrorDesc () {
		return bankErrorDesc;
	}

	/**
	 * Set the value related to the column: bank_error_desc
	 * @param bankErrorDesc the bank_error_desc value
	 */
	public void setBankErrorDesc (java.lang.String bankErrorDesc) {
		this.bankErrorDesc = bankErrorDesc;
	}



	/**
	 * Return the value associated with the column: bank_id
	 */
	public long getBankId () {
		return bankId;
	}

	/**
	 * Set the value related to the column: bank_id
	 * @param bankId the bank_id value
	 */
	public void setBankId (long bankId) {
		this.bankId = bankId;
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