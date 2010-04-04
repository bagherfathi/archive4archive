/**
 * @{#} TaskTrigger.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_TASK_TRIGGER table. Do
 * not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_TASK_TRIGGER"
 */

public class TaskTrigger implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "TaskTrigger";

    public static String PROP_TRIGGER_STATUS = "triggerStatus";

    public static String PROP_TRIGGER_CRONTYPE = "triggerCrontype";

    public static String PROP_TRIGGER_DESC = "triggerDesc";

    public static String PROP_JOB_ID = "jobId";

    public static String PROP_TRIGGER_CRON = "triggerCron";

    public static String PROP_TRIGGER_START = "triggerStart";

    public static String PROP_TRIGGER_ID = "triggerId";

    public TaskTrigger() {

    }

    // primary key
    private long triggerId;

    // fields
    private long jobId;

    private java.lang.String triggerCron;

    private long triggerCrontype;

    private java.lang.String triggerDesc;

    private long triggerStatus;

    private long triggerStart;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="TRIGGER_ID"
     */
    public long getTriggerId() {
        return triggerId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param triggerId
     *                the new ID
     */
    public void setTriggerId(long triggerId) {
        this.triggerId = triggerId;
    }

    /**
     * Return the value associated with the column: JOB_ID
     */
    public long getJobId() {
        return jobId;
    }

    /**
     * Set the value related to the column: JOB_ID
     * 
     * @param jobId
     *                the JOB_ID value
     */
    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    /**
     * Return the value associated with the column: TRIGGER_CRON
     */
    public java.lang.String getTriggerCron() {
        return triggerCron;
    }

    /**
     * Set the value related to the column: TRIGGER_CRON
     * 
     * @param triggerCron
     *                the TRIGGER_CRON value
     */
    public void setTriggerCron(java.lang.String triggerCron) {
        this.triggerCron = triggerCron;
    }

    /**
     * Return the value associated with the column: TRIGGER_CRONTYPE
     */
    public long getTriggerCrontype() {
        return triggerCrontype;
    }

    /**
     * Set the value related to the column: TRIGGER_CRONTYPE
     * 
     * @param triggerCrontype
     *                the TRIGGER_CRONTYPE value
     */
    public void setTriggerCrontype(long triggerCrontype) {
        this.triggerCrontype = triggerCrontype;
    }

    /**
     * Return the value associated with the column: TRIGGER_DESC
     */
    public java.lang.String getTriggerDesc() {
        return triggerDesc;
    }

    /**
     * Set the value related to the column: TRIGGER_DESC
     * 
     * @param triggerDesc
     *                the TRIGGER_DESC value
     */
    public void setTriggerDesc(java.lang.String triggerDesc) {
        this.triggerDesc = triggerDesc;
    }

    /**
     * Return the value associated with the column: TRIGGER_STATUS
     */
    public long getTriggerStatus() {
        return triggerStatus;
    }

    /**
     * Set the value related to the column: TRIGGER_STATUS
     * 
     * @param triggerStatus
     *                the TRIGGER_STATUS value
     */
    public void setTriggerStatus(long triggerStatus) {
        this.triggerStatus = triggerStatus;
    }

    /**
     * Return the value associated with the column: TRIGGER_START
     */
    public long getTriggerStart() {
        return triggerStart;
    }

    /**
     * Set the value related to the column: TRIGGER_START
     * 
     * @param triggerStart
     *                the TRIGGER_START value
     */
    public void setTriggerStart(long triggerStart) {
        this.triggerStart = triggerStart;
    }

    public String toString() {
        return super.toString();
    }

}