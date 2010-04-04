package com.ft.sm.dto;

import com.ft.sm.entity.TaskJob;
import com.ft.sm.entity.TaskTrigger;
import com.ft.sm.entity.TaskTriggerLog;

public class TaskTriggerLogDTO {
    
    private TaskTrigger trigger;

    private TaskJob taskJob;
    
    private TaskTriggerLog triggerLog;
    
    public static long ACTION_SUCCESS = 1; //启动成功
    
    public static long ACTION_FAIL = 0; //启动失败
    
    public TaskTriggerLogDTO() {
        this.trigger = new TaskTrigger();
        this.taskJob = new TaskJob();
        this.triggerLog = new TaskTriggerLog();
    }
    
    public TaskTriggerLogDTO(TaskTriggerLog triggerLog,TaskTrigger trigger, TaskJob taskJob) {
        this.triggerLog = triggerLog;
        this.trigger = trigger;
        this.taskJob = taskJob;
    }

    public TaskTrigger getTrigger() {
        return trigger;
    }

    public void setTrigger(TaskTrigger trigger) {
        this.trigger = trigger;
    }

    public TaskJob getTaskJob() {
        return taskJob;
    }

    public void setTaskJob(TaskJob taskJob) {
        this.taskJob = taskJob;
    }

    public TaskTriggerLog getTriggerLog() {
        return triggerLog;
    }

    public void setTriggerLog(TaskTriggerLog triggerLog) {
        this.triggerLog = triggerLog;
    }
    
}
