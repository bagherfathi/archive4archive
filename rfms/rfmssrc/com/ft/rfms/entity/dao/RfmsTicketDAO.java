package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsTicket;


/**
 * RfmsTicket 实体数据访问类
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
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return RfmsTicket.class;
    }

    /**
     * 根据ID查找对象
     */
    public RfmsTicket getById(Serializable key)
    {
        return (RfmsTicket) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public RfmsTicket loadById(Serializable key)
    {
        return (RfmsTicket) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}