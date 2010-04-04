/**
 * @{#} MaintainLog.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_MAINTAIN_LOG table. Do
 * not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_MAINTAIN_LOG"
 */

public class MaintainLog implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "MaintainLog";

    public static String PROP_LOG_TITLE = "logTitle";

    public static String PROP_LOG_CONTENT = "logContent";

    public static String PROP_LOG_TIME = "logTime";

    public static String PROP_LOG_ID = "logId";

    public static String PROP_LOG_TYPE = "logType";

    public static String PROP_PLAN_ID = "planId";

    public static String PROP_CREATOR = "creator";

    public MaintainLog() {

    }

    // primary key
    private long logId;

    // fields
    private long planId;

    private java.util.Date logTime;

    private java.lang.String logContent;

    private java.lang.String logTitle;

    private java.lang.String creator;

    private long logType;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="LOG_ID"
     */
    public long getLogId() {
        return logId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param logId
     *                the new ID
     */
    public void setLogId(long logId) {
        this.logId = logId;
    }

    /**
     * Return the value associated with the column: PLAN_ID
     */
    public long getPlanId() {
        return planId;
    }

    /**
     * Set the value related to the column: PLAN_ID
     * 
     * @param planId
     *                the PLAN_ID value
     */
    public void setPlanId(long planId) {
        this.planId = planId;
    }

    /**
     * Return the value associated with the column: LOG_TIME
     */
    public java.util.Date getLogTime() {
        return logTime;
    }

    /**
     * Set the value related to the column: LOG_TIME
     * 
     * @param logTime
     *                the LOG_TIME value
     */
    public void setLogTime(java.util.Date logTime) {
        this.logTime = logTime;
    }

    /**
     * Return the value associated with the column: LOG_CONTENT
     */
    public java.lang.String getLogContent() {
        return logContent;
    }

    /**
     * Set the value related to the column: LOG_CONTENT
     * 
     * @param logContent
     *                the LOG_CONTENT value
     */
    public void setLogContent(java.lang.String logContent) {
        this.logContent = logContent;
    }

    /**
     * Return the value associated with the column: LOG_TITLE
     */
    public java.lang.String getLogTitle() {
        return logTitle;
    }

    /**
     * Set the value related to the column: LOG_TITLE
     * 
     * @param logTitle
     *                the LOG_TITLE value
     */
    public void setLogTitle(java.lang.String logTitle) {
        this.logTitle = logTitle;
    }

    /**
     * Return the value associated with the column: CREATOR
     */
    public java.lang.String getCreator() {
        return creator;
    }

    /**
     * Set the value related to the column: CREATOR
     * 
     * @param creator
     *                the CREATOR value
     */
    public void setCreator(java.lang.String creator) {
        this.creator = creator;
    }

    /**
     * Return the value associated with the column: LOG_TYPE
     */
    public long getLogType() {
        return logType;
    }

    /**
     * Set the value related to the column: LOG_TYPE
     * 
     * @param logType
     *                the LOG_TYPE value
     */
    public void setLogType(long logType) {
        this.logType = logType;
    }

    public String toString() {
        return super.toString();
    }

}