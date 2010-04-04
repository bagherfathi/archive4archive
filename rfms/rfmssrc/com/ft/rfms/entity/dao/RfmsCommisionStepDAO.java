package com.ft.rfms.entity.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsCommisionStep;


/**
 * RfmsCommisionStep ʵ�����ݷ�����
 * @spring.bean id="RfmsCommisionStepDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsCommisionStepDAO extends BaseDao {

	public RfmsCommisionStepDAO () {}
	
	
	public RfmsCommisionStepDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RfmsCommisionStep.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsCommisionStep getById(Serializable key)
    {
        return (RfmsCommisionStep) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsCommisionStep loadById(Serializable key)
    {
        return (RfmsCommisionStep) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    public List findByMerchantId(Long merchantId){
    	StringBuffer hql=new StringBuffer();
    	hql.append("from ").append(this.getReferenceClass().getName()).append(" step where ");
    	hql.append(" step.").append(RfmsCommisionStep.PROP_MERCHANT_ID).append("=?");
    	return this.query(hql.toString(),new Object[]{merchantId});
    }
}