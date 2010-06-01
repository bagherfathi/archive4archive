package com.ft.rfms.entity.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsMerchantBranch;
import com.ft.rfms.entity.RfmsMerchantPos;



/**
 * RfmsMerchantBranch ʵ�����ݷ�����
 * @spring.bean id="RfmsMerchantBranchDAO"
 *
 * @spring.property ref="sessionFactory" name="sessionFactory"
 *
 */
public class RfmsMerchantBranchDAO extends BaseDao {

	public RfmsMerchantBranchDAO () {}
	
	public RfmsMerchantBranchDAO (SessionFactory sessionFactory) {
	 super.setSessionFactory(sessionFactory);
	}

	

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RfmsMerchantBranch.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsMerchantBranch getById(Serializable key)
    {
        return (RfmsMerchantBranch) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RfmsMerchantBranch loadById(Serializable key)
    {
        return (RfmsMerchantBranch) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
    
    public List findByMerchantId(Long merchantId){
    	StringBuffer hql=new StringBuffer();
    	hql.append("from ").append(this.getReferenceClass().getName()).append(" branch where ");
    	hql.append(" branch.").append(RfmsMerchantBranch.PROP_MERCHANT_ID).append("=?");
    	hql.append(" and branch.").append(RfmsMerchantBranch.PROP_EXPIRE_DATE).append(" is null");
    	return this.query(hql.toString(),new Object[]{merchantId});
    }
    

}