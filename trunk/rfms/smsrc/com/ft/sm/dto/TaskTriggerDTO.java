package com.ft.sm.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ft.sm.entity.TaskJob;
import com.ft.sm.entity.TaskTrigger;

/**
 * 任务规则DTO类.
 * 
 */
public class TaskTriggerDTO implements DTO {

    private static final long serialVersionUID = 1L;

    private TaskTrigger trigger;

    private TaskJob taskJob;

    private List params;

    public static final long TRIGGER_STATUS_RUN = 2;

    public static final long TRIGGER_STATUS_PAUSE = 1;

    public static final long TRIGGER_START = 1;
    
    public static final long TRIGGER_STOP = 0;
    
    public static final long TRIGGER_MISS_FIRE = 2; // 规则已过期，不会被触发
    
    private Integer runCount;  //运行次数
    
    private Date lastRunTime; //最后运行时间

    public TaskTriggerDTO() {
        this.trigger = new TaskTrigger();
        this.taskJob = new TaskJob();
    }

    public TaskTriggerDTO(TaskTrigger trigger, TaskJob taskJob) {
        this.trigger = trigger;
        this.taskJob = taskJob;
    }
    
    public TaskTriggerDTO(Long triggerId,Integer runCount,Date lastRunTime) {
        trigger = new TaskTrigger();
        this.setTriggerId(triggerId);
        this.runCount = runCount;
        this.lastRunTime = lastRunTime;
    }

    public TaskTriggerDTO(TaskTrigger trigger) {
        this.trigger = trigger;
    }

    public List getParams() {
        if (this.params == null) {
            this.params = new ArrayList();
        }
        return this.params;
    }

    public TaskTrigger getTaskTrigger() {
        return this.trigger;
    }

    /**
     * @return Returns the needStartup.
     */
    public long getNeedStartup() {
        return this.trigger.getTriggerStart();
    }

    /**
     * @param needStartup
     *                The needStartup to set.
     */
    public void setNeedStartup(long needStartup) {
        this.trigger.setTriggerStart(needStartup);
    }

    /**
     * @return Returns the status.
     */
    public Long getStatus() {
        return new Long(this.trigger.getTriggerStatus());
    }

    /**
     * @param status
     *                The status to set.
     */
    public void setStatus(long status) {
        this.trigger.setTriggerStatus(status);
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return this.trigger.getTriggerDesc();
    }

    /**
     * @param description
     *                The description to set.
     */
    public void setDescription(String description) {
        this.trigger.setTriggerDesc(description);
    }

    /**
     * @return Returns the Id.
     */
    public Long getTriggerId() {
        return new Long(this.trigger.getTriggerId());
    }

    /**
     * @param id
     *                The id to set.
     */
    public void setTriggerId(Long id) {
        this.trigger.setTriggerId(id.longValue());
    }

    /**
     * @return Returns the cronString.
     */
    public String getCronString() {
        return this.trigger.getTriggerCron();
    }

    /**
     * @param cronString
     *                The cronString to set.
     */
    public void setCronString(String cronString) {
        this.trigger.setTriggerCron(cronString);
    }

    /**
     * @hibernate.property column = "trigger_cronType"
     * @return Returns the cronType.
     */
    public long getCronType() {
        return trigger.getTriggerCrontype();
    }

    /**
     * @param cronType
     *                The cronType to set.
     */
    public void setCronType(long cronType) {
        this.trigger.setTriggerCrontype(cronType);
    }

    /**
     * @return Returns the jobDefine.
     */
    public TaskJob getTaskJob() {
        return this.taskJob;
    }

    /**
     * @param job
     *                The jobDefine to set.
     */
    public void setTaskJob(TaskJob job) {
        this.taskJob = job;
        if (trigger.getJobId() == 0) {
            this.trigger.setJobId(job.getJobId());
        }
    }

    public long getJobId() {
        return this.trigger.getJobId();
    }

    public Object getTarget() {
        return this.trigger;
    }

    public void setTarget(Object target) {
        this.trigger = (TaskTrigger) target;
    }

    /**
     * @return the lastRunTime
     */
    public Date getLastRunTime() {
        return lastRunTime;
    }

    /**
     * @param lastRunTime the lastRunTime to set
     */
    public void setLastRunTime(Date lastRunTime) {
        this.lastRunTime = lastRunTime;
    }

    /**
     * @return the runCount
     */
    public Integer getRunCount() {
        return runCount;
    }

    /**
     * @param runCount the runCount to set
     */
    public void setRunCount(Integer runCount) {
        this.runCount = runCount;
    }
    
    

}
