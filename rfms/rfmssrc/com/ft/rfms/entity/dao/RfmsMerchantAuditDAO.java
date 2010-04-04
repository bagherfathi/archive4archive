package com.ft.rfms.entity.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsMerchantAudit;


/**
 * RfmsMerchantAudit ʵ�����ݷ�����
 * @spring.bean id="RfmsMerchantAuditDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsMerchantAuditDAO extends BaseDao {

	public RfmsMerchantAuditDAO () {}
	
	public RfmsMerchantAuditDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RfmsMerchantAudit.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsMerchantAudit getById(Serializable key)
    {
        return (RfmsMerchantAudit) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsMerchantAudit loadById(Serializable key)
    {
        return (RfmsMerchantAudit) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    public List findByMerchantIdAndFlowId(Long merchantId,Long flowCtrlId){
    	StringBuffer hql=new StringBuffer();
    	hql.append("from ").append(this.getReferenceClass().getName()).append(" aud where 1=1 ");
    	hql.append(" and aud.").append(RfmsMerchantAudit.PROP_MERCHANT_ID).append("=?");
    	hql.append(" and aud.").append(RfmsMerchantAudit.PROP_FLOW_CTRL_ID).append("=?");
    	return this.query(hql.toString(),new Long[]{merchantId,flowCtrlId});
    }
    
    public RfmsMerchantAudit findCurOperator(Long merchantId){
    	StringBuffer hql=new StringBuffer();
    	hql.append("from ").append(this.getReferenceClass().getName()).append(" aud where 1=1 ");
    	hql.append(" and aud.").append(RfmsMerchantAudit.PROP_MERCHANT_ID).append("=?");
    	hql.append(" order by ").append(RfmsMerchantAudit.PROP_AUDIT_ID).append(" desc ");
    	List list=this.query(hql.toString(),new Long[]{merchantId});
    	if(list!=null && !list.isEmpty()){
    		return (RfmsMerchantAudit)list.get(0);
    	}
    	return null;
    }
}