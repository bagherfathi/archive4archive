package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.BankHis;

/**
 * BankHis ʵ�����ݷ�����
 * @spring.bean
 * id="BankHisDao"
 * @spring.property
 * ref="sessionFactory"
 * name="sessionFactory"
 */
public class BankHisDao extends BaseDao {

	/**
	 * ���캯��
	 */
	public BankHisDao () {}



    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return BankHis.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public BankHis getById(Serializable key)
    {
        return (BankHis) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public BankHis loadById(Serializable key)
    {
        return (BankHis) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}