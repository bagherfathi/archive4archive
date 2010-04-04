
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RuleCategory;

/**
 * RuleCategory 实体数据访问类
 * 
 * @spring.bean id="RuleCategoryDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RuleCategoryDao extends BaseDao {

    /**
     * 构造函数
     */
    public RuleCategoryDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return RuleCategory.class;
    }

    /**
     * 根据ID查找对象
     */
    public RuleCategory getById(Serializable key) {
        return (RuleCategory) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public RuleCategory loadById(Serializable key) {
        return (RuleCategory) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}