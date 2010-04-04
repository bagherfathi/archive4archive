package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsTicketBind;


/**
 * RfmsTicketBind ʵ�����ݷ�����
 * @spring.bean id="RfmsTicketBindDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsTicketBindDAO extends BaseDao {

	public RfmsTicketBindDAO () {}
	
	public RfmsTicketBindDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RfmsTicketBind.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsTicketBind getById(Serializable key)
    {
        return (RfmsTicketBind) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsTicketBind loadById(Serializable key)
    {
        return (RfmsTicketBind) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}