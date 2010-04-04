package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsMerchantBranchHis;


/**
 * RfmsMerchantBranchHis 实体数据访问类
 * @spring.bean id="RfmsMerchantBranchHisDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsMerchantBranchHisDAO extends BaseDao {

	public RfmsMerchantBranchHisDAO () {}
	
	public RfmsMerchantBranchHisDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return RfmsMerchantBranchHis.class;
    }

    /**
     * 根据ID查找对象
     */
    public RfmsMerchantBranchHis getById(Serializable key)
    {
        return (RfmsMerchantBranchHis) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public RfmsMerchantBranchHis loadById(Serializable key)
    {
        return (RfmsMerchantBranchHis) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}