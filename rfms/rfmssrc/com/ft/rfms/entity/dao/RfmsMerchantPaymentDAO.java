package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsMerchantPayment;


/**
 * RfmsMerchantPayment ʵ�����ݷ�����
 * @spring.bean id="RfmsMerchantPaymentDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsMerchantPaymentDAO extends BaseDao {

	public RfmsMerchantPaymentDAO () {}
	
	public RfmsMerchantPaymentDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RfmsMerchantPayment.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsMerchantPayment getById(Serializable key)
    {
        return (RfmsMerchantPayment) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsMerchantPayment loadById(Serializable key)
    {
        return (RfmsMerchantPayment) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}