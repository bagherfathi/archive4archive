package com.ft.sm.dto;

import com.ft.sm.entity.TaskJobParam;
import com.ft.sm.entity.TaskParamDefine;

/**
 * 任务参数DTO类.
 * 
 */
public class TaskJobParamDTO implements DTO {

    private static final long serialVersionUID = 2694886497252410669L;

    private TaskJobParam jobParam;

    private TaskParamDefine paramDefine;

    public TaskJobParamDTO(TaskJobParam jobParam, TaskParamDefine paramDefine) {
        this.jobParam = jobParam;
        this.paramDefine = paramDefine;
    }

    /**
     * @return Returns the paramId.
     */
    public Long getParamId() {
        return new Long(this.jobParam.getParamId());
    }

    /**
     * @param paramId
     *                The paramId to set.
     */
    public void setParamId(Long paramId) {
        this.jobParam.setParamId(paramId.longValue());
    }

    /**
     * @return Returns the paramDefine.
     */
    public TaskParamDefine getParamDefine() {
        return paramDefine;
    }

    /**
     * @param taskJobParamDefine
     *                The taskJobParamDefine to set.
     */
    public void setParamDefine(TaskParamDefine taskJobParamDefine) {
        this.paramDefine = taskJobParamDefine;
    }

    /**
     * @hibernate.property column = "trigger_id"
     * 
     * @return Returns the taskTrigger.
     */
    public Long getTaskTriggerId() {
        return new Long(this.jobParam.getTriggerId());
    }

    /**
     * @param triggerId
     *                The triggerId to set.
     */
    public void setTaskTriggerId(Long triggerId) {
        this.jobParam.setTriggerId(triggerId.longValue());
    }

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return this.jobParam.getParamValue();
    }

    /**
     * @param value
     *                The value to set.
     */
    public void setValue(String value) {
        this.jobParam.setParamValue(value);
    }

    public Object getTarget() {
        return this.jobParam;
    }

    public void setTarget(Object target) {
        this.jobParam = (TaskJobParam) target;
    }

}
