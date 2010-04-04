package com.ft.busi.sm.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ft.busi.sm.impl.dao.OperatorLogDao;
import com.ft.busi.sm.impl.dao.PerformanceLogDao;
import com.ft.busi.sm.model.OperateLogManager;
import com.ft.commons.page.PageBean;
import com.ft.sm.dto.OperatorLogDTO;
import com.ft.sm.dto.PerformanceLogDTO;
import com.ft.sm.entity.OperatorLog;
import com.ft.sm.entity.PerformanceLog;

/**
 * 操作日志管理实现类.
 * 
 * @version 1.0
 * 
 * @spring.aop-bean id="operateLogManager" parent="transactionProxyFactoryBean"
 *                  target="operateLogManagerImpl"
 * 
 * @spring.bean id="operateLogManagerImpl"
 */
public class OperateLogManagerImpl implements OperateLogManager {
    private OperatorLogDao operatorLogDao;

    private PerformanceLogDao performanceLogDao;

    /**
     * @spring.property ref = "OperatorLogDao"
     * @param operatorLogDao
     *                the operatorLogDao to set
     */
    public void setOperatorLogDao(OperatorLogDao operatorLogDao) {
        this.operatorLogDao = operatorLogDao;
    }

    /**
     * @spring.property ref = "PerformanceLogDao"
     * @param performanceLogDao
     *                the performanceLogDao to set
     */
    public void setPerformanceLogDao(PerformanceLogDao performanceLogDao) {
        this.performanceLogDao = performanceLogDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperateLogManager#save(com.huashu.boss.sm.entity.OperateLog)
     */
    public void save(OperatorLogDTO log) {
        log.setRecordTime(new Date());
        this.operatorLogDao.save(log.getTarget());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.OperateLogManager#save(com.huashu.boss.sm.entity.PerformanceLog)
     */
    public void save(PerformanceLogDTO log) {
        log.setRecordTime(new Date());
        OperatorLog operatorLog = log.getOperatorLog();
        this.operatorLogDao.save(operatorLog);
        PerformanceLog performanceLog = log.getPerformanceLog();
        performanceLog.setLogId(operatorLog.getLogId());
        this.performanceLogDao.save(performanceLog);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#getEntity(java.lang.Class,
     *      java.io.Serializable)
     */
    public Object getEntity(Class typeClass, Serializable id) {
        if (typeClass.equals(OperatorLogDTO.class)) {
            OperatorLog log = this.operatorLogDao.getById(id);
            if (log != null)
                return new OperatorLogDTO(log);
            else
                return null;
        }

        if (typeClass.equals(PerformanceLogDTO.class)) {
            PerformanceLog plog = this.performanceLogDao.getById(id);
            if (plog != null) {
                OperatorLog log = this.operatorLogDao.getById(new Long(plog
                        .getLogId()));
                return new PerformanceLogDTO(log, plog);
            } else {
                return null;
            }
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
    @SuppressWarnings("unchecked")
	public List getResultSet(Class typeClass, String hql, Object[] params,
            PageBean page) {
        List<Object> result = new ArrayList<Object>();
        if (typeClass.equals(OperatorLog.class)) {
            List logList = this.operatorLogDao.query(hql, params, page);
            for (Iterator iterator = logList.iterator(); iterator.hasNext();) {
                OperatorLog log = (OperatorLog) iterator.next();
                result.add(new OperatorLogDTO(log));
            }

            return result;
        }

        if (typeClass.equals(PerformanceLog.class)) {
            result = this.performanceLogDao.query(hql, params, page);
            return this.converPerformanceLog2DTO(result);
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.huashu.boss.busi.sm.model.EntityManager#loadByIds(java.lang.Class,
     *      java.io.Serializable[])
     */
    @SuppressWarnings("unchecked")
	public List loadByIds(Class typeClass, Serializable[] ids) {
        List result = new ArrayList();
        if (typeClass.equals(OperatorLogDTO.class)) {
            List logList = this.operatorLogDao
                    .loadByIds(OperatorLog.class, ids);
            for (Iterator iterator = logList.iterator(); iterator.hasNext();) {
                OperatorLog log = (OperatorLog) iterator.next();
                result.add(new OperatorLogDTO(log));
            }

            return result;
        }

        if (typeClass.equals(PerformanceLogDTO.class)) {
            result = this.performanceLogDao.loadByIds(OperatorLog.class, ids);
            return this.converPerformanceLog2DTO(result);
        }

        return result;
    }

    /**
     * 将性能日志实体列表转换为DTO列表。
     * 
     * @param plogList
     * @return
     */
    @SuppressWarnings("unchecked")
	private List converPerformanceLog2DTO(List plogList) {
        List result = new ArrayList();
        Long[] logIds = new Long[plogList.size()];
        for (int i = 0; i < plogList.size(); i++) {
            PerformanceLog plog = (PerformanceLog) plogList.get(i);
            logIds[i] = new Long(plog.getLogId());
        }

        List logList = this.operatorLogDao.loadByIds(OperatorLog.class, logIds);

        for (Iterator iterator = plogList.iterator(); iterator.hasNext();) {
            PerformanceLog plog = (PerformanceLog) iterator.next();

            for (Iterator iter = logList.iterator(); iter.hasNext();) {
                OperatorLog log = (OperatorLog) iter.next();
                if (log.getLogId() == plog.getLogId()) {
                    result.add(new PerformanceLogDTO(log, plog));
                    break;
                }
            }
        }

        return result;
    }
}
