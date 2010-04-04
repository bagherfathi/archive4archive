package com.ft.busi.sm.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ft.busi.sm.impl.dao.MaintainLogDao;
import com.ft.busi.sm.impl.dao.MaintainPlanDao;
import com.ft.busi.sm.model.MaintainManager;
import com.ft.commons.page.PageBean;
import com.ft.sm.dto.MaintainLogDTO;
import com.ft.sm.dto.MaintainPlanDTO;
import com.ft.sm.entity.MaintainLog;
import com.ft.sm.entity.MaintainPlan;

/**
 * 系统维护计划接口实现类
 * 
 * @spring.aop-bean id="maintainManager" parent="transactionProxyFactoryBean"
 *                  target="maintainManagerImpl"
 * 
 * @spring.bean id="maintainManagerImpl"
 * 
 * @version 1.0
 */
public class MaintainManagerImpl implements MaintainManager {

    private MaintainLogDao logDao;

    private MaintainPlanDao planDao;

    /**
     * @spring.property ref="MaintainLogDao"
     * @param logDao
     *                the logDao to set
     */
    public void setLogDao(MaintainLogDao logDao) {
        this.logDao = logDao;
    }

    /**
     * @spring.property ref="MaintainPlanDao"
     * @param planDao
     *                the planDao to set
     */
    public void setPlanDao(MaintainPlanDao planDao) {
        this.planDao = planDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.MaintainManager#saveMaintainPlan(com.huashu.boss.sm.entity.MaintainPlan)
     */
    public Long saveMaintainPlan(MaintainPlanDTO maintainPlan) {
        if (null == maintainPlan) {
            throw new IllegalArgumentException();
        }

        MaintainPlan plan = (MaintainPlan) maintainPlan.getTarget();
        this.planDao.save(plan);

        return new Long(plan.getPlanId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.MaintainManager#updateMaintainPlan(com.huashu.boss.sm.entity.MaintainPlan)
     */
    public void updateMaintainPlan(MaintainPlanDTO maintainPlan) {
        if (null == maintainPlan) {
            throw new IllegalArgumentException();
        }

        this.planDao.update(maintainPlan.getTarget());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.MaintainManager#deleteMaintainPlan(com.huashu.boss.sm.entity.MaintainPlan)
     */
    public void deleteMaintainPlan(Long planId) {
        if (null == planId) {
            throw new IllegalArgumentException();
        }

        this.planDao.deletePlanById(planId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.MaintainManager#saveMaintainLog(com.huashu.boss.sm.entity.MaintainLog)
     */
    public Long saveMaintainLog(MaintainLogDTO maintainLog) {
        if (null == maintainLog) {
            throw new IllegalArgumentException();
        }
        MaintainLog log = (MaintainLog) maintainLog.getTarget();
        this.logDao.save(log);

        return new Long(log.getLogId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.MaintainManager#updateMaintainLog(com.huashu.boss.sm.entity.MaintainLog)
     */
    public void updateMaintainLog(MaintainLogDTO maintainLog) {
        if (null == maintainLog) {
            throw new IllegalArgumentException();
        }

        // 设置日志数据
        MaintainLog log = this.logDao.getById(maintainLog.getLogId());

        log.setLogContent(maintainLog.getContent());
        log.setPlanId(maintainLog.getPlanId().longValue());
        log.setLogTitle(maintainLog.getTitle());
        log.setLogType(maintainLog.getType());
        this.logDao.update(log);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.MaintainManager#deleteMaintainLog(com.huashu.boss.sm.entity.MaintainLog)
     */
    public void deleteMaintainLog(Long logId) {
        if (null == logId) {
            throw new IllegalArgumentException();
        }

        this.logDao.deleteLogById(logId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.MaintainManager#findAllMaintainPlans()
     */
    public List findAllMaintainPlans() {
        List result = this.planDao.findAll();
        return EntityDTOConverter.converMaintainPlan2DTO(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getEntity(java.lang.Class,
     *      java.io.Serializable)
     */
    public Object getEntity(Class typeClass, Serializable id) {
        if (typeClass.equals(MaintainPlanDTO.class)) {
            MaintainPlan plan = this.planDao.getById(id);

            if (plan != null)
                return new MaintainPlanDTO(plan);
            else
                return null;
        }

        if (typeClass.equals(MaintainLogDTO.class)) {
            MaintainLog log = this.logDao.getById(id);
            if (log != null)
                return new MaintainLogDTO(log);
            else
                return null;
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getResultSet(java.lang.Class,
     *      java.lang.String, java.lang.Object[],
     *      com.huashu.commons.page.PageBean)
     */
    public List getResultSet(Class typeClass, String hql, Object[] params,
            PageBean page) {
        List result = new ArrayList();
        if (typeClass.equals(MaintainPlan.class)) {
            result = this.planDao.query(hql, params, page);
            return EntityDTOConverter.converMaintainPlan2DTO(result);
        }

        if (typeClass.equals(MaintainLog.class)) {
            result = this.logDao.query(hql, params, page);
            return EntityDTOConverter.converMaintainLog2DTO(result);
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#loadByIds(java.lang.Class,
     *      java.io.Serializable[])
     */
    public List loadByIds(Class typeClass, Serializable[] ids) {
        List result = new ArrayList();
        if (typeClass.equals(MaintainPlanDTO.class)) {
            result = this.planDao.loadByIds(MaintainPlan.class, ids);
            return EntityDTOConverter.converMaintainPlan2DTO(result);
        }

        if (typeClass.equals(MaintainLogDTO.class)) {
            result = this.logDao.loadByIds(MaintainLog.class, ids);
            return EntityDTOConverter.converMaintainLog2DTO(result);
        }

        return result;
    }
}
