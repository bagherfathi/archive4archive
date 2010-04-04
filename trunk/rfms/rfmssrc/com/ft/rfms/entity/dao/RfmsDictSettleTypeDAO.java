package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsDictSettleType;


/**
 * RfmsDictSettleType ʵ�����ݷ�����
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
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RfmsDictSettleType.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsDictSettleType getById(Serializable key)
    {
        return (RfmsDictSettleType) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsDictSettleType loadById(Serializable key)
    {
        return (RfmsDictSettleType) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}