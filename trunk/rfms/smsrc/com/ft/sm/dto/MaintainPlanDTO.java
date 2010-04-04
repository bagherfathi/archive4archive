package com.ft.sm.dto;

import com.ft.sm.entity.MaintainPlan;

/**
 * ϵͳά���ƻ�ʵ���װ�ࡣ
 * 
 */
public class MaintainPlanDTO implements DTO {
    private static final long serialVersionUID = -5609910910650211509L;

    private MaintainPlan plan;

    /**
     * Ĭ�Ϲ�����
     * 
     */
    public MaintainPlanDTO() {
        this.plan = new MaintainPlan();
    }

    public MaintainPlanDTO(MaintainPlan plan) {
        this.plan = plan;
    }

    /**
     * ������ϵͳά���ƻ���������
     * 
     * @param name
     * @param desc
     * @param cycle
     * @param cycleUnit
     */
    public MaintainPlanDTO(String name, String desc, long cycle, long cycleUnit) {
        this.plan = new MaintainPlan();
        this.plan.setPlanName(name);
        this.plan.setPlanDesc(desc);
        this.plan.setPlanCycle(cycle);
        this.plan.setCycleUnit(cycleUnit);
    }

    /**
     * �ƻ����ڡ�
     * 
     * @return Returns the cycle.
     */
    public long getCycle() {
        return this.plan.getPlanCycle();
    }

    /**
     * @param cycle
     *                The cycle to set.
     */
    public void setCycle(long cycle) {
        this.plan.setPlanCycle(cycle);
    }

    /**
     * ���ڵ�λ��
     * 
     * @return Returns the cycleUnit.
     */
    public long getCycleUnit() {
        return this.plan.getCycleUnit();
    }

    /**
     * @param cycleUnit
     *                The cycleUnit to set.
     */
    public void setCycleUnit(long cycleUnit) {
        this.plan.setCycleUnit(cycleUnit);
    }

    /**
     * �ƻ�������
     * 
     * @return Returns the desc.
     */
    public String getDesc() {
        return this.plan.getPlanDesc();
    }

    /**
     * @param desc
     *                The desc to set.
     */
    public void setDesc(String desc) {
        this.plan.setPlanDesc(desc);
    }

    /**
     * �ƻ����ơ�
     * 
     * @return Returns the name.
     */
    public String getName() {
        return this.plan.getPlanName();
    }

    /**
     * @param name
     *                The name to set.
     */
    public void setName(String name) {
        this.plan.setPlanName(name);
    }

    /**
     * �ƻ�ID��
     * 
     * @return Returns the planId.
     */
    public Long getPlanId() {
        return new Long(this.plan.getPlanId());
    }

    /**
     * 
     * @param planId
     *                The planId to set.
     */
    public void setPlanId(Long planId) {
        this.plan.setPlanId(planId.longValue());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#getTarget()
     */
    public Object getTarget() {
        return this.plan;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ft.sm.dto.DTO#setTarget(java.lang.Object)
     */
    public void setTarget(Object target) {
        this.plan = (MaintainPlan) target;
    }
}
