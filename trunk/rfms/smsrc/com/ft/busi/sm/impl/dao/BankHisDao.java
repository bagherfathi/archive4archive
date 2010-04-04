package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.BankHis;

/**
 * BankHis 实体数据访问类
 * @spring.bean
 * id="BankHisDao"
 * @spring.property
 * ref="sessionFactory"
 * name="sessionFactory"
 */
public class BankHisDao extends BaseDao {

	/**
	 * 构造函数
	 */
	public BankHisDao () {}



    /**
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return BankHis.class;
    }

    /**
     * 根据ID查找对象
     */
    public BankHis getById(Serializable key)
    {
        return (BankHis) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public BankHis loadById(Serializable key)
    {
        return (BankHis) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}