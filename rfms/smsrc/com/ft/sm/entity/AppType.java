/**
 * @{#} AppType.java Create on 2006-11-3 15:17:57
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;


/**
 * This is an object that contains data related to the CS_APP_TYPE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="CS_APP_TYPE"
 */


public class AppType implements Serializable {
        private static final long serialVersionUID = 1L;

	public static String REF = "AppType";
	public static String PROP_IS_ACCEPT = "isAccept";
	public static String PROP_OPERATOR_ID = "operatorId";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_APP_TYPE_CODE = "appTypeCode";
	public static String PROP_ORG_ID = "orgId";
	public static String PROP_APP_TYPE_ID = "appTypeId";
	public static String PROP_IS_MANAGER_NOTICE = "isManagerNotice";
	public static String PROP_APP_ACTION = "appAction";
	public static String PROP_IS_CHARGE = "isCharge";
	public static String PROP_CATEGORY = "category";
	public static String PROP_IS_PRINT = "isPrint";
	public static String PROP_LOGIN_ORG_ID = "loginOrgId";


	public AppType () {
	    
	}


	// primary key
	private long appTypeId;

	// fields
	private java.lang.String appAction;
	private java.lang.String description;
	private long isCharge;
	private long isManagerNotice;
	private long isAccept;
	private long isPrint;
	private long category;
	private long operatorId;
	private long orgId;
	private long loginOrgId;
	private java.lang.String appTypeCode;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="APP_TYPE_ID"
     */
	public long getAppTypeId () {
		return appTypeId;
	}

	/**
	 * Set the unique identifier of this class
	 * @param appTypeId the new ID
	 */
	public void setAppTypeId (long appTypeId) {
		this.appTypeId = appTypeId;
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
	 * Return the value associated with the column: DESCRIPTION
	 */
	public java.lang.String getDescription () {
		return description;
	}

	/**
	 * Set the value related to the column: DESCRIPTION
	 * @param description the DESCRIPTION value
	 */
	public void setDescription (java.lang.String description) {
		this.description = description;
	}



	/**
	 * Return the value associated with the column: IS_CHARGE
	 */
	public long getIsCharge () {
		return isCharge;
	}

	/**
	 * Set the value related to the column: IS_CHARGE
	 * @param isCharge the IS_CHARGE value
	 */
	public void setIsCharge (long isCharge) {
		this.isCharge = isCharge;
	}



	/**
	 * Return the value associated with the column: IS_MANAGER_NOTICE
	 */
	public long getIsManagerNotice () {
		return isManagerNotice;
	}

	/**
	 * Set the value related to the column: IS_MANAGER_NOTICE
	 * @param isManagerNotice the IS_MANAGER_NOTICE value
	 */
	public void setIsManagerNotice (long isManagerNotice) {
		this.isManagerNotice = isManagerNotice;
	}



	/**
	 * Return the value associated with the column: IS_ACCEPT
	 */
	public long getIsAccept () {
		return isAccept;
	}

	/**
	 * Set the value related to the column: IS_ACCEPT
	 * @param isAccept the IS_ACCEPT value
	 */
	public void setIsAccept (long isAccept) {
		this.isAccept = isAccept;
	}



	/**
	 * Return the value associated with the column: IS_PRINT
	 */
	public long getIsPrint () {
		return isPrint;
	}

	/**
	 * Set the value related to the column: IS_PRINT
	 * @param isPrint the IS_PRINT value
	 */
	public void setIsPrint (long isPrint) {
		this.isPrint = isPrint;
	}



	/**
	 * Return the value associated with the column: CATEGORY
	 */
	public long getCategory () {
		return category;
	}

	/**
	 * Set the value related to the column: CATEGORY
	 * @param category the CATEGORY value
	 */
	public void setCategory (long category) {
		this.category = category;
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
	 * Return the value associated with the column: APP_TYPE_CODE
	 */
	public java.lang.String getAppTypeCode () {
		return appTypeCode;
	}

	/**
	 * Set the value related to the column: APP_TYPE_CODE
	 * @param appTypeCode the APP_TYPE_CODE value
	 */
	public void setAppTypeCode (java.lang.String appTypeCode) {
		this.appTypeCode = appTypeCode;
	}







	public String toString () {
		return super.toString();
	}


}