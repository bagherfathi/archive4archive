package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.DataResourceEntryHis;

/**
 * DataResourceEntryHis 实体数据访问类
 * 
 * @spring.bean id="DataResourceEntryHisDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class DataResourceEntryHisDao extends BaseDao {

    /**
     * 构造函数
     */
    public DataResourceEntryHisDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return DataResourceEntryHis.class;
    }

    /**
     * 根据ID查找对象
     */
    public DataResourceEntryHis getById(Serializable key) {
        return (DataResourceEntryHis) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public DataResourceEntryHis loadById(Serializable key) {
        return (DataResourceEntryHis) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}