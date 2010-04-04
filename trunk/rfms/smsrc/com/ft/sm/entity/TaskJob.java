/**
 * @{#} TaskJob.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_TASK_JOB table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * 
 * @hibernate.class table="SM_TASK_JOB"
 */

public class TaskJob implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "TaskJob";

    public static String PROP_JOB_CREATETIME = "jobCreatetime";

    public static String PROP_JOB_CLASSNAME = "jobClassname";

    public static String PROP_JOB_DESC = "jobDesc";

    public static String PROP_JOB_NAME = "jobName";

    public static String PROP_JOB_ID = "jobId";

    public static String PROP_JOB_METHODNAME = "jobMethodname";

    public static String PROP_CATEGROY_ID = "categoryId";

    public TaskJob() {

    }

    // primary key
    private long jobId;

    // fields
    private long categoryId;

    private java.lang.String jobName;

    private java.lang.String jobDesc;

    private java.lang.String jobMethodname;

    private java.lang.String jobClassname;

    private java.util.Date jobCreatetime;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="JOB_ID"
     */
    public long getJobId() {
        return jobId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param jobId
     *            the new ID
     */
    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    /**
     * Return the value associated with the column: JOB_NAME
     */
    public java.lang.String getJobName() {
        return jobName;
    }

    /**
     * Set the value related to the column: JOB_NAME
     * 
     * @param jobName
     *            the JOB_NAME value
     */
    public void setJobName(java.lang.String jobName) {
        this.jobName = jobName;
    }

    /**
     * Return the value associated with the column: JOB_DESC
     */
    public java.lang.String getJobDesc() {
        return jobDesc;
    }

    /**
     * Set the value related to the column: JOB_DESC
     * 
     * @param jobDesc
     *            the JOB_DESC value
     */
    public void setJobDesc(java.lang.String jobDesc) {
        this.jobDesc = jobDesc;
    }

    /**
     * Return the value associated with the column: JOB_METHODNAME
     */
    public java.lang.String getJobMethodname() {
        return jobMethodname;
    }

    /**
     * Set the value related to the column: JOB_METHODNAME
     * 
     * @param jobMethodname
     *            the JOB_METHODNAME value
     */
    public void setJobMethodname(java.lang.String jobMethodname) {
        this.jobMethodname = jobMethodname;
    }

    /**
     * Return the value associated with the column: JOB_CLASSNAME
     */
    public java.lang.String getJobClassname() {
        return jobClassname;
    }

    /**
     * Set the value related to the column: JOB_CLASSNAME
     * 
     * @param jobClassname
     *            the JOB_CLASSNAME value
     */
    public void setJobClassname(java.lang.String jobClassname) {
        this.jobClassname = jobClassname;
    }

    /**
     * Return the value associated with the column: JOB_CREATETIME
     */
    public java.util.Date getJobCreatetime() {
        return jobCreatetime;
    }

    /**
     * Set the value related to the column: JOB_CREATETIME
     * 
     * @param jobCreatetime
     *            the JOB_CREATETIME value
     */
    public void setJobCreatetime(java.util.Date jobCreatetime) {
        this.jobCreatetime = jobCreatetime;
    }

    /**
     * Return the value associated with the column: CATEGORY_ID
     */
    public long getCategoryId() {
        return categoryId;
    }

    /**
     * Set the value related to the column: CATEGORY_ID
     * 
     * @param categoryId
     *            the CATEGORY_ID value
     */
    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String toString() {
        return super.toString();
    }

}