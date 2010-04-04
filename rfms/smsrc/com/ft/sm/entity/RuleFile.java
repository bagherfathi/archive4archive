/**
 * @{#} RuleFile.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_RULE_FILE table. Do
 * not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_RULE_FILE"
 */

public class RuleFile implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "RuleFile";

    public static String PROP_FILE_CONTENT = "fileContent";

    public static String PROP_OPERATOR_ID = "operatorId";

    public static String PROP_PUBLISHER = "publisher";

    public static String PROP_VERSION = "version";

    public static String PROP_FILE_ID = "fileId";

    public static String PROP_UPLOAD_TIME = "uploadTime";

    public static String PROP_RULE_ID = "ruleId";

    public RuleFile() {

    }

    // primary key
    private long fileId;

    // fields
    private long ruleId;

    private byte[] fileContent;

    private long version;

    private java.util.Date uploadTime;

    private java.lang.String publisher;

    private long operatorId;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="FILE_ID"
     */
    public long getFileId() {
        return fileId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param fileId
     *                the new ID
     */
    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    /**
     * Return the value associated with the column: RULE_ID
     */
    public long getRuleId() {
        return ruleId;
    }

    /**
     * Set the value related to the column: RULE_ID
     * 
     * @param ruleId
     *                the RULE_ID value
     */
    public void setRuleId(long ruleId) {
        this.ruleId = ruleId;
    }

    /**
     * Return the value associated with the column: FILE_CONTENT
     */
    public byte[] getFileContent() {
        return fileContent;
    }

    /**
     * Set the value related to the column: FILE_CONTENT
     * 
     * @param fileContent
     *                the FILE_CONTENT value
     */
    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    /**
     * Return the value associated with the column: VERSION
     */
    public long getVersion() {
        return version;
    }

    /**
     * Set the value related to the column: VERSION
     * 
     * @param version
     *                the VERSION value
     */
    public void setVersion(long version) {
        this.version = version;
    }

    /**
     * Return the value associated with the column: UPLOAD_TIME
     */
    public java.util.Date getUploadTime() {
        return uploadTime;
    }

    /**
     * Set the value related to the column: UPLOAD_TIME
     * 
     * @param uploadTime
     *                the UPLOAD_TIME value
     */
    public void setUploadTime(java.util.Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * Return the value associated with the column: PUBLISHER
     */
    public java.lang.String getPublisher() {
        return publisher;
    }

    /**
     * Set the value related to the column: PUBLISHER
     * 
     * @param publisher
     *                the PUBLISHER value
     */
    public void setPublisher(java.lang.String publisher) {
        this.publisher = publisher;
    }

    /**
     * Return the value associated with the column: OPERATOR_ID
     */
    public long getOperatorId() {
        return operatorId;
    }

    /**
     * Set the value related to the column: OPERATOR_ID
     * 
     * @param operatorId
     *                the OPERATOR_ID value
     */
    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public String toString() {
        return super.toString();
    }

}