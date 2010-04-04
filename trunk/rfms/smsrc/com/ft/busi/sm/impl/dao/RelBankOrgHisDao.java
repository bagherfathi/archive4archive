package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelBankOrgHis;

/**
 * RelBankOrgHis ʵ�����ݷ�����
 * @spring.bean
 * id="RelBankOrgHisDao"
 * @spring.property
 * ref="sessionFactory"
 * name="sessionFactory"
 */
public class RelBankOrgHisDao extends BaseDao {

	/**
	 * ���캯��
	 */
	public RelBankOrgHisDao () {}



    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RelBankOrgHis.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RelBankOrgHis getById(Serializable key)
    {
        return (RelBankOrgHis) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RelBankOrgHis loadById(Serializable key)
    {
        return (RelBankOrgHis) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}