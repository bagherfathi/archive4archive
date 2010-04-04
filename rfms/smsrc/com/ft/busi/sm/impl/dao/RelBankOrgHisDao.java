package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelBankOrgHis;

/**
 * RelBankOrgHis 实体数据访问类
 * @spring.bean
 * id="RelBankOrgHisDao"
 * @spring.property
 * ref="sessionFactory"
 * name="sessionFactory"
 */
public class RelBankOrgHisDao extends BaseDao {

	/**
	 * 构造函数
	 */
	public RelBankOrgHisDao () {}



    /**
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return RelBankOrgHis.class;
    }

    /**
     * 根据ID查找对象
     */
    public RelBankOrgHis getById(Serializable key)
    {
        return (RelBankOrgHis) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public RelBankOrgHis loadById(Serializable key)
    {
        return (RelBankOrgHis) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}