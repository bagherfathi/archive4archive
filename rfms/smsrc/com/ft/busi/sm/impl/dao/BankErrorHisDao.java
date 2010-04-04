package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.BankErrorHis;

/**
 * BankErrorHis 实体数据访问类
 * @spring.bean
 * id="BankErrorHisDao"
 * @spring.property
 * ref="sessionFactory"
 * name="sessionFactory"
 */
public class BankErrorHisDao extends BaseDao {

	/**
	 * 构造函数
	 */
	public BankErrorHisDao () {}



    /**
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return BankErrorHis.class;
    }

    /**
     * 根据ID查找对象
     */
    public BankErrorHis getById(Serializable key)
    {
        return (BankErrorHis) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public BankErrorHis loadById(Serializable key)
    {
        return (BankErrorHis) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}