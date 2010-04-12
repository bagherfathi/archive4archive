package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfcsPosSignin;


/**
 * RfcsPosSignin 实体数据访问类
 * @spring.bean id="RfcsPosSigninDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfcsPosSigninDAO extends BaseDao {

	public RfcsPosSigninDAO () {}
	
	public RfcsPosSigninDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return RfcsPosSignin.class;
    }

    /**
     * 根据ID查找对象
     */
    public RfcsPosSignin getById(Serializable key)
    {
        return (RfcsPosSignin) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public RfcsPosSignin loadById(Serializable key)
    {
        return (RfcsPosSignin) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}