package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsMerchantPosHis;


/**
 * RfmsMerchantPosHis 实体数据访问类
 * @spring.bean id="RfmsMerchantPosHisDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsMerchantPosHisDAO extends BaseDao {

	public RfmsMerchantPosHisDAO () {}
	
	public RfmsMerchantPosHisDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return RfmsMerchantPosHis.class;
    }

    /**
     * 根据ID查找对象
     */
    public RfmsMerchantPosHis getById(Serializable key)
    {
        return (RfmsMerchantPosHis) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public RfmsMerchantPosHis loadById(Serializable key)
    {
        return (RfmsMerchantPosHis) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}