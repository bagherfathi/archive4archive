package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsSms;


/**
 * RfmsSms 实体数据访问类
 * @spring.bean id="RfmsSmsDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsSmsDAO extends BaseDao {

	public RfmsSmsDAO () {}
	
	public RfmsSmsDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return RfmsSms.class;
    }

    /**
     * 根据ID查找对象
     */
    public RfmsSms getById(Serializable key)
    {
        return (RfmsSms) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public RfmsSms loadById(Serializable key)
    {
        return (RfmsSms) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}