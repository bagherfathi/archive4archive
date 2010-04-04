package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsMerchantHis;


/**
 * RfmsMerchantHis 实体数据访问类
 * @spring.bean id="RfmsMerchantHisDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsMerchantHisDAO extends BaseDao {

	public RfmsMerchantHisDAO () {}
	
	public RfmsMerchantHisDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return RfmsMerchantHis.class;
    }

    /**
     * 根据ID查找对象
     */
    public RfmsMerchantHis getById(Serializable key)
    {
        return (RfmsMerchantHis) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public RfmsMerchantHis loadById(Serializable key)
    {
        return (RfmsMerchantHis) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}