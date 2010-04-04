package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsDictSettleType;


/**
 * RfmsDictSettleType 实体数据访问类
 * @spring.bean id="RfmsDictSettleTypeDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsDictSettleTypeDAO extends BaseDao {

	public RfmsDictSettleTypeDAO () {}
	
	public RfmsDictSettleTypeDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return RfmsDictSettleType.class;
    }

    /**
     * 根据ID查找对象
     */
    public RfmsDictSettleType getById(Serializable key)
    {
        return (RfmsDictSettleType) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public RfmsDictSettleType loadById(Serializable key)
    {
        return (RfmsDictSettleType) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}