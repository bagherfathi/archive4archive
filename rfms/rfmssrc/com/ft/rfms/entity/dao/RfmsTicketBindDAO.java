package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsTicketBind;


/**
 * RfmsTicketBind 实体数据访问类
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
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return RfmsTicketBind.class;
    }

    /**
     * 根据ID查找对象
     */
    public RfmsTicketBind getById(Serializable key)
    {
        return (RfmsTicketBind) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public RfmsTicketBind loadById(Serializable key)
    {
        return (RfmsTicketBind) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}