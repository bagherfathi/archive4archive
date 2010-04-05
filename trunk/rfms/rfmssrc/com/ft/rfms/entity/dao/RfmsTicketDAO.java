package com.ft.rfms.entity.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsTicket;


/**
 * RfmsTicket ʵ�����ݷ�����
 * @spring.bean id="RfmsTicketDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsTicketDAO extends BaseDao {

	public RfmsTicketDAO () {}
	
	public RfmsTicketDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RfmsTicket.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsTicket getById(Serializable key)
    {
        return (RfmsTicket) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsTicket loadById(Serializable key)
    {
        return (RfmsTicket) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
    
    @SuppressWarnings("unchecked")
	public List<RfmsTicket> findTicketsByMerchant(String merchantCode){
    	return query("from RfmsTicket t where t.merchantId=?",new Object[]{merchantCode});
    }

}