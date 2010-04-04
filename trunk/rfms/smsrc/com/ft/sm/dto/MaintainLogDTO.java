package com.ft.sm.dto;

import java.util.Date;

import com.ft.sm.entity.MaintainLog;

/**
 * ά����־ʵ���װ�ࡣ
 * 
 */
public class MaintainLogDTO implements DTO {
    private static final long serialVersionUID = 9011511022918727080L;

    private MaintainLog log;

    /**
     * Ĭ�Ϲ�������
     * 
     */
    public MaintainLogDTO() {
        this.log = new MaintainLog();
    }

    public MaintainLogDTO(MaintainLog log) {
        this.log = log;
    }

    /**
     * ������������
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
     * ��־���ݡ�
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
     * ��־���ߡ�
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
     * ϵͳά����־ID��
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
     * ��־ʱ�䡣
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
     * ��־���⡣
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
     * ��־���͡�
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
     * ϵͳά���ƻ�ID��
     * <p>
     * ��¼��Ӧ��ϵͳά���ƻ���ID������������������ϵ��
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
