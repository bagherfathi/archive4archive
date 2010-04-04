package com.ft.rfms.entity.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsMerchant;


/**
 * RfmsMerchant ʵ�����ݷ�����
 * @spring.bean id="RfmsMerchantDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsMerchantDAO extends BaseDao {

	public RfmsMerchantDAO () {}
	
	public RfmsMerchantDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RfmsMerchant.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsMerchant getById(Serializable key)
    {
        return (RfmsMerchant) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsMerchant loadById(Serializable key)
    {
        return (RfmsMerchant) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    public List findMerchantsByOperatorAndAuditStatus(Long operatorId,Long auditStatus){
    	StringBuffer hql=new StringBuffer();
    	//select * from rfms.rfms_merchant mer,rfms.rfms_merchant_audit au where au.merchant_id=mer.merchant_id
    	 //and mer.audit_status=1 and au.next_operator_id=1;
    	hql.append("select distinct mer from RfmsMerchant mer,RfmsMerchantAudit au where au.merchantId=mer.merchantId");
    	hql.append(" and mer.auditStatus=?");
    	hql.append(" and au.nextOperatorId=?");
    	return this.query(hql.toString(),new Object[]{auditStatus,operatorId+""});
    }
}