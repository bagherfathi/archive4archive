package com.ft.sm.dto;

import java.util.Date;

import com.ft.sm.entity.Operator;
import com.ft.sm.entity.PermitLog;

/**
 * ��Ȩ��ʷʵ���װ�ࡣ
 * 
 * @version 1.0
 */
public class PermitLogDTO implements DTO {
    private static final long serialVersionUID = 6665949036669468446L;

    // ִ����Ȩ
    public static final long OPERATION_TYPE_ADD = 1;

    // ȡ����Ȩ
    public static final long OPERATION_TYPE_REMOVE = 0;

    // ��Ȩ�������ͣ�1������Ա��2������Ա�飻3����ɫ
    public static final long GRANTEE_TYPE_OP = 1;

    public static final long GRANTEE_TYPE_GROUP = 2;

    public static final long GRANTEE_TYPE_ROLE = 3;

    // Ȩ������:1:����Ȩ�ޣ�2��ҵ��Ȩ�ޣ�3����֯;4:��ɫ;5:����Ա��
    public static final long PERMIT_TYPE_RESOURCE = 1;

    public static final long PERMIT_TYPE_DATARESOURCE = 2;

    public static final long PERMIT_TYPE_ORG = 3;

    public static final long PERMIT_TYPE_ROLE = 4;

    public static final long PERMIT_TYPE_GROUP = 5;

    private PermitLog permitLog;

    public PermitLogDTO() {
        init();
    }

    public PermitLogDTO(Operator grantor, long operationType) {
        init();

        this.permitLog.setOpType(operationType);
        this.permitLog.setGrantorId(grantor.getOperatorId());
        this.permitLog.setGrantorOrgId(grantor.getOrgId());
    }

    public PermitLogDTO(Operator grantor, long operationType, long granteeType) {
        init();

        this.permitLog.setOpType(operationType);
        this.permitLog.setGranteeType(granteeType);
        this.permitLog.setGrantorId(grantor.getOperatorId());
        this.permitLog.setGrantorOrgId(grantor.getOrgId());
    }

    public PermitLogDTO(Operator grantor, long operationType, long granteeType,
            long permitType) {
        init();

        this.permitLog.setOpType(operationType);
        this.permitLog.setGranteeType(granteeType);
        this.permitLog.setGrantorId(grantor.getOperatorId());
        this.permitLog.setGrantorOrgId(grantor.getOrgId());
        this.permitLog.setPermitType(permitType);
    }

    private void init() {
        this.permitLog = new PermitLog();
        this.permitLog.setRoleId(-1);
        this.permitLog.setGroupId(-1);
        this.permitLog.setOrgId(-1);
        this.permitLog.setPermitId(-1);
        this.permitLog.setLogTime(new Date());
    }

    /**
     * ��Ȩ�����ʶ. ��Ȩ�������Ϊ����Ա������Ա��ͽ�ɫ��
     * 
     * @return
     */
    public Long getGranteeId() {
        return new Long(this.permitLog.getGranteeId());
    }

    public void setGranteeId(Long granteeId) {
        this.permitLog.setGranteeId(granteeId.longValue());
    }

    /**
     * ��Ȩ�������� ��Ȩ�������Ͱ�����1������Ա��2������Ա�飻3��ɫ��
     * 
     * @return
     */
    public long getGranteeType() {
        return this.permitLog.getGranteeType();
    }

    public void setGranteeType(long granteeType) {
        this.permitLog.setGranteeType(granteeType);
    }

    /**
     * ��Ȩ����Ա��ʶ��
     * 
     * @return
     */
    public long getGrantorId() {
        return this.permitLog.getGrantorId();
    }

    public void setGrantorId(long grantorId) {
        this.permitLog.setGrantorId(grantorId);
    }

    /**
     * ��Ȩ����Ա������֯������ʶ��
     * 
     * @return
     */
    public long getGrantorOrgId() {
        return this.permitLog.getGrantorOrgId();
    }

    public void setGrantorOrgId(long grantorOrgId) {
        this.permitLog.setGrantorOrgId(grantorOrgId);
    }

    /**
     * ������Ĳ���Ա���ʶ. ֻ�и�����Ա�������Ա��ʱΪ����Ա���ʶ������Ϊ0��
     * 
     * @return
     */
    public long getGroupId() {
        return this.permitLog.getGroupId();
    }

    public void setGroupId(long groupId) {
        this.permitLog.setGroupId(groupId);
    }

    /**
     * ��Ȩ��ʷ��¼ID��
     * 
     * @return Returns the id.
     */
    public long getHistoryId() {
        return this.permitLog.getLogId();
    }

    /**
     * �������� �������Ͱ�����0��ȡ����Ȩ��1��ִ����Ȩ��
     * 
     * @return
     */
    public long getOpType() {
        return this.permitLog.getOpType();
    }

    public void setOpType(long opType) {
        this.permitLog.setOpType(opType);
    }

    /**
     * ����Ŀɷ�����֯������ʶ. ���ڲ���Ա�����Ա����Ȩʱ���������Ա�����Ա��Ŀɷ�����֯��ʶ��
     * 
     * @return
     */
    public long getOrgId() {
        return this.permitLog.getOrgId();
    }

    public void setOrgId(long orgId) {
        this.permitLog.setOrgId(orgId);
    }

    /**
     * ��¼ʱ��
     * 
     * @return
     */
    public Date getRecordTime() {
        return this.permitLog.getLogTime();
    }

    public void setRecordTime(Date recordTime) {
        this.permitLog.setLogTime(recordTime);
    }

    /**
     * ��Ȩ��Ȩ�ޱ�ʶ. ����Ȩ����Ϊ��ɫʱ����¼��Ȩ��Ȩ�ޱ�ʶ��
     * 
     * @return
     */
    public long getResourceId() {
        return this.permitLog.getPermitId();
    }

    public void setResourceId(long resourceId) {
        this.permitLog.setPermitId(resourceId);
    }

    /**
     * ��Ȩ������ ��Ȩ�����Ͱ�����1������Ȩ�ޣ�2��ҵ��Ȩ��;3����֯Ȩ�ޣ�4����ɫȨ��;5������Ա�顣
     * 
     * @return
     */
    public long getPermitType() {
        return this.permitLog.getPermitType();
    }

    /**
     * @param permitType
     *                The permitType to set.
     */
    public void setPermitType(long permitType) {
        this.permitLog.setPermitType(permitType);
    }

    /**
     * ��Ȩ�Ľ�ɫ��ʶ. ����Ȩ����Ϊ����Ա�����Ա��ʱ����¼����/ȡ���Ľ�ɫ��ʶ��
     * 
     * @return
     */
    public long getRoleId() {
        return this.permitLog.getRoleId();
    }

    public void setRoleId(long roleId) {
        this.permitLog.setRoleId(roleId);
    }

    /**
     * @return the permitLog
     */
    public PermitLog getPermitLog() {
        return permitLog;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.permitLog;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.permitLog = (PermitLog) target;
    }
}
