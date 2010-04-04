package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.dto.TaskTriggerLogDTO;
import com.ft.sm.entity.TaskTriggerLog;

/**
 * TaskTrigger 实体数据访问类
 * 
 * @spring.bean id="TaskTriggerLogDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class TaskTriggerLogDao extends BaseDao {

    /**
     * 构造函数
     */
    public TaskTriggerLogDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return TaskTriggerLog.class;
    }

    /**
     * 根据ID查找对象
     */
    public TaskTriggerLog getById(Serializable key) {
        return (TaskTriggerLog) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public TaskTriggerLog loadById(Serializable key) {
        return (TaskTriggerLog) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }
    
    public TaskTriggerLogDTO getTaskTriggerLogDTOByAppId(Long appId) {
        StringBuffer hql = new StringBuffer("select new ");
        hql.append(" com.ft.sm.dto.TaskTriggerLogDTO(triggerLog,trigger,job)");
        hql.append(" from TaskTriggerLog triggerLog,TaskTrigger trigger,TaskJob job ");
        hql.append(" where triggerLog.triggerId = trigger.triggerId ");
        hql.append(" and trigger.jobId = job.jobId ");
        hql.append(" and triggerLog.appId = ? ");
        return (TaskTriggerLogDTO) this.getSingle(hql.toString(),
                new Object[] { appId });
    }
    
}