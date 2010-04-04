package com.ft.sm.dto;

import com.ft.sm.entity.TaskTriggerHis;

/**
 * 任务规则历史DTO类.
 * 
 */
public class TaskTriggerHisDTO implements DTO {

    private static final long serialVersionUID = 1L;

    private TaskTriggerHis triggerHis;

    public TaskTriggerHisDTO() {
        this.triggerHis = new TaskTriggerHis();
    }

    public TaskTriggerHisDTO(TaskTriggerHis triggerHis) {
        this.triggerHis = triggerHis;
    }

    public long getTriggerId() {
        return this.triggerHis.getTriggerId();
    }

    public void setTriggerId(long triggerId) {
        this.triggerHis.setTriggerId(triggerId);
    }

    public long getTaskId() {
        return this.triggerHis.getTaskId();
    }

    public void setTaskId(long taskId) {
        this.triggerHis.setTaskId(taskId);
    }

    public java.lang.String getTaskName() {
        return this.triggerHis.getTaskName();
    }

    public void setTaskName(java.lang.String taskName) {
        this.triggerHis.setTaskName(taskName);
    }

    public java.lang.String getClassName() {
        return this.triggerHis.getClassName();
    }

    public void setClassName(java.lang.String className) {
        this.triggerHis.setClassName(className);
    }

    public java.lang.String getMethodName() {
        return this.triggerHis.getMethodName();
    }

    public void setMethodName(java.lang.String methodName) {
        this.triggerHis.setMethodName(methodName);
    }

    public java.lang.String getTriggerDesc() {
        return this.triggerHis.getTriggerDesc();
    }

    public void setTriggerDesc(java.lang.String triggerDesc) {
        this.triggerHis.setTriggerDesc(triggerDesc);
    }

    public java.util.Date getEndTime() {
        return this.triggerHis.getEndTime();
    }

    public void setEndTime(java.util.Date endTime) {
        this.triggerHis.setEndTime(endTime);
    }

    public java.util.Date getStartTime() {
        return this.triggerHis.getStartTime();
    }

    public void setStartTime(java.util.Date startTime) {
        this.triggerHis.setStartTime(startTime);
    }

    public Object getTarget() {
        return this.triggerHis;
    }

    public void setTarget(Object target) {
        this.triggerHis = (TaskTriggerHis) target;

    }
    public TaskTriggerHis getTaskTrigger() {
        return this.triggerHis;
    }

}
