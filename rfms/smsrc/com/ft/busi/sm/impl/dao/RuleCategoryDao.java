
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RuleCategory;

/**
 * RuleCategory ʵ�����ݷ�����
 * 
 * @spring.bean id="RuleCategoryDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RuleCategoryDao extends BaseDao {

    /**
     * ���캯��
     */
    public RuleCategoryDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return RuleCategory.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RuleCategory getById(Serializable key) {
        return (RuleCategory) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RuleCategory loadById(Serializable key) {
        return (RuleCategory) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}