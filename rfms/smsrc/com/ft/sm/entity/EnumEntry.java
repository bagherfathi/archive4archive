/**
 * @{#} EnumEntry.java Create on 2007-1-22 10:30:56
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SM_ENUM_ENTRY table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="SM_ENUM_ENTRY"
 */


public class EnumEntry implements Serializable {
        private static final long serialVersionUID = 1L;

	public static String REF = "EnumEntry";
	public static String PROP_ENTRY_LINK_VALUE = "entryLinkValue";
	public static String PROP_ORG_ID = "orgId";
	public static String PROP_ENUM_ENTRY_VALUE = "enumEntryValue";
	public static String PROP_CREATE_DATE = "createDate";
	public static String PROP_ENUM_ENTRY_ID = "enumEntryId";
	public static String PROP_VALID_DATE = "validDate";
	public static String PROP_UPDATE_DATE = "updateDate";
	public static String PROP_OPERATOR_ID = "operatorId";
	public static String PROP_ENUM_ID = "enumId";
	public static String PROP_ENUM_ENTRY_LABEL = "enumEntryLabel";
	public static String PROP_APP_ID = "appId";
	public static String PROP_ENTRY_ORDER = "entryOrder";
	public static String PROP_EXPIRE_DATE = "expireDate";
	public static String PROP_ENUM_ENTRY_STATUS = "enumEntryStatus";
	public static String PROP_LOGIN_ORG_ID = "loginOrgId";


	public EnumEntry () {
	    
	}


	// primary key
	private long enumEntryId;

	// fields
	private long enumId;
	private java.lang.String enumEntryLabel;
	private java.lang.String enumEntryValue;
	private long enumEntryStatus;
	private java.lang.String entryLinkValue;
	private long appId;
	private java.util.Date createDate;
	private java.util.Date validDate;
	private java.util.Date expireDate;
	private java.util.Date updateDate;
	private long operatorId;
	private long orgId;
	private long loginOrgId;
	private long entryOrder;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="ENUM_ENTRY_ID"
     */
	public long getEnumEntryId () {
		return enumEntryId;
	}

	/**
	 * Set the unique identifier of this class
	 * @param enumEntryId the new ID
	 */
	public void setEnumEntryId (long enumEntryId) {
		this.enumEntryId = enumEntryId;
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
	 * Return the value associated with the column: ENUM_ENTRY_LABEL
	 */
	public java.lang.String getEnumEntryLabel () {
		return enumEntryLabel;
	}

	/**
	 * Set the value related to the column: ENUM_ENTRY_LABEL
	 * @param enumEntryLabel the ENUM_ENTRY_LABEL value
	 */
	public void setEnumEntryLabel (java.lang.String enumEntryLabel) {
		this.enumEntryLabel = enumEntryLabel;
	}



	/**
	 * Return the value associated with the column: ENUM_ENTRY_VALUE
	 */
	public java.lang.String getEnumEntryValue () {
		return enumEntryValue;
	}

	/**
	 * Set the value related to the column: ENUM_ENTRY_VALUE
	 * @param enumEntryValue the ENUM_ENTRY_VALUE value
	 */
	public void setEnumEntryValue (java.lang.String enumEntryValue) {
		this.enumEntryValue = enumEntryValue;
	}



	/**
	 * Return the value associated with the column: ENUM_ENTRY_STATUS
	 */
	public long getEnumEntryStatus () {
		return enumEntryStatus;
	}

	/**
	 * Set the value related to the column: ENUM_ENTRY_STATUS
	 * @param enumEntryStatus the ENUM_ENTRY_STATUS value
	 */
	public void setEnumEntryStatus (long enumEntryStatus) {
		this.enumEntryStatus = enumEntryStatus;
	}



	/**
	 * Return the value associated with the column: ENTRY_LINK_VALUE
	 */
	public java.lang.String getEntryLinkValue () {
		return entryLinkValue;
	}

	/**
	 * Set the value related to the column: ENTRY_LINK_VALUE
	 * @param entryLinkValue the ENTRY_LINK_VALUE value
	 */
	public void setEntryLinkValue (java.lang.String entryLinkValue) {
		this.entryLinkValue = entryLinkValue;
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
	 * Return the value associated with the column: ENTRY_ORDER
	 */
	public long getEntryOrder () {
		return entryOrder;
	}

	/**
	 * Set the value related to the column: ENTRY_ORDER
	 * @param entryOrder the ENTRY_ORDER value
	 */
	public void setEntryOrder (long entryOrder) {
		this.entryOrder = entryOrder;
	}







	public String toString () {
		return super.toString();
	}


}