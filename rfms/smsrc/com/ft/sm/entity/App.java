package com.ft.sm.entity;

import java.io.Serializable;


/**
 * This is an object that contains data related to the CS_APP table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="CS_APP"
 */


public class App implements Serializable {
        private static final long serialVersionUID = 1L;

	public static String REF = "App";
	public static String PROP_CORP_ORG_ID = "corpOrgId";
	public static String PROP_APP_STATUS = "appStatus";
	public static String PROP_RELAT_APP_ID = "relatAppId";
	public static String PROP_OPERATOR_ID = "operatorId";
	public static String PROP_ORG_ID = "orgId";
	public static String PROP_APP_ID = "appId";
	public static String PROP_NOTES = "notes";
	public static String PROP_APP_CODE = "appCode";
	public static String PROP_APP_ACTION = "appAction";
	public static String PROP_REC_ORG_ID = "recOrgId";
	public static String PROP_LOGIN_ORG_ID = "loginOrgId";
	public static String PROP_APP_TIME = "appTime";


	public App () {
	    
	}


	// primary key
	private long appId;

	// fields
	private java.lang.String appAction;
	private java.lang.String appCode;
	private long appStatus;
	private java.util.Date appTime;
	private long corpOrgId;
	private long loginOrgId;
	private java.lang.String notes;
	private long operatorId;
	private long orgId;
	private long recOrgId;
	private long relatAppId;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="APP_ID"
     */
	public long getAppId () {
		return appId;
	}

	/**
	 * Set the unique identifier of this class
	 * @param appId the new ID
	 */
	public void setAppId (long appId) {
		this.appId = appId;
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
	 * Return the value associated with the column: APP_STATUS
	 */
	public long getAppStatus () {
		return appStatus;
	}

	/**
	 * Set the value related to the column: APP_STATUS
	 * @param appStatus the APP_STATUS value
	 */
	public void setAppStatus (long appStatus) {
		this.appStatus = appStatus;
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
	 * Return the value associated with the column: CORP_ORG_ID
	 */
	public long getCorpOrgId () {
		return corpOrgId;
	}

	/**
	 * Set the value related to the column: CORP_ORG_ID
	 * @param corpOrgId the CORP_ORG_ID value
	 */
	public void setCorpOrgId (long corpOrgId) {
		this.corpOrgId = corpOrgId;
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
	 * Return the value associated with the column: REC_ORG_ID
	 */
	public long getRecOrgId () {
		return recOrgId;
	}

	/**
	 * Set the value related to the column: REC_ORG_ID
	 * @param recOrgId the REC_ORG_ID value
	 */
	public void setRecOrgId (long recOrgId) {
		this.recOrgId = recOrgId;
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







	public String toString () {
		return super.toString();
	}


}