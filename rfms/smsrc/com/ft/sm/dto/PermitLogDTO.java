package com.ft.sm.dto;

import java.util.Date;

import com.ft.sm.entity.Operator;
import com.ft.sm.entity.PermitLog;

/**
 * 授权历史实体封装类。
 * 
 * @version 1.0
 */
public class PermitLogDTO implements DTO {
    private static final long serialVersionUID = 6665949036669468446L;

    // 执行授权
    public static final long OPERATION_TYPE_ADD = 1;

    // 取消授权
    public static final long OPERATION_TYPE_REMOVE = 0;

    // 授权对象类型：1：操作员；2：操作员组；3：角色
    public static final long GRANTEE_TYPE_OP = 1;

    public static final long GRANTEE_TYPE_GROUP = 2;

    public static final long GRANTEE_TYPE_ROLE = 3;

    // 权限类型:1:功能权限；2：业务权限；3：组织;4:角色;5:操作员组
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
     * 授权对象标识. 授权对象可以为操作员、操作员组和角色。
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
     * 授权对象类型 授权对象类型包括：1：操作员；2：操作员组；3角色。
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
     * 授权操作员标识。
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
     * 授权操作员所在组织机构标识。
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
     * 被授予的操作员组标识. 只有给操作员分配操作员组时为操作员组标识，否则为0。
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
     * 授权历史记录ID。
     * 
     * @return Returns the id.
     */
    public long getHistoryId() {
        return this.permitLog.getLogId();
    }

    /**
     * 操作类型 操作类型包括：0：取消授权；1：执行授权。
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
     * 授予的可访问组织机构标识. 对于操作员或操作员组授权时，授予操作员或操作员组的可访问组织标识。
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
     * 记录时间
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
     * 授权的权限标识. 当授权对象为角色时，记录授权的权限标识。
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
     * 授权的类型 授权的类型包括：1：功能权限；2：业务权限;3：组织权限；4：角色权限;5：操作员组。
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
     * 授权的角色标识. 当授权对象为操作员或操作员组时，记录授予/取消的角色标识。
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
