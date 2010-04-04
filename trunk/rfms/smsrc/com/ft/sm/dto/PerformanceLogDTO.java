package com.ft.sm.dto;

import java.util.Date;

import com.ft.sm.entity.OperatorLog;
import com.ft.sm.entity.PerformanceLog;

/**
 * ������־ʵ���װ�ࡣ
 * 
 * @version 1.0
 */
public class PerformanceLogDTO extends OperatorLogDTO {
    private static final long serialVersionUID = 6968733616265453519L;

    private PerformanceLog performanceLog;

    public PerformanceLogDTO() {
        this.log = new OperatorLog();
        this.performanceLog = new PerformanceLog();
    }

    public PerformanceLogDTO(OperatorLog log, PerformanceLog performanceLog) {
        this.log = log;
        this.performanceLog = performanceLog;
    }

    /**
     * ��������ʱ�䡣
     * 
     * @return
     */
    public Date getEndTime() {
        return this.performanceLog.getEndTime();
    }

    public void setEndTime(Date endTime) {
        this.performanceLog.setEndTime(endTime);
    }

    /**
     * ������ʼʱ�䡣
     * 
     * @return
     */
    public Date getStartTime() {
        return this.performanceLog.getStartTime();
    }

    public void setStartTime(Date startTime) {
        this.performanceLog.setStartTime(startTime);
    }

    /**
     * ����������־ID��
     */
    public Long getLogId() {
        return new Long(this.performanceLog.getLogId());
    }

    public void setLogId(Long logId) {
        this.performanceLog.setLogId(logId.longValue());
    }

    /**
     * ������־ID��
     * 
     * @return
     */
    public Long getPerformanceLogId() {
        return new Long(this.performanceLog.getPerformanceLogId());
    }

    public void setPerformanceLogId(Long performanceLogId) {
        this.performanceLog.setPerformanceLogId(performanceLogId.longValue());
    }

    /**
     * @return the performanceLog
     */
    public PerformanceLog getPerformanceLog() {
        return performanceLog;
    }

    /**
     * 
     * @return the log
     */
    public OperatorLog getOperatorLog() {
        return log;
    }
}
