/**
 * @{#} ViApp.java Create on 2006-10-25 12:59:30
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;


/**
 * This is an object that contains data related to the CS_VI_APP table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="CS_VI_APP"
 */


public class ViApp implements Serializable {
        private static final long serialVersionUID = 1L;

	public static String REF = "ViApp";
	public static String PROP_RELAT_APP_ID = "relatAppId";
	public static String PROP_OPERATOR_ID = "operatorId";
	public static String PROP_ORG_ID = "orgId";
	public static String PROP_NOTES = "notes";
	public static String PROP_APP_CODE = "appCode";
	public static String PROP_APP_ID = "appId";
	public static String PROP_APP_ACTION = "appAction";
	public static String PROP_LOGIN_ORG_ID = "loginOrgId";
	public static String PROP_APP_TIME = "appTime";
	public static String PROP_ID = "id";


	public ViApp () {
	    
	}


	// primary key
	private long id;

	// fields
	private long appId;
	private java.lang.String appCode;
	private java.util.Date appTime;
	private java.lang.String appAction;
	private long relatAppId;
	private java.lang.String notes;
	private long operatorId;
	private long orgId;
	private long loginOrgId;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="increment"
     *  column="ID"
     */
	public long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (long id) {
		this.id = id;
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
	 * Return the value associated with the column: APP_CODE
	 */
	public java.lang.String getAppCode () {
		return appCode;
	}

	/**
	 * Set the value related to the column: APP_CODE
	 * @param appCode the APP_CODE value
	 */
	public void setAppCode (java.lang.String appCode) {
		this.appCode = appCode;
	}



	/**
	 * Return the value associated with the column: APP_TIME
	 */
	public java.util.Date getAppTime () {
		return appTime;
	}

	/**
	 * Set the value related to the column: APP_TIME
	 * @param appTime the APP_TIME value
	 */
	public void setAppTime (java.util.Date appTime) {
		this.appTime = appTime;
	}



	/**
	 * Return the value associated with the column: APP_ACTION
	 */
	public java.lang.String getAppAction () {
		return appAction;
	}

	/**
	 * Set the value related to the column: APP_ACTION
	 * @param appAction the APP_ACTION value
	 */
	public void setAppAction (java.lang.String appAction) {
		this.appAction = appAction;
	}



	/**
	 * Return the value associated with the column: RELAT_APP_ID
	 */
	public long getRelatAppId () {
		return relatAppId;
	}

	/**
	 * Set the value related to the column: RELAT_APP_ID
	 * @param relatAppId the RELAT_APP_ID value
	 */
	public void setRelatAppId (long relatAppId) {
		this.relatAppId = relatAppId;
	}



	/**
	 * Return the value associated with the column: NOTES
	 */
	public java.lang.String getNotes () {
		return notes;
	}

	/**
	 * Set the value related to the column: NOTES
	 * @param notes the NOTES value
	 */
	public void setNotes (java.lang.String notes) {
		this.notes = notes;
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