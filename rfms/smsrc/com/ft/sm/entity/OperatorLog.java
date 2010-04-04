/**
 * @{#} OperatorLog.java Create on 2006-10-27 14:46:43
 *
 * Copyright (c) 2006 by WASU.
 */

package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_OPERATOR_LOG table. Do
 * not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_OPERATOR_LOG"
 */

public class OperatorLog implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "OperatorLog";

    public static String PROP_REMOTE_HOST = "remoteHost";

    public static String PROP_ACTION_SEQUENCE = "actionSequence";

    public static String PROP_SUB_SYS_CODE = "subSysCode";

    public static String PROP_OPER_LOGIN_NAME = "operLoginName";

    public static String PROP_ACTION_CODE = "actionCode";

    public static String PROP_ACTION_CATEGROY = "actionCategroy";

    public static String PROP_LOG_ID = "logId";

    public static String PROP_OPER_NAME = "operName";

    public static String PROP_INSERT_TIME = "insertTime";

    public static String PROP_OPER_ORG_NAME = "operOrgName";

    public static String PROP_RESULT_CODE = "resultCode";

    public static String PROP_MODEL_NAME = "modelName";

    public OperatorLog() {

    }

    // primary key
    private long logId;

    // fields
    private java.util.Date insertTime;

    private java.lang.String operName;

    private java.lang.String operLoginName;

    private java.lang.String operOrgName;

    private java.lang.String remoteHost;

    private java.lang.String subSysCode;

    private java.lang.String modelName;

    private java.lang.String actionCategroy;

    private java.lang.String actionCode;

    private java.lang.String actionSequence;

    private java.lang.String resultCode;

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
     * Return the value associated with the column: INSERT_TIME
     */
    public java.util.Date getInsertTime() {
        return insertTime;
    }

    /**
     * Set the value related to the column: INSERT_TIME
     * 
     * @param insertTime
     *                the INSERT_TIME value
     */
    public void setInsertTime(java.util.Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * Return the value associated with the column: OPER_NAME
     */
    public java.lang.String getOperName() {
        return operName;
    }

    /**
     * Set the value related to the column: OPER_NAME
     * 
     * @param operName
     *                the OPER_NAME value
     */
    public void setOperName(java.lang.String operName) {
        this.operName = operName;
    }

    /**
     * Return the value associated with the column: OPER_LOGIN_NAME
     */
    public java.lang.String getOperLoginName() {
        return operLoginName;
    }

    /**
     * Set the value related to the column: OPER_LOGIN_NAME
     * 
     * @param operLoginName
     *                the OPER_LOGIN_NAME value
     */
    public void setOperLoginName(java.lang.String operLoginName) {
        this.operLoginName = operLoginName;
    }

    /**
     * Return the value associated with the column: OPER_ORG_NAME
     */
    public java.lang.String getOperOrgName() {
        return operOrgName;
    }

    /**
     * Set the value related to the column: OPER_ORG_NAME
     * 
     * @param operOrgName
     *                the OPER_ORG_NAME value
     */
    public void setOperOrgName(java.lang.String operOrgName) {
        this.operOrgName = operOrgName;
    }

    /**
     * Return the value associated with the column: REMOTE_HOST
     */
    public java.lang.String getRemoteHost() {
        return remoteHost;
    }

    /**
     * Set the value related to the column: REMOTE_HOST
     * 
     * @param remoteHost
     *                the REMOTE_HOST value
     */
    public void setRemoteHost(java.lang.String remoteHost) {
        this.remoteHost = remoteHost;
    }

    /**
     * Return the value associated with the column: SUB_SYS_CODE
     */
    public java.lang.String getSubSysCode() {
        return subSysCode;
    }

    /**
     * Set the value related to the column: SUB_SYS_CODE
     * 
     * @param subSysCode
     *                the SUB_SYS_CODE value
     */
    public void setSubSysCode(java.lang.String subSysCode) {
        this.subSysCode = subSysCode;
    }

    /**
     * Return the value associated with the column: MODEL_NAME
     */
    public java.lang.String getModelName() {
        return modelName;
    }

    /**
     * Set the value related to the column: MODEL_NAME
     * 
     * @param modelName
     *                the MODEL_NAME value
     */
    public void setModelName(java.lang.String modelName) {
        this.modelName = modelName;
    }

    /**
     * Return the value associated with the column: ACTION_CATEGROY
     */
    public java.lang.String getActionCategroy() {
        return actionCategroy;
    }

    /**
     * Set the value related to the column: ACTION_CATEGROY
     * 
     * @param actionCategroy
     *                the ACTION_CATEGROY value
     */
    public void setActionCategroy(java.lang.String actionCategroy) {
        this.actionCategroy = actionCategroy;
    }

    /**
     * Return the value associated with the column: ACTION_CODE
     */
    public java.lang.String getActionCode() {
        return actionCode;
    }

    /**
     * Set the value related to the column: ACTION_CODE
     * 
     * @param actionCode
     *                the ACTION_CODE value
     */
    public void setActionCode(java.lang.String actionCode) {
        this.actionCode = actionCode;
    }

    /**
     * Return the value associated with the column: ACTION_SEQUENCE
     */
    public java.lang.String getActionSequence() {
        return actionSequence;
    }

    /**
     * Set the value related to the column: ACTION_SEQUENCE
     * 
     * @param actionSequence
     *                the ACTION_SEQUENCE value
     */
    public void setActionSequence(java.lang.String actionSequence) {
        this.actionSequence = actionSequence;
    }

    /**
     * Return the value associated with the column: RESULT_CODE
     */
    public java.lang.String getResultCode() {
        return resultCode;
    }

    /**
     * Set the value related to the column: RESULT_CODE
     * 
     * @param resultCode
     *                the RESULT_CODE value
     */
    public void setResultCode(java.lang.String resultCode) {
        this.resultCode = resultCode;
    }

    public String toString() {
        return super.toString();
    }

}