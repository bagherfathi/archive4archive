package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.PerformanceLog;

/**
 * PerformanceLog 实体数据访问类
 * 
 * @spring.bean id="PerformanceLogDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class PerformanceLogDao extends BaseDao {

    /**
     * 构造函数
     */
    public PerformanceLogDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return PerformanceLog.class;
    }

    /**
     * 根据ID查找对象
     */
    public PerformanceLog getById(Serializable key) {
        return (PerformanceLog) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public PerformanceLog loadById(Serializable key) {
        return (PerformanceLog) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}