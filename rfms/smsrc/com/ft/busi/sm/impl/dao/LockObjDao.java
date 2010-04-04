package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.LockObj;

/**
 * LockObj 实体数据访问类
 * 
 * @spring.bean id="lockObjDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class LockObjDao extends BaseDao {

    /**
     * 构造函数
     */
    public LockObjDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return LockObj.class;
    }

    /**
     * 根据ID查找对象
     */
    public LockObj getById(Serializable key) {
        return (LockObj) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * 根据ID查找对象
     */
    public LockObj loadById(Serializable key) {
        return (LockObj) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}