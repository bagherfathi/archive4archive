package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.Bank;
import com.ft.sm.entity.Organization;
import com.ft.sm.entity.RelBankOrg;

/**
 * RelBankOrg ʵ�����ݷ�����
 * 
 * @spring.bean id="RelBankOrgDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RelBankOrgDao extends BaseDao {

    /**
     * ���캯��
     */
    public RelBankOrgDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return RelBankOrg.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RelBankOrg getById(Serializable key) {
        return (RelBankOrg) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RelBankOrg loadById(Serializable key) {
        return (RelBankOrg) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
    
    /**
     * �����ݿ��в�ѯָ���������а���֯��
     * @param bankId    ����ID
     * @return
     */
    public List findBankOrgsOfBank(Long bankId){
        StringBuffer hql = new StringBuffer("select new com.ft.sm.dto.RelBankOrgDTO(relBankOrg,org)");
        hql.append(" from RelBankOrg relBankOrg,Organization org");
        hql.append(" where relBankOrg.").append(RelBankOrg.PROP_BANK_ORG_ID).append("=org.").append(Organization.PROP_ORG_ID);
        hql.append(" and relBankOrg.").append(RelBankOrg.PROP_BANK_ID).append("=?");
        hql.append(" and relBankOrg.expireDate is null");
        
        return this.query(hql.toString(),new Object[]{bankId});
    }

    /**
     * @param id
     * @return
     */
    public Object findBankOrgById(Serializable id) {
        StringBuffer hql = new StringBuffer("select new com.ft.sm.dto.RelBankOrgDTO(relBankOrg,org)");
        hql.append(" from RelBankOrg relBankOrg,Organization org");
        hql.append(" where relBankOrg.").append(RelBankOrg.PROP_BANK_ORG_ID).append("=org.").append(Organization.PROP_ORG_ID);
        hql.append(" and relBankOrg.").append(RelBankOrg.PROP_REL_ID).append("=?");
        hql.append(" and relBankOrg.expireDate is null");
        
        List result = this.query(hql.toString(),new Object[]{id});
        return result.size() > 0 ? result.get(0) : null;
    }
    
    /**
     * ��ѯָ����֯��������Ϣ
     * 
     * @param orgId
     *                ��֯ID
     * @return ����ʵ���б�
     */
    public List findBanksByOrg(Long orgId) {
        StringBuffer hql = new StringBuffer("select new com.ft.sm.dto.RelBankOrgDTO(relBankOrg,bank,org)");
        hql.append(" from RelBankOrg relBankOrg,Bank bank,Organization org");
        hql.append(" where relBankOrg.").append(RelBankOrg.PROP_BANK_ID).append("=bank.").append(Bank.PROP_BANK_ID);
        hql.append(" and relBankOrg.").append(RelBankOrg.PROP_BANK_ORG_ID).append("=org.").append(Organization.PROP_ORG_ID);
        hql.append(" and relBankOrg.").append(RelBankOrg.PROP_BANK_ORG_ID).append("=?");
        hql.append(" and bank.").append(Bank.PROP_STATUS).append("=0");
        
        return this.query(hql.toString(), new Object[] { orgId });
    }
    
    public List findRelBankOrgsByStatusTypeAndOrg(Long onlineStatus,Long onlineType,Long bankOrgId) {
    	List<Object> params = new ArrayList<Object>();
    	StringBuffer hql = new StringBuffer("select new com.ft.sm.dto.RelBankOrgDTO(relBankOrg,bank,org)");
        hql.append(" from RelBankOrg relBankOrg,Bank bank,Organization org");
        hql.append(" where relBankOrg.").append(RelBankOrg.PROP_BANK_ORG_ID).append("=org.").append(Organization.PROP_ORG_ID);
        hql.append(" and bank.").append(Bank.PROP_BANK_ID).append("=relBankOrg.").append(RelBankOrg.PROP_BANK_ID);
        if(onlineStatus!=null) {
        	hql.append(" and relBankOrg.").append(RelBankOrg.PROP_ONLINE_STATUS).append("=?");
        	params.add(onlineStatus);
        }
        if(onlineType!=null) {
        	hql.append(" and relBankOrg.").append(RelBankOrg.PROP_ONLINE_TYPE).append("=?");
        	params.add(onlineType);
        }
        if(bankOrgId!=null && bankOrgId.longValue()!=0) {
        	hql.append(" and relBankOrg.").append(RelBankOrg.PROP_BANK_ORG_ID).append("=?");
        	params.add(bankOrgId);
        }
        hql.append(" and relBankOrg.expireDate is null");
        
        return this.query(hql.toString(),params.toArray(new Long[0]));
    }
}