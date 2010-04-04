package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.OperatorLog;

/**
 * OperatorLog 实体数据访问类
 * 
 * @spring.bean id="OperatorLogDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class OperatorLogDao extends BaseDao {

    /**
     * 构造函数
     */
    public OperatorLogDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return OperatorLog.class;
    }

    /**
     * 根据ID查找对象
     */
    public OperatorLog getById(Serializable key) {
        return (OperatorLog) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public OperatorLog loadById(Serializable key) {
        return (OperatorLog) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}