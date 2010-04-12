package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfcsPosSignin;


/**
 * RfcsPosSignin ʵ�����ݷ�����
 * @spring.bean id="RfcsPosSigninDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfcsPosSigninDAO extends BaseDao {

	public RfcsPosSigninDAO () {}
	
	public RfcsPosSigninDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RfcsPosSignin.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RfcsPosSignin getById(Serializable key)
    {
        return (RfcsPosSignin) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RfcsPosSignin loadById(Serializable key)
    {
        return (RfcsPosSignin) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}