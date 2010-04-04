package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsTicketDetail;


/**
 * RfmsTicketDetail 实体数据访问类
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
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return RfmsTicketDetail.class;
    }

    /**
     * 根据ID查找对象
     */
    public RfmsTicketDetail getById(Serializable key)
    {
        return (RfmsTicketDetail) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public RfmsTicketDetail loadById(Serializable key)
    {
        return (RfmsTicketDetail) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}