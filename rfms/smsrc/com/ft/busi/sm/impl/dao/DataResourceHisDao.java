package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.DataResourceHis;

/**
 * DataResourceHis 实体数据访问类
 * 
 * @spring.bean id="DataResourceHisDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class DataResourceHisDao extends BaseDao {

    /**
     * 构造函数
     */
    public DataResourceHisDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return DataResourceHis.class;
    }

    /**
     * 根据ID查找对象
     */
    public DataResourceHis getById(Serializable key) {
        return (DataResourceHis) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public DataResourceHis loadById(Serializable key) {
        return (DataResourceHis) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}