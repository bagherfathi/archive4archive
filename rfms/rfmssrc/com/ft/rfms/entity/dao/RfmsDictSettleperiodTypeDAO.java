package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsDictSettleperiodType;


/**
 * RfmsDictSettleperiodType 实体数据访问类
 * @spring.bean id="RfmsDictSettleperiodTypeDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsDictSettleperiodTypeDAO extends BaseDao {

	public RfmsDictSettleperiodTypeDAO () {}
	
	public RfmsDictSettleperiodTypeDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return RfmsDictSettleperiodType.class;
    }

    /**
     * 根据ID查找对象
     */
    public RfmsDictSettleperiodType getById(Serializable key)
    {
        return (RfmsDictSettleperiodType) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public RfmsDictSettleperiodType loadById(Serializable key)
    {
        return (RfmsDictSettleperiodType) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}