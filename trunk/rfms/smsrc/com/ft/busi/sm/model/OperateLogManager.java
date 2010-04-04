package com.ft.busi.sm.model;

import com.ft.sm.dto.OperatorLogDTO;
import com.ft.sm.dto.PerformanceLogDTO;

/**
 * ������־����ӿڡ�
 * 
 * @ejb.client jndiName="ejb/sm/operateLogManager" id="operateLogManager"
 *             homepackage="com.ft.busi.sm.ejb"
 */
public interface OperateLogManager extends EntityManager {
    /**
     * ��¼������־��
     * 
     * @param log
     *                ������־ʵ�塣
     */
    public void save(OperatorLogDTO log) throws Exception;

    /**
     * ��¼������־��
     * 
     * @param log
     *                ������־ʵ�塣
     */
    public void save(PerformanceLogDTO log) throws Exception;
}
