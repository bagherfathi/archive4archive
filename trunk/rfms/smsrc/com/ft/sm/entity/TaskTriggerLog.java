package com.ft.sm.entity;

import java.io.Serializable;

/**
 * This is an object that contains data related to the SM_TASK_TRIGGER table. Do
 * not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="SM_TASK_TRIGGER_LOG"
 */

public class TaskTriggerLog implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String REF = "TaskTriggerLog";

    public static String PROP_LOG_ID = "logId";

    public static String PROP_APP_ID = "appId";

    public static String PROP_TRIGGER_ID = "triggerId";

    public static String PROP_OPER_DATE = "operDate";

    public static String PROP_OPERATOR_ID = "operatorId";

    public static String PROP_MARK = "mark";

    public TaskTriggerLog() {

    }

    // primary key
    private long logId;

    // fields
    private long appId;

    private long triggerId;

    private java.util.Date operDate;

    private long operatorId;
    
    private String mark;
    
    private long triggerStart;
    
    private long actionResult;

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public long getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(long triggerId) {
        this.triggerId = triggerId;
    }

    public java.util.Date getOperDate() {
        return operDate;
    }

    public void setOperDate(java.util.Date operDate) {
        this.operDate = operDate;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
    
    public long getTriggerStart() {
        return triggerStart;
    }

    public void setTriggerStart(long triggerStart) {
        this.triggerStart = triggerStart;
    }

    public long getActionResult() {
        return actionResult;
    }

    public void setActionResult(long actionResult) {
        this.actionResult = actionResult;
    }

    public String toString() {
        return super.toString();
    }

}
