package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsSettlePay;


/**
 * RfmsSettlePay 实体数据访问类
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
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return RfmsSettlePay.class;
    }

    /**
     * 根据ID查找对象
     */
    public RfmsSettlePay getById(Serializable key)
    {
        return (RfmsSettlePay) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public RfmsSettlePay loadById(Serializable key)
    {
        return (RfmsSettlePay) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}