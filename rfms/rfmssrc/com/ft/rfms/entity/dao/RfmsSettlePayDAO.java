package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsSettlePay;


/**
 * RfmsSettlePay ʵ�����ݷ�����
 * @spring.bean id="RfmsSettlePayDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsSettlePayDAO extends BaseDao {

	public RfmsSettlePayDAO () {}
	
	public RfmsSettlePayDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RfmsSettlePay.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsSettlePay getById(Serializable key)
    {
        return (RfmsSettlePay) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsSettlePay loadById(Serializable key)
    {
        return (RfmsSettlePay) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}