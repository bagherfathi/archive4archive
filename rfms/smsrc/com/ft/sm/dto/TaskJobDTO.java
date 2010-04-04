package com.ft.sm.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ft.sm.entity.TaskJob;

/**
 * 任务定义DTO类.
 * 
 * @version 1.0
 */
public class TaskJobDTO implements DTO {

    private static final long serialVersionUID = -1193465271665948377L;

    private TaskJob taskJob;

    private List paramDefines;

    public TaskJobDTO(TaskJob job) {
        this.taskJob = job;
    }

    public TaskJobDTO() {
        this.taskJob = new TaskJob();
    }

    /**
     * @return Returns the methodName.
     */
    public String getMethodName() {
        return taskJob.getJobMethodname();
    }

    /**
     * @param methodName
     *                The methodName to set.
     */
    public void setMethodName(String jobMethodname) {
        this.taskJob.setJobMethodname(jobMethodname);
    }
    
    public Long getCategoryId() {
        return new Long(taskJob.getCategoryId());
    }
    
    public void setCategoryId(Long categoryId) {
        this.taskJob.setCategoryId(categoryId.longValue());
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return taskJob.getJobName();
    }

    /**
     * @param jobName
     *                The jobName to set.
     */
    public void setName(String jobName) {
        this.taskJob.setJobName(jobName);
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return this.taskJob.getJobDesc();
    }

    /**
     * @param description
     *                The description to set.
     */
    public void setDescription(String description) {
        this.taskJob.setJobDesc(description);
    }

    /**
     * @return Returns the createTime.
     */
    public Date getCreateTime() {
        return this.getCreateTime();
    }

    /**
     * @param createTime
     *                The createTime to set.
     */
    public void setCreateTime(Date createTime) {
        this.taskJob.setJobCreatetime(createTime);
    }

    /**
     * @return Returns the jobId.
     */
    public Long getJobId() {
        return new Long(this.taskJob.getJobId());
    }

    /**
     * @param jobId
     *                The jobId to set.
     */
    public void setJobId(Long jobId) {
        this.taskJob.setJobId(jobId.longValue());
    }

    /**
     * 
     * @return Returns the className.
     */
    public String getClassName() {
        return this.taskJob.getJobClassname();
    }

    /**
     * @param className
     *                The className to set.
     */
    public void setClassName(String className) {
        this.taskJob.setJobClassname(className);
    }

    public List getParamDefines() {
        if (paramDefines == null) {
            this.paramDefines = new ArrayList();
        }
        return this.paramDefines;
    }

    /**
     * @param paramDefines
     *                The paramDefines to set.
     */
    public void setParamDefines(List paramDefines) {
        this.paramDefines = paramDefines;
    }

    public Object getTarget() {
        return this.taskJob;
    }

    public void setTarget(Object target) {
        this.taskJob = (TaskJob) target;
    }

    public TaskJob getTaskJob() {
        return this.taskJob;
    }

}
