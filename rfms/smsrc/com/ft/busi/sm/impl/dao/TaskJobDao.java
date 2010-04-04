
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.TaskJob;

/**
 * TaskJob 实体数据访问类
 * 
 * @spring.bean id="TaskJobDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class TaskJobDao extends BaseDao {

    /**
     * 构造函数
     */
    public TaskJobDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return TaskJob.class;
    }

    /**
     * 根据ID查找对象
     */
    public TaskJob getById(Serializable key) {
        return (TaskJob) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * 根据ID查找对象
     */
    public TaskJob loadById(Serializable key) {
        return (TaskJob) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 查找任务参数定义
     * 
     * @param jobId
     *                任务ID
     * @return TaskParamDefine实体列表
     */
    public List findJobParamDefines(Long jobId) {
        String hql = "from TaskParamDefine param where param.jobId = ?";
        return this.query(hql, new Object[] { jobId });
    }

    /**
     * 查找任务的规则
     * 
     * @param id
     *                任务id
     * @return TaskTriggerDTO实体列表
     */
    public List findTriggerByTaskID(Long id) {
        StringBuffer hql = new StringBuffer("select new ");
        hql.append(" com.ft.sm.dto.TaskTriggerDTO(trigger,job)");
        hql.append(" from TaskTrigger trigger,TaskJob job");
        hql.append(" where trigger.jobId = job.jobId");
        hql.append(" and job.jobId = ?");
        return this.query(hql.toString(), new Object[] { id });
    }

    /**
     * 查找规则参数
     * 
     * @param triggerId
     *                规则id
     * @return TaskJobParamDTO实体列表
     */
    public List findTaskJobParam(Long triggerId) {
        StringBuffer hql = new StringBuffer("select new ");
        hql
                .append(" com.ft.sm.dto.TaskJobParamDTO(param,paramDefine)");
        hql.append(" from TaskJobParam param,TaskParamDefine paramDefine ");
        hql.append(" where param.triggerId = ?");
        hql.append(" and param.paramDefineid = paramDefine.paramDefineid");
        return this.query(hql.toString(), new Object[] { triggerId });
    }
}