/**
 * @{#} TaskJobParam.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_TASK_JOB_PARAM table.
 * Do not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_TASK_JOB_PARAM"
 */

public class TaskJobParam implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "TaskJobParam";

    public static String PROP_PARAM_ID = "paramId";

    public static String PROP_PARAM_VALUE = "paramValue";

    public static String PROP_PARAM_DEFINEID = "paramDefineid";

    public static String PROP_TRIGGER_ID = "triggerId";

    public TaskJobParam() {

    }

    // primary key
    private long paramId;

    // fields
    private long triggerId;

    private long paramDefineid;

    private java.lang.String paramValue;

    /**
     * Return the unique identifier of this class
     * 
     * @hibernate.id generator-class="sequence" column="PARAM_ID"
     */
    public long getParamId() {
        return paramId;
    }

    /**
     * Set the unique identifier of this class
     * 
     * @param paramId
     *                the new ID
     */
    public void setParamId(long paramId) {
        this.paramId = paramId;
    }

    /**
     * Return the value associated with the column: TRIGGER_ID
     */
    public long getTriggerId() {
        return triggerId;
    }

    /**
     * Set the value related to the column: TRIGGER_ID
     * 
     * @param triggerId
     *                the TRIGGER_ID value
     */
    public void setTriggerId(long triggerId) {
        this.triggerId = triggerId;
    }

    /**
     * Return the value associated with the column: PARAM_DEFINEID
     */
    public long getParamDefineid() {
        return paramDefineid;
    }

    /**
     * Set the value related to the column: PARAM_DEFINEID
     * 
     * @param paramDefineid
     *                the PARAM_DEFINEID value
     */
    public void setParamDefineid(long paramDefineid) {
        this.paramDefineid = paramDefineid;
    }

    /**
     * Return the value associated with the column: PARAM_VALUE
     */
    public java.lang.String getParamValue() {
        return paramValue;
    }

    /**
     * Set the value related to the column: PARAM_VALUE
     * 
     * @param paramValue
     *                the PARAM_VALUE value
     */
    public void setParamValue(java.lang.String paramValue) {
        this.paramValue = paramValue;
    }

    public String toString() {
        return super.toString();
    }

}