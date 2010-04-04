package com.ft.sm.dto;

import com.ft.sm.entity.TaskParamDefine;

/**
 * 任务参数定义DTO类.
 * 
 */
public class TaskParamDefineDTO implements DTO {

    private static final long serialVersionUID = 2376877354578117226L;

    private TaskParamDefine paramDefine;

    public TaskParamDefineDTO() {
        this.paramDefine = new TaskParamDefine();
    }

    /**
     * @return Returns the jobDefine.
     */
    public Long getJobId() {
        return new Long(this.paramDefine.getJobId());
    }

    /**
     * @param jobId
     *                The jobId to set.
     */
    public void setJobId(Long jobId) {
        this.paramDefine.setJobId(jobId.longValue());
    }

    /**
     * @return Returns the desc.
     */
    public String getDesc() {
        return this.paramDefine.getParamDesc();
    }

    /**
     * @param desc
     *                The desc to set.
     */
    public void setDesc(String desc) {
        this.paramDefine.setParamDesc(desc);
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return paramDefine.getParamName();
    }

    /**
     * @param name
     *                The name to set.
     */
    public void setName(String name) {
        paramDefine.setParamName(name);
    }

    /**
     * @return Returns the type.
     */
    public long getType() {
        return this.paramDefine.getParamType();
    }

    /**
     * @param type
     *                The type to set.
     */
    public void setType(long type) {
        this.paramDefine.setParamType(type);
    }

    /**
     * @return Returns the paramDefineId.
     */
    public Long getParamDefineId() {
        return new Long(this.paramDefine.getParamDefineid());
    }

    /**
     * @param paramDefineId
     *                The paramDefineId to set.
     */
    public void setParamDefineId(Long paramDefineId) {
        this.paramDefine.setParamDefineid(paramDefineId.longValue());
    }

    public Object getTarget() {
        return this.paramDefine;
    }

    public void setTarget(Object target) {
        this.paramDefine = (TaskParamDefine) target;

    }

}
