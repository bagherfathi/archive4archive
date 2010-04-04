
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.ResourceHis;

/**
 * ResourceHis 实体数据访问类
 * 
 * @spring.bean id="ResourceHisDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class ResourceHisDao extends BaseDao {

    /**
     * 构造函数
     */
    public ResourceHisDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return ResourceHis.class;
    }

    /**
     * 根据ID查找对象
     */
    public ResourceHis getById(Serializable key) {
        return (ResourceHis) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public ResourceHis loadById(Serializable key) {
        return (ResourceHis) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}