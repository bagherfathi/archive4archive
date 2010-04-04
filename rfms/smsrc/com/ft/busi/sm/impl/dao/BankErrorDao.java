package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.BankError;

/**
 * BankError ʵ�����ݷ�����
 * 
 * @spring.bean id="BankErrorDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class BankErrorDao extends BaseDao {

    /**
     * ���캯��
     */
    public BankErrorDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return BankError.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public BankError getById(Serializable key) {
        return (BankError) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * ����ID���Ҷ���
     */
    public BankError loadById(Serializable key) {
        return (BankError) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ��ѯָ�����е����մ�����Ϣ
     * 
     * @param bankId
     *                ���б�ʶ��
     * @return �������մ���ʵ���б�
     */
    public List findBankErrorsByBank(Long bankId) {
        StringBuffer hql = new StringBuffer("from BankError error");
        hql.append(" where error.").append(BankError.PROP_BANK_ID).append("=?");
       
        return this.query(hql.toString(), new Object[] { bankId });
    }
}