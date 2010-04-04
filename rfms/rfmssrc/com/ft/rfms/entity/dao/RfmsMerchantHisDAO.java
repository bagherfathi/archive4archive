package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsMerchantHis;


/**
 * RfmsMerchantHis ʵ�����ݷ�����
 * @spring.bean id="RfmsMerchantHisDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsMerchantHisDAO extends BaseDao {

	public RfmsMerchantHisDAO () {}
	
	public RfmsMerchantHisDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RfmsMerchantHis.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsMerchantHis getById(Serializable key)
    {
        return (RfmsMerchantHis) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsMerchantHis loadById(Serializable key)
    {
        return (RfmsMerchantHis) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}