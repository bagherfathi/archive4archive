/**
 * @{#} TaskParamDefine.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_TASK_PARAM_DEFINE
 * table. Do not modify this class because it will be overwritten if the
 * configuration file related to this class is modified.
 * 
 * @hibernate.class table="SM_TASK_PARAM_DEFINE"
 */

public class TaskParamDefine implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "TaskParamDefine";

    public static String PROP_PARAM_NAME = "paramName";

    public static String PROP_JOB_ID = "jobId";

    public static String PROP_PARAM_TYPE = "paramType";

    public static String PROP_PARAM_DESC = "paramDesc";

    public static String PROP_PARAM_DEFINEID = "paramDefineid";

    public TaskParamDefine() {

    }

    // primary key
    private long paramDefineid;

    // fields
    private long jobId;

    private java.lang.String paramName;

    private long paramType;

    private java.lang.String paramDesc;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="PARAM_DEFINEID"
     */
    public long getParamDefineid() {
        return paramDefineid;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param paramDefineid
     *                the new ID
     */
    public void setParamDefineid(long paramDefineid) {
        this.paramDefineid = paramDefineid;
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
     * Return the value associated with the column: PARAM_NAME
     */
    public java.lang.String getParamName() {
        return paramName;
    }

    /**
     * Set the value related to the column: PARAM_NAME
     * 
     * @param paramName
     *                the PARAM_NAME value
     */
    public void setParamName(java.lang.String paramName) {
        this.paramName = paramName;
    }

    /**
     * Return the value associated with the column: PARAM_TYPE
     */
    public long getParamType() {
        return paramType;
    }

    /**
     * Set the value related to the column: PARAM_TYPE
     * 
     * @param paramType
     *                the PARAM_TYPE value
     */
    public void setParamType(long paramType) {
        this.paramType = paramType;
    }

    /**
     * Return the value associated with the column: PARAM_DESC
     */
    public java.lang.String getParamDesc() {
        return paramDesc;
    }

    /**
     * Set the value related to the column: PARAM_DESC
     * 
     * @param paramDesc
     *                the PARAM_DESC value
     */
    public void setParamDesc(java.lang.String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public String toString() {
        return super.toString();
    }

}