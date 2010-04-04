package com.ft.web.sm.task;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ft.common.task.corn.CronEntity;
import com.ft.sm.dto.TaskJobDTO;
import com.ft.sm.dto.TaskTriggerDTO;
import com.ft.web.sm.BaseValidatorForm;

/**
 * 任务管理Form类
 * 
 * 
 * @struts.form name="taskForm"
 */
public class TaskForm extends BaseValidatorForm {
    /** <code> serialVersionUID </code> . */

    private static final long serialVersionUID = 1784993062642150619L;

    /**
     * 相当于一个任务中的Job.
     */
    private TaskJobDTO jobDefine;

    /**
     * 相当于一个任务中的trigger.
     */
    private TaskTriggerDTO taskTrigger;

    /**
     * 运行规则的基类.
     */
    private CronEntity cronEntity;

    /**
     * 运行规则的类型.
     */
    private long cronType;
    
    private String jobName;
    
    private long triggerStatus;
    
    private Long categoryId;
    
    /**
     * @return the categoryId
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the jobName
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * @param jobName the jobName to set
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * @return the triggerStatus
     */
    public long getTriggerStatus() {
        return triggerStatus;
    }

    /**
     * @param triggerStatus the triggerStatus to set
     */
    public void setTriggerStatus(long triggerStatus) {
        this.triggerStatus = triggerStatus;
    }

    /**
     * @struts.entity-field initial="triggerId"
     * @return The taskTrigger
     */
    public TaskTriggerDTO getTaskTrigger() {
        return taskTrigger;
    }

    /**
     * @param taskTrigger
     *                The taskTrigger to set.
     */
    public void setTaskTrigger(TaskTriggerDTO taskTrigger) {
        this.taskTrigger = taskTrigger;
    }

    /**
     * @struts.entity-field initial="jobId"
     * @return The jobDefine
     */
    public TaskJobDTO getJobDefine() {
        return jobDefine;
    }

    /**
     * @param taskJob
     *                The jobDefine to set.
     */
    public void setJobDefine(TaskJobDTO jobDefine) {
        this.jobDefine = jobDefine;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        if (jobDefine == null) {
            jobDefine = new TaskJobDTO();
        }

        if (taskTrigger == null) {
            taskTrigger = new TaskTriggerDTO();
        }
        super.reset(mapping, request);
    }

    /**
     * @return Returns the cronEntity.
     */
    public CronEntity getCronEntity() {
        return cronEntity;
    }

    /**
     * @param cronEntity
     *                The cronEntity to set.
     */
    public void setCronEntity(CronEntity cronEntity) {
        this.cronEntity = cronEntity;
    }

    /**
     * @return Returns the cronType.
     */
    public long getCronType() {
        return cronType;
    }

    /**
     * @param cronType
     *                The cronType to set.
     */
    public void setCronType(long cronType) {
        this.cronType = cronType;
    }
}
