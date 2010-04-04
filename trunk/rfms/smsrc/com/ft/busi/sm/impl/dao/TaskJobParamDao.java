
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.TaskJobParam;

/**
 * TaskJobParam 实体数据访问类
 * 
 * @spring.bean id="TaskJobParamDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class TaskJobParamDao extends BaseDao {

    /**
     * 构造函数
     */
    public TaskJobParamDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return TaskJobParam.class;
    }

    /**
     * 根据ID查找对象
     */
    public TaskJobParam getById(Serializable key) {
        return (TaskJobParam) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public TaskJobParam loadById(Serializable key) {
        return (TaskJobParam) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
    
    /**
     * 根据规则ID删除任务参数
     * @param triggerId
     */
    public void deleteTaskJobParamByTriggerId(Long triggerId){
        StringBuffer hql =  new StringBuffer();
        hql.append(" from TaskJobParam tjp ");
        hql.append(" where tjp.").append(TaskJobParam.PROP_TRIGGER_ID).append(" = ?");
        this.deleteFromQuery(hql.toString(), new Object[]{triggerId});
    }
    
}