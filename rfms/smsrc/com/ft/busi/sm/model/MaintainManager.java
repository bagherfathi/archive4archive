package com.ft.busi.sm.model;

import java.util.List;

import com.ft.sm.dto.MaintainLogDTO;
import com.ft.sm.dto.MaintainPlanDTO;

/**
 * 系统维护计划管理接口。
 * 
 * 
 * @ejb.client jndiName="ejb/sm/maintainManager" id="maintainManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface MaintainManager extends EntityManager {

    /**
     * 保存系统维护计划。
     * 
     * @param maintainPlan
     *                系统维护计划实体数据。
     * @return 系统维护计划ID。
     */
    public Long saveMaintainPlan(MaintainPlanDTO maintainPlan) throws Exception;

    /**
     * 更新系统维护计划。
     * 
     * @param maintainPlan
     *                系统维护计划实体数据。
     */
    public void updateMaintainPlan(MaintainPlanDTO maintainPlan)
            throws Exception;

    /**
     * 删除系统维护计划。
     * 
     * @param maintainPlanId
     *                系统维护计划ID。
     */
    public void deleteMaintainPlan(Long maintainPlanId) throws Exception;

    /**
     * 保存系统维护日志。
     * 
     * @param maintainLog
     *                系统维护日志实体数据。
     * @return 系统维护日志ID。
     */
    public Long saveMaintainLog(MaintainLogDTO maintainLog) throws Exception;

    /**
     * 更新系统维护日志。
     * 
     * @param maintainLog
     *                系统维护日志实体数据。
     */
    public void updateMaintainLog(MaintainLogDTO maintainLog) throws Exception;

    /**
     * 删除系统维护日志。
     * 
     * @param maintainLogId
     *                系统维护日志ID。
     */
    public void deleteMaintainLog(Long maintainLogId) throws Exception;

    /**
     * 查询系统中所有的维护计划。
     * 
     * @return
     */
    public List findAllMaintainPlans() throws Exception;
}
