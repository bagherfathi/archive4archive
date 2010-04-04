package com.ft.sm.dto;

import java.util.Date;

import com.ft.sm.entity.OperatorLog;

/**
 * 操作日志实体封装类。
 * 
 * @version 1.0
 */
public class OperatorLogDTO implements DTO {
    private static final long serialVersionUID = 177675369422364915L;

    protected OperatorLog log;

    public OperatorLogDTO() {
        this.log = new OperatorLog();
    }

    public OperatorLogDTO(OperatorLog log) {
        this.log = log;
    }

    /**
     * 操作类别。
     * 
     * @return
     */
    public String getActionCategory() {
        return this.log.getActionCategroy();
    }

    public void setActionCategory(String actionCategory) {
        this.log.setActionCategroy(actionCategory);
    }

    /**
     * 操作代码。
     * 
     * @return
     */
    public String getActionCode() {
        return this.log.getActionCode();
    }

    public void setActionCode(String actionCode) {
        this.log.setActionCode(actionCode);
    }

    /**
     * 操作流水号。
     * 
     * @return
     */
    public String getActionSeq() {
        return this.log.getActionSequence();
    }

    public void setActionSeq(String actionSeq) {
        this.log.setActionSequence(actionSeq);
    }

    /**
     * 操作日志ID。
     * 
     * @return Returns the id.
     */
    public Long getLogId() {
        return new Long(this.log.getLogId());
    }

    public void setLogId(Long logId) {
        this.log.setLogId(logId.longValue());
    }

    /**
     * 模块名称。
     * 
     * @return
     */
    public String getModelName() {
        return this.log.getModelName();
    }

    public void setModelName(String modelName) {
        this.log.setModelName(modelName);
    }

    /**
     * 操作员登陆名称。
     * 
     * @return
     */
    public String getOperatorLoginName() {
        return this.log.getOperLoginName();
    }

    public void setOperatorLoginName(String operatorLoginName) {
        this.log.setOperLoginName(operatorLoginName);
    }

    /**
     * 操作员名称。
     * 
     * @return
     */
    public String getOperatorName() {
        return this.log.getOperName();
    }

    public void setOperatorName(String operatorName) {
        this.log.setOperName(operatorName);
    }

    /**
     * 操作员所在组织机构。
     * 
     * @return
     */
    public String getOrgName() {
        return this.log.getOperOrgName();
    }

    public void setOrgName(String orgName) {
        this.log.setOperOrgName(orgName);
    }

    /**
     * 日志记录时间。
     * 
     * @return
     */
    public Date getRecordTime() {
        return this.log.getInsertTime();
    }

    public void setRecordTime(Date recordTime) {
        this.log.setInsertTime(recordTime);
    }

    /**
     * 客户端机器名（IP地址）。
     * 
     * @return
     */
    public String getRemoteHost() {
        return this.log.getRemoteHost();
    }

    public void setRemoteHost(String remoteHost) {
        this.log.setRemoteHost(remoteHost);
    }

    /**
     * 操作结果代码。
     * 
     * @return
     */
    public String getResultCode() {
        return this.log.getResultCode();
    }

    public void setResultCode(String resultCode) {
        this.log.setResultCode(resultCode);
    }

    /**
     * 子系统代码。
     * 
     * @return
     */
    public String getSystemCode() {
        return this.log.getSubSysCode();
    }

    public void setSystemCode(String systemCode) {
        this.log.setSubSysCode(systemCode);
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
        this.log = (OperatorLog) target;
    }
}
