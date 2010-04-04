package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsMerchantPayment;


/**
 * RfmsMerchantPayment 实体数据访问类
 * @spring.bean id="RfmsMerchantPaymentDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsMerchantPaymentDAO extends BaseDao {

	public RfmsMerchantPaymentDAO () {}
	
	public RfmsMerchantPaymentDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return RfmsMerchantPayment.class;
    }

    /**
     * 根据ID查找对象
     */
    public RfmsMerchantPayment getById(Serializable key)
    {
        return (RfmsMerchantPayment) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public RfmsMerchantPayment loadById(Serializable key)
    {
        return (RfmsMerchantPayment) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}