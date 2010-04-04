package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.BankErrorHis;

/**
 * BankErrorHis ʵ�����ݷ�����
 * @spring.bean
 * id="BankErrorHisDao"
 * @spring.property
 * ref="sessionFactory"
 * name="sessionFactory"
 */
public class BankErrorHisDao extends BaseDao {

	/**
	 * ���캯��
	 */
	public BankErrorHisDao () {}



    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return BankErrorHis.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public BankErrorHis getById(Serializable key)
    {
        return (BankErrorHis) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public BankErrorHis loadById(Serializable key)
    {
        return (BankErrorHis) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}