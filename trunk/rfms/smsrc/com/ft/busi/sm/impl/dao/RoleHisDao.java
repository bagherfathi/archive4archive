
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RoleHis;

/**
 * RoleHis 实体数据访问类
 * 
 * @spring.bean id="RoleHisDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RoleHisDao extends BaseDao {

    /**
     * 构造函数
     */
    public RoleHisDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return RoleHis.class;
    }

    /**
     * 根据ID查找对象
     */
    public RoleHis getById(Serializable key) {
        return (RoleHis) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * 根据ID查找对象
     */
    public RoleHis loadById(Serializable key) {
        return (RoleHis) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}