package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsSms;


/**
 * RfmsSms ʵ�����ݷ�����
 * @spring.bean id="RfmsSmsDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsSmsDAO extends BaseDao {

	public RfmsSmsDAO () {}
	
	public RfmsSmsDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RfmsSms.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsSms getById(Serializable key)
    {
        return (RfmsSms) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsSms loadById(Serializable key)
    {
        return (RfmsSms) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}