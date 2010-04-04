package com.ft.busi.sm.model;

import java.util.List;

import com.ft.sm.dto.MaintainLogDTO;
import com.ft.sm.dto.MaintainPlanDTO;

/**
 * ϵͳά���ƻ�����ӿڡ�
 * 
 * 
 * @ejb.client jndiName="ejb/sm/maintainManager" id="maintainManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface MaintainManager extends EntityManager {

    /**
     * ����ϵͳά���ƻ���
     * 
     * @param maintainPlan
     *                ϵͳά���ƻ�ʵ�����ݡ�
     * @return ϵͳά���ƻ�ID��
     */
    public Long saveMaintainPlan(MaintainPlanDTO maintainPlan) throws Exception;

    /**
     * ����ϵͳά���ƻ���
     * 
     * @param maintainPlan
     *                ϵͳά���ƻ�ʵ�����ݡ�
     */
    public void updateMaintainPlan(MaintainPlanDTO maintainPlan)
            throws Exception;

    /**
     * ɾ��ϵͳά���ƻ���
     * 
     * @param maintainPlanId
     *                ϵͳά���ƻ�ID��
     */
    public void deleteMaintainPlan(Long maintainPlanId) throws Exception;

    /**
     * ����ϵͳά����־��
     * 
     * @param maintainLog
     *                ϵͳά����־ʵ�����ݡ�
     * @return ϵͳά����־ID��
     */
    public Long saveMaintainLog(MaintainLogDTO maintainLog) throws Exception;

    /**
     * ����ϵͳά����־��
     * 
     * @param maintainLog
     *                ϵͳά����־ʵ�����ݡ�
     */
    public void updateMaintainLog(MaintainLogDTO maintainLog) throws Exception;

    /**
     * ɾ��ϵͳά����־��
     * 
     * @param maintainLogId
     *                ϵͳά����־ID��
     */
    public void deleteMaintainLog(Long maintainLogId) throws Exception;

    /**
     * ��ѯϵͳ�����е�ά���ƻ���
     * 
     * @return
     */
    public List findAllMaintainPlans() throws Exception;
}
