package com.ft.rfms.entity.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;

import com.ft.hibernate.support.BaseDao;
import com.ft.rfms.entity.RfmsMerchantBranch;
import com.ft.rfms.entity.RfmsMerchantPos;



/**
 * RfmsMerchantBranch 实体数据访问类
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
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return RfmsMerchantBranch.class;
    }

    /**
     * 根据ID查找对象
     */
    public RfmsMerchantBranch getById(Serializable key)
    {
        return (RfmsMerchantBranch) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public RfmsMerchantBranch loadById(Serializable key)
    {
        return (RfmsMerchantBranch) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
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