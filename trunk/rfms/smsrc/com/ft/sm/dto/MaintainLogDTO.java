package com.ft.sm.dto;

import java.util.Date;

import com.ft.sm.entity.MaintainLog;

/**
 * 维护日志实体封装类。
 * 
 */
public class MaintainLogDTO implements DTO {
    private static final long serialVersionUID = 9011511022918727080L;

    private MaintainLog log;

    /**
     * 默认构造器。
     * 
     */
    public MaintainLogDTO() {
        this.log = new MaintainLog();
    }

    public MaintainLogDTO(MaintainLog log) {
        this.log = log;
    }

    /**
     * 完整构造器。
     * 
     * @param title
     * @param content
     * @param creator
     * @param type
     * @param logTime
     * @param planId
     */
    public MaintainLogDTO(String title, String content, String creator,
            long type, Date logTime, Long planId) {
        this.log = new MaintainLog();
        this.log.setLogTitle(title);
        this.log.setLogContent(content);
        this.log.setCreator(creator);
        this.log.setLogType(type);
        this.log.setLogTime(logTime);
        this.log.setPlanId(planId.longValue());
    }

    /**
     * 日志内容。
     * 
     * @return Returns the content.
     */
    public String getContent() {
        return this.log.getLogContent();
    }

    /**
     * @param content
     *                The content to set.
     */
    public void setContent(String content) {
        this.log.setLogContent(content);
    }

    /**
     * 日志作者。
     * 
     * @return Returns the creator.
     */
    public String getCreator() {
        return this.log.getCreator();
    }

    /**
     * @param creator
     *                The creator to set.
     */
    public void setCreator(String creator) {
        this.log.setCreator(creator);
    }

    /**
     * 系统维护日志ID。
     * 
     * @return Returns the logId.
     */
    public Long getLogId() {
        return new Long(this.log.getLogId());
    }

    /**
     * @param logId
     *                The logId to set.
     */
    public void setLogId(Long logId) {
        this.log.setLogId(logId.longValue());
    }

    /**
     * 日志时间。
     * 
     * @return Returns the logTime.
     */
    public Date getLogTime() {
        return this.log.getLogTime();
    }

    /**
     * @param logTime
     *                The logTime to set.
     */
    public void setLogTime(Date logTime) {
        this.log.setLogTime(logTime);
    }

    /**
     * 日志标题。
     * 
     * @return Returns the title.
     */
    public String getTitle() {
        return this.log.getLogTitle();
    }

    /**
     * @param title
     *                The title to set.
     */
    public void setTitle(String title) {
        this.log.setLogTitle(title);
    }

    /**
     * 日志类型。
     * 
     * @return Returns the type.
     */
    public long getType() {
        return this.log.getLogType();
    }

    /**
     * @param type
     *                The type to set.
     */
    public void setType(long type) {
        this.log.setLogType(type);
    }

    /**
     * 系统维护计划ID。
     * <p>
     * 记录对应的系统维护计划的ID，但并不产生关联关系。
     * </p>
     * 
     * @return Returns the planId.
     */
    public Long getPlanId() {
        return new Long(this.log.getPlanId());
    }

    /**
     * @param planId
     *                The planId to set.
     */
    public void setPlanId(Long planId) {
        this.log.setPlanId(planId.longValue());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.log;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.log = (MaintainLog) target;
    }
}
