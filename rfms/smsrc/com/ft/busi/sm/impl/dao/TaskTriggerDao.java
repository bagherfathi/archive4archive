
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import com.ft.hibernate.support.BaseDao;
import com.ft.sm.dto.TaskTriggerDTO;
import com.ft.sm.entity.TaskJob;
import com.ft.sm.entity.TaskTrigger;

/**
 * TaskTrigger 实体数据访问类
 * 
 * @spring.bean id="TaskTriggerDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class TaskTriggerDao extends BaseDao {

    /**
     * 构造函数
     */
    public TaskTriggerDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return TaskTrigger.class;
    }

    /**
     * 根据ID查找对象
     */
    public TaskTrigger getById(Serializable key) {
        return (TaskTrigger) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public TaskTrigger loadById(Serializable key) {
        return (TaskTrigger) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有启用的规则
     */
    public java.util.List findAll() {
        StringBuffer hql = new StringBuffer("select trigger,job ");
        hql.append(" from TaskTrigger trigger,TaskJob job ");
        hql.append(" where trigger.jobId = job.jobId ");
        hql.append(" and trigger.triggerStart = ?");
        List trigger_job = this.query(hql.toString(), new Object[] { new Long(
                TaskTriggerDTO.TRIGGER_START) });

        List<TaskTriggerDTO> result = new ArrayList<TaskTriggerDTO>();

        for (Iterator iter = trigger_job.iterator(); iter.hasNext();) {
            Object[] element = (Object[]) iter.next();
            result.add(new TaskTriggerDTO((TaskTrigger) element[0],
                    (TaskJob) element[1]));

        }
        return result;
    }

    /**
     * 根据ID查找对象
     * 
     * @param id
     * @return TaskTriggerDTO
     */
    public TaskTriggerDTO findTriggerById(Serializable id) {
        StringBuffer hql = new StringBuffer("select new ");
        hql.append(" com.ft.sm.dto.TaskTriggerDTO(trigger,job)");
        hql.append(" from TaskTrigger trigger,TaskJob job ");
        hql.append("where trigger.triggerId = ? ");
        hql.append(" and trigger.jobId = job.jobId");
        return (TaskTriggerDTO) this.getSingle(hql.toString(),
                new Object[] { id });
    }

    /**
     * @param jobName
     * @param cronType
     * @param triggerStatus
     */
    public List searchTrigger(String jobName, Long cronType, Long triggerStatus) {
        StringBuffer hql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        hql.append(" select new com.ft.sm.dto.TaskTriggerDTO(tt,tj) ");
        hql.append(" from TaskTrigger tt ,TaskJob tj ");
        hql.append(" where tt.").append(TaskTrigger.PROP_JOB_ID);
        hql.append(" = tj.").append(TaskJob.PROP_JOB_ID);
        if (StringUtils.isNotEmpty(jobName)) {
            hql.append(" and tj.").append(TaskJob.PROP_JOB_NAME);
            hql.append(" like ? ");
            params.add("%" + jobName + "%");
        }
        if (cronType != null && cronType.longValue() != 0) {
            hql.append(" and tt.").append(TaskTrigger.PROP_TRIGGER_CRONTYPE);
            hql.append(" = ?");
            params.add(cronType);
        }
        if (triggerStatus != null && triggerStatus.longValue() != 0) {
            hql.append(" and tt.").append(TaskTrigger.PROP_TRIGGER_STATUS);
            hql.append(" = ?");
            params.add(triggerStatus);
        }
        return this.query(hql.toString(), params.toArray());
    }
}