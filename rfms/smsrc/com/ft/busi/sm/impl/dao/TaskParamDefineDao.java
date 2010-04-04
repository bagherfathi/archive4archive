
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.TaskParamDefine;

/**
 * TaskParamDefine 实体数据访问类
 * 
 * @spring.bean id="TaskParamDefineDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class TaskParamDefineDao extends BaseDao {

    /**
     * 构造函数
     */
    public TaskParamDefineDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return TaskParamDefine.class;
    }

    /**
     * 根据ID查找对象
     */
    public TaskParamDefine getById(Serializable key) {
        return (TaskParamDefine) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public TaskParamDefine loadById(Serializable key) {
        return (TaskParamDefine) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
    
    /**
     * 根据任务ID删除参数定义
     * @param taskId
     */
    public void deleteTaskParamDefineByTaskId(Long taskId){
        StringBuffer hql = new StringBuffer();
        hql.append(" from TaskParamDefine tpd ");
        hql.append(" where tpd.").append(TaskParamDefine.PROP_JOB_ID).append("=?");
        
        this.deleteFromQuery(hql.toString(), new Object[]{taskId});
    }
}