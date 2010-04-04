package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsDictSettleperiodType;


/**
 * RfmsDictSettleperiodType ʵ�����ݷ�����
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
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RfmsDictSettleperiodType.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsDictSettleperiodType getById(Serializable key)
    {
        return (RfmsDictSettleperiodType) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsDictSettleperiodType loadById(Serializable key)
    {
        return (RfmsDictSettleperiodType) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}