package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.MaintainPlan;

/**
 * MaintainPlan 实体数据访问类
 * 
 * @spring.bean id="MaintainPlanDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class MaintainPlanDao extends BaseDao {

    /**
     * 构造函数
     */
    public MaintainPlanDao() {
    }

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass() {
        return MaintainPlan.class;
    }

    /**
     * 根据ID查找对象
     */
    public MaintainPlan getById(Serializable key) {
        return (MaintainPlan) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * 根据ID查找对象
     */
    public MaintainPlan loadById(Serializable key) {
        return (MaintainPlan) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * 查找所有对象
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * 根据ID删除维护计划。
     * 
     * @param planId
     *                维护计划ID。
     */
    public void deletePlanById(Long planId) {
        StringBuffer hql = new StringBuffer("from MaintainPlan plan");
        hql.append(" where plan.").append(MaintainPlan.PROP_PLAN_ID).append(
                "=?");

        this.deleteFromQuery(hql.toString(), new Object[] { planId });

    }
}