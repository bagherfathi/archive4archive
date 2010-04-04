/**
 * @{#} EnumHis.java Create on 2006-12-20 0:01:08
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SM_ENUM_HIS table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="SM_ENUM_HIS"
 */


public class EnumHis implements Serializable {
        private static final long serialVersionUID = 1L;

	public static String REF = "EnumHis";
	public static String PROP_ENUM_CODE = "enumCode";
	public static String PROP_STATUS = "status";
	public static String PROP_NEW_APP_ID = "newAppId";
	public static String PROP_ORG_ID = "orgId";
	public static String PROP_ENUM_NAME = "enumName";
	public static String PROP_CREATE_DATE = "createDate";
	public static String PROP_HISTORY_ID = "historyId";
	public static String PROP_VALID_DATE = "validDate";
	public static String PROP_UPDATE_DATE = "updateDate";
	public static String PROP_OPERATOR_ID = "operatorId";
	public static String PROP_ENUM_ID = "enumId";
	public static String PROP_APP_ID = "appId";
	public static String PROP_ENUM_DESC = "enumDesc";
	public static String PROP_EXPIRE_DATE = "expireDate";
	public static String PROP_LOGIN_ORG_ID = "loginOrgId";


	public EnumHis () {
	    
	}


	// primary key
	private long historyId;

	// fields
	private long enumId;
	private java.lang.String enumName;
	private java.lang.String enumCode;
	private java.lang.String enumDesc;
	private long status;
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
     * @hibernate.id
     *  generator-class="sequence"
     *  column="HISTORY_ID"
     */
	public long getHistoryId () {
		return historyId;
	}

	/**
	 * Set the unique identifier of this class
	 * @param historyId the new ID
	 */
	public void setHistoryId (long historyId) {
		this.historyId = historyId;
	}




	/**
	 * Return the value associated with the column: ENUM_ID
	 */
	public long getEnumId () {
		return enumId;
	}

	/**
	 * Set the value related to the column: ENUM_ID
	 * @param enumId the ENUM_ID value
	 */
	public void setEnumId (long enumId) {
		this.enumId = enumId;
	}



	/**
	 * Return the value associated with the column: ENUM_NAME
	 */
	public java.lang.String getEnumName () {
		return enumName;
	}

	/**
	 * Set the value related to the column: ENUM_NAME
	 * @param enumName the ENUM_NAME value
	 */
	public void setEnumName (java.lang.String enumName) {
		this.enumName = enumName;
	}



	/**
	 * Return the value associated with the column: ENUM_CODE
	 */
	public java.lang.String getEnumCode () {
		return enumCode;
	}

	/**
	 * Set the value related to the column: ENUM_CODE
	 * @param enumCode the ENUM_CODE value
	 */
	public void setEnumCode (java.lang.String enumCode) {
		this.enumCode = enumCode;
	}



	/**
	 * Return the value associated with the column: ENUM_DESC
	 */
	public java.lang.String getEnumDesc () {
		return enumDesc;
	}

	/**
	 * Set the value related to the column: ENUM_DESC
	 * @param enumDesc the ENUM_DESC value
	 */
	public void setEnumDesc (java.lang.String enumDesc) {
		this.enumDesc = enumDesc;
	}



	/**
	 * Return the value associated with the column: STATUS
	 */
	public long getStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: STATUS
	 * @param status the STATUS value
	 */
	public void setStatus (long status) {
		this.status = status;
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



	/**
	 * Return the value associated with the column: NEW_APP_ID
	 */
	public long getNewAppId () {
		return newAppId;
	}

	/**
	 * Set the value related to the column: NEW_APP_ID
	 * @param newAppId the NEW_APP_ID value
	 */
	public void setNewAppId (long newAppId) {
		this.newAppId = newAppId;
	}







	public String toString () {
		return super.toString();
	}


}