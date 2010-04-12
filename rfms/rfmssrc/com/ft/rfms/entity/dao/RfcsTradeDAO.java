package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfcsTrade;


/**
 * RfcsTrade ʵ�����ݷ�����
 * @spring.bean id="RfcsTradeDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfcsTradeDAO extends BaseDao {

	public RfcsTradeDAO () {}
	
	public RfcsTradeDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RfcsTrade.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RfcsTrade getById(Serializable key)
    {
        return (RfcsTrade) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RfcsTrade loadById(Serializable key)
    {
        return (RfcsTrade) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}