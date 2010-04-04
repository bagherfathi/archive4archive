
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.TaskTriggerHis;

/**
 * TaskTriggerHis 实体数据访问类
 * 
 * @spring.bean id="TaskTriggerHisDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class TaskTriggerHisDao extends BaseDao {

    /**
     * 构造函数
     */
    public TaskTriggerHisDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return TaskTriggerHis.class;
    }

    /**
     * 根据ID查找对象
     */
    public TaskTriggerHis getById(Serializable key) {
        return (TaskTriggerHis) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public TaskTriggerHis loadById(Serializable key) {
        return (TaskTriggerHis) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 查询历史记录
     * 
     * @param triggerId
     * @return
     */
    public List findTriggerHistorybyTriggerId(Long triggerId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from TaskTriggerHis tth ");
        hql.append(" where tth.").append(TaskTriggerHis.PROP_TRIGGER_ID);
        hql.append(" = ?");
        return this.query(hql.toString(), new Object[] { triggerId });
    }

    /**
     * 根据任务Id查询每条规则的执行次数，和最后执行时间 。jobId为空则统计所有规则。
     * 
     * @param jobId
     *            jobId
     * @return 对象数组集合 [规则ID,执行次数,最后执行时间]
     */
    public List findTriggerHistorybyTaskId(Long jobId) {
        StringBuffer hql = new StringBuffer();
        List<Long> param = new ArrayList<Long>();
        
        hql.append(" select new com.ft.sm.dto.TaskTriggerDTO");
        hql.append("(tth.triggerId,count(tth.hisId),max(tth.endTime))");
        hql.append(" from TaskTriggerHis tth");
        if (jobId != null) {
            hql.append(" where tth.").append(TaskTriggerHis.PROP_TASK_ID)
                    .append(" =? ");
            param.add(jobId);
        }
        hql.append(" group by tth.").append(TaskTriggerHis.PROP_TRIGGER_ID);
        List result = this.query(hql.toString(), param.toArray());
        return result;
    }
}