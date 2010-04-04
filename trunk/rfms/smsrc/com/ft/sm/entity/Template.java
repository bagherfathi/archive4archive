/**
 * @{#} Template.java Create on 2006-12-25 8:52:57
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SM_TEMPLATE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="SM_TEMPLATE"
 */


public class Template implements Serializable {
        private static final long serialVersionUID = 1L;

	public static String REF = "Template";
	public static String PROP_PUBLISH_VERSION = "publishVersion";
	public static String PROP_ORG_ID = "orgId";
	public static String PROP_LAST_VERSION = "lastVersion";
	public static String PROP_TEMPLATE_NAME = "templateName";
	public static String PROP_CREATE_DATE = "createDate";
	public static String PROP_VALID_DATE = "validDate";
	public static String PROP_TEMPLATE_CODE = "templateCode";
	public static String PROP_TEMPLATE_DESC = "templateDesc";
	public static String PROP_UPDATE_DATE = "updateDate";
	public static String PROP_OPERATOR_ID = "operatorId";
	public static String PROP_APP_ID = "appId";
	public static String PROP_EXPIRE_DATE = "expireDate";
	public static String PROP_LOGIN_ORG_ID = "loginOrgId";
	public static String PROP_CATEGORY_CODE = "categoryCode";
	public static String PROP_TEMPLATE_ID = "templateId";


	public Template () {
	    
	}


	// primary key
	private long templateId;

	// fields
	private java.lang.String templateName;
	private java.lang.String templateCode;
	private java.lang.String categoryCode;
	private java.lang.String templateDesc;
	private long lastVersion;
	private long publishVersion;
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
     *  column="TEMPLATE_ID"
     */
	public long getTemplateId () {
		return templateId;
	}

	/**
	 * Set the unique identifier of this class
	 * @param templateId the new ID
	 */
	public void setTemplateId (long templateId) {
		this.templateId = templateId;
	}




	/**
	 * Return the value associated with the column: TEMPLATE_NAME
	 */
	public java.lang.String getTemplateName () {
		return templateName;
	}

	/**
	 * Set the value related to the column: TEMPLATE_NAME
	 * @param templateName the TEMPLATE_NAME value
	 */
	public void setTemplateName (java.lang.String templateName) {
		this.templateName = templateName;
	}



	/**
	 * Return the value associated with the column: TEMPLATE_CODE
	 */
	public java.lang.String getTemplateCode () {
		return templateCode;
	}

	/**
	 * Set the value related to the column: TEMPLATE_CODE
	 * @param templateCode the TEMPLATE_CODE value
	 */
	public void setTemplateCode (java.lang.String templateCode) {
		this.templateCode = templateCode;
	}



	/**
	 * Return the value associated with the column: CATEGORY_CODE
	 */
	public java.lang.String getCategoryCode () {
		return categoryCode;
	}

	/**
	 * Set the value related to the column: CATEGORY_CODE
	 * @param categoryCode the CATEGORY_CODE value
	 */
	public void setCategoryCode (java.lang.String categoryCode) {
		this.categoryCode = categoryCode;
	}



	/**
	 * Return the value associated with the column: TEMPLATE_DESC
	 */
	public java.lang.String getTemplateDesc () {
		return templateDesc;
	}

	/**
	 * Set the value related to the column: TEMPLATE_DESC
	 * @param templateDesc the TEMPLATE_DESC value
	 */
	public void setTemplateDesc (java.lang.String templateDesc) {
		this.templateDesc = templateDesc;
	}



	/**
	 * Return the value associated with the column: LAST_VERSION
	 */
	public long getLastVersion () {
		return lastVersion;
	}

	/**
	 * Set the value related to the column: LAST_VERSION
	 * @param lastVersion the LAST_VERSION value
	 */
	public void setLastVersion (long lastVersion) {
		this.lastVersion = lastVersion;
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