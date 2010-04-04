package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsMerchantPosHis;


/**
 * RfmsMerchantPosHis ʵ�����ݷ�����
 * @spring.bean id="RfmsMerchantPosHisDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsMerchantPosHisDAO extends BaseDao {

	public RfmsMerchantPosHisDAO () {}
	
	public RfmsMerchantPosHisDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RfmsMerchantPosHis.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsMerchantPosHis getById(Serializable key)
    {
        return (RfmsMerchantPosHis) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsMerchantPosHis loadById(Serializable key)
    {
        return (RfmsMerchantPosHis) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}