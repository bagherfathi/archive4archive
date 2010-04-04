/**
 * @{#} TemplateFile.java Create on 2006-12-25 8:52:57
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SM_TEMPLATE_FILE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="SM_TEMPLATE_FILE"
 */


public class TemplateFile implements Serializable {
        private static final long serialVersionUID = 1L;

	public static String REF = "TemplateFile";
	public static String PROP_FILE_CONTENT = "fileContent";
	public static String PROP_FILE_VERSION = "fileVersion";
	public static String PROP_OPERATOR_ID = "operatorId";
	public static String PROP_ORG_ID = "orgId";
	public static String PROP_LOGIN_ORG_ID = "loginOrgId";
	public static String PROP_CREATE_DATE = "createDate";
	public static String PROP_TEMPLATE_ID = "templateId";
	public static String PROP_FILE_ID = "fileId";


	public TemplateFile () {
	    
	}


	// primary key
	private long fileId;

	// fields
	private long templateId;
	private long fileVersion;
	private byte[] fileContent;
	private java.util.Date createDate;
	private long operatorId;
	private long orgId;
	private long loginOrgId;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="FILE_ID"
     */
	public long getFileId () {
		return fileId;
	}

	/**
	 * Set the unique identifier of this class
	 * @param fileId the new ID
	 */
	public void setFileId (long fileId) {
		this.fileId = fileId;
	}




	/**
	 * Return the value associated with the column: TEMPLATE_ID
	 */
	public long getTemplateId () {
		return templateId;
	}

	/**
	 * Set the value related to the column: TEMPLATE_ID
	 * @param templateId the TEMPLATE_ID value
	 */
	public void setTemplateId (long templateId) {
		this.templateId = templateId;
	}



	/**
	 * Return the value associated with the column: FILE_VERSION
	 */
	public long getFileVersion () {
		return fileVersion;
	}

	/**
	 * Set the value related to the column: FILE_VERSION
	 * @param fileVersion the FILE_VERSION value
	 */
	public void setFileVersion (long fileVersion) {
		this.fileVersion = fileVersion;
	}



	/**
	 * Return the value associated with the column: FILE_CONTENT
	 */
	public byte[] getFileContent () {
		return fileContent;
	}

	/**
	 * Set the value related to the column: FILE_CONTENT
	 * @param fileContent the FILE_CONTENT value
	 */
	public void setFileContent (byte[] fileContent) {
		this.fileContent = fileContent;
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