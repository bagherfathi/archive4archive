package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.GroupHis;

/**
 * GroupHis 实体数据访问类
 * 
 * @spring.bean id="GroupHisDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class GroupHisDao extends BaseDao {

    /**
     * 构造函数
     */
    public GroupHisDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return GroupHis.class;
    }

    /**
     * 根据ID查找对象
     */
    public GroupHis getById(Serializable key) {
        return (GroupHis) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * 根据ID查找对象
     */
    public GroupHis loadById(Serializable key) {
        return (GroupHis) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}