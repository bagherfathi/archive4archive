package com.ft.busi.sm.model;

import com.ft.sm.dto.OperatorLogDTO;
import com.ft.sm.dto.PerformanceLogDTO;

/**
 * 操作日志管理接口。
 * 
 * @ejb.client jndiName="ejb/sm/operateLogManager" id="operateLogManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface OperateLogManager extends EntityManager {
    /**
     * 记录操作日志。
     * 
     * @param log
     *                操作日志实体。
     */
    public void save(OperatorLogDTO log) throws Exception;

    /**
     * 记录性能日志。
     * 
     * @param log
     *                操作日志实体。
     */
    public void save(PerformanceLogDTO log) throws Exception;
}
