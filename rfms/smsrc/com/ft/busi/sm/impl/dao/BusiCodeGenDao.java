package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.BusiCodeGen;

/**
 * BusiCodeGen ʵ�����ݷ�����
 * 
 * @spring.bean id="busiCodeGenDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class BusiCodeGenDao extends BaseDao {

    /**
     * ���캯��
     */
    public BusiCodeGenDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return BusiCodeGen.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public BusiCodeGen getById(Serializable key) {
        return (BusiCodeGen) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public BusiCodeGen loadById(Serializable key) {
        return (BusiCodeGen) this.getHibernateTemplate().load(
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
    public BusiCodeGen getBusiCodeGenByCode(String busiCodeGenCode,
            long busiCodeRuleId) {
        String hql = " from BusiCodeGen bg where bg.busiCodeGenCode =?"
                + " and bg.busiCodeRuleId=?";
        List list = this.query(hql, new Object[] { busiCodeGenCode,
                new Long(busiCodeRuleId) });
        if ((list != null) && (list.size() == 1))
            return (BusiCodeGen) list.get(0);
        return null;
    }
}