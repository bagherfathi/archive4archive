package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfcsTrade;


/**
 * RfcsTrade 实体数据访问类
 * @spring.bean id="RfcsTradeDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfcsTradeDAO extends BaseDao {

	public RfcsTradeDAO () {}
	
	public RfcsTradeDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return RfcsTrade.class;
    }

    /**
     * 根据ID查找对象
     */
    public RfcsTrade getById(Serializable key)
    {
        return (RfcsTrade) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public RfcsTrade loadById(Serializable key)
    {
        return (RfcsTrade) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}