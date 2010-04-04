package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.BusiCodeRule;

/**
 * BusiCodeRule ʵ�����ݷ�����
 * 
 * @spring.bean id="busiCodeRuleDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class BusiCodeRuleDao extends BaseDao {

    /**
     * ���캯��
     */
    public BusiCodeRuleDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return BusiCodeRule.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public BusiCodeRule getById(Serializable key) {
        return (BusiCodeRule) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public BusiCodeRule loadById(Serializable key) {
        return (BusiCodeRule) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ����ҵ����������ұ��������
     * 
     * @param busiCodeRuleCode
     * @return
     */
    public BusiCodeRule getBusiCodeRuleByCode(String busiCodeRuleCode) {
        String hql = " from BusiCodeRule cr where cr.busiCodeRuleCode =?";
        List list = this.query(hql, new String[] { busiCodeRuleCode });
        if ((list != null) && (list.size() == 1))
            return (BusiCodeRule) list.get(0);
        return null;
    }

}