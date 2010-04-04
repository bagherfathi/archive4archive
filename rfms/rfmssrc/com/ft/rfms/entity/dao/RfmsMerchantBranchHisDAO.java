package com.ft.rfms.entity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsMerchantBranchHis;


/**
 * RfmsMerchantBranchHis ʵ�����ݷ�����
 * @spring.bean id="RfmsMerchantBranchHisDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsMerchantBranchHisDAO extends BaseDao {

	public RfmsMerchantBranchHisDAO () {}
	
	public RfmsMerchantBranchHisDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RfmsMerchantBranchHis.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsMerchantBranchHis getById(Serializable key)
    {
        return (RfmsMerchantBranchHis) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsMerchantBranchHis loadById(Serializable key)
    {
        return (RfmsMerchantBranchHis) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

}