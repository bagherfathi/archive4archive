/**
 * @{#} PerformanceLog.java Create on 2006-10-31 13:48:10
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_PERFORMANCE_LOG table.
 * Do not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_PERFORMANCE_LOG"
 */

public class PerformanceLog implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "PerformanceLog";

    public static String PROP_END_TIME = "endTime";

    public static String PROP_START_TIME = "startTime";

    public static String PROP_PERFORMANCE_LOG_ID = "performanceLogId";

    public static String PROP_LOG_ID = "logId";

    public PerformanceLog() {

    }

    // primary key
    private long performanceLogId;

    // fields
    private long logId;

    private java.util.Date startTime;

    private java.util.Date endTime;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="PERFORMANCE_LOG_ID"
     */
    public long getPerformanceLogId() {
        return performanceLogId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param performanceLogId
     *                the new ID
     */
    public void setPerformanceLogId(long performanceLogId) {
        this.performanceLogId = performanceLogId;
    }

    /**
     * Return the value associated with the column: LOG_ID
     */
    public long getLogId() {
        return logId;
    }

    /**
     * Set the value related to the column: LOG_ID
     * 
     * @param logId
     *                the LOG_ID value
     */
    public void setLogId(long logId) {
        this.logId = logId;
    }

    /**
     * Return the value associated with the column: START_TIME
     */
    public java.util.Date getStartTime() {
        return startTime;
    }

    /**
     * Set the value related to the column: START_TIME
     * 
     * @param startTime
     *                the START_TIME value
     */
    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Return the value associated with the column: END_TIME
     */
    public java.util.Date getEndTime() {
        return endTime;
    }

    /**
     * Set the value related to the column: END_TIME
     * 
     * @param endTime
     *                the END_TIME value
     */
    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    public String toString() {
        return super.toString();
    }

}