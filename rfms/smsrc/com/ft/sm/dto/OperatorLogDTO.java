package com.ft.sm.dto;

import java.util.Date;

import com.ft.sm.entity.OperatorLog;

/**
 * ������־ʵ���װ�ࡣ
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
     * �������
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
     * �������롣
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
     * ������ˮ�š�
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
     * ������־ID��
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
     * ģ�����ơ�
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
     * ����Ա��½���ơ�
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
     * ����Ա���ơ�
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
     * ����Ա������֯������
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
     * ��־��¼ʱ�䡣
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
     * �ͻ��˻�������IP��ַ����
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
     * ����������롣
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
     * ��ϵͳ���롣
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
