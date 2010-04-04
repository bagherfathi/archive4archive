package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.PermitLog;

/**
 * PermitLog 实体数据访问类
 * 
 * @spring.bean id="PermitLogDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class PermitLogDao extends BaseDao {

    /**
     * 构造函数
     */
    public PermitLogDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return PermitLog.class;
    }

    /**
     * 根据ID查找对象
     */
    public PermitLog getById(Serializable key) {
        return (PermitLog) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * 根据ID查找对象
     */
    public PermitLog loadById(Serializable key) {
        return (PermitLog) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}