package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.MaintainPlan;

/**
 * MaintainPlan ʵ�����ݷ�����
 * 
 * @spring.bean id="MaintainPlanDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class MaintainPlanDao extends BaseDao {

    /**
     * ���캯��
     */
    public MaintainPlanDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return MaintainPlan.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public MaintainPlan getById(Serializable key) {
        return (MaintainPlan) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public MaintainPlan loadById(Serializable key) {
        return (MaintainPlan) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ����IDɾ��ά���ƻ���
     * 
     * @param planId
     *                ά���ƻ�ID��
     */
    public void deletePlanById(Long planId) {
        StringBuffer hql = new StringBuffer("from MaintainPlan plan");
        hql.append(" where plan.").append(MaintainPlan.PROP_PLAN_ID).append(
                "=?");

        this.deleteFromQuery(hql.toString(), new Object[] { planId });

    }
}