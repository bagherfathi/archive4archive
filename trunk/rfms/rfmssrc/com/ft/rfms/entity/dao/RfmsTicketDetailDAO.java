package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsTicketDetail;


/**
 * RfmsTicketDetail ʵ�����ݷ�����
 * @spring.bean id="RfmsTicketDetailDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsTicketDetailDAO extends BaseDao {

	public RfmsTicketDetailDAO () {}
	
	public RfmsTicketDetailDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RfmsTicketDetail.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsTicketDetail getById(Serializable key)
    {
        return (RfmsTicketDetail) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsTicketDetail loadById(Serializable key)
    {
        return (RfmsTicketDetail) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}