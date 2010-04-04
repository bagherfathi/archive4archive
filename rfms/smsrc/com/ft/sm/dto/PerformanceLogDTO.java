package com.ft.sm.dto;

import java.util.Date;

import com.ft.sm.entity.OperatorLog;
import com.ft.sm.entity.PerformanceLog;

/**
 * 性能日志实体封装类。
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
     * 操作结束时间。
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
     * 操作开始时间。
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
     * 所属操作日志ID。
     */
    public Long getLogId() {
        return new Long(this.performanceLog.getLogId());
    }

    public void setLogId(Long logId) {
        this.performanceLog.setLogId(logId.longValue());
    }

    /**
     * 性能日志ID。
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
