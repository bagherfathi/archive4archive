
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.TaskCategory;

/**
 * TaskCategory 实体数据访问类
 * 
 * @spring.bean id="TaskCategoryDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class TaskCategoryDao extends BaseDao {

    /**
     * 构造函数
     */
    public TaskCategoryDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return TaskCategory.class;
    }

    /**
     * 根据ID查找对象
     */
    public TaskCategory getById(Serializable key) {
        return (TaskCategory) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public TaskCategory loadById(Serializable key) {
        return (TaskCategory) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}