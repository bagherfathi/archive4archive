
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelTemplateOrg;

/**
 * RelTemplateOrg ʵ�����ݷ�����
 * @spring.bean
 * id="RelTemplateOrgDao"
 * @spring.property
 * ref="sessionFactory"
 * name="sessionFactory"
 */
public class RelTemplateOrgDao extends BaseDao {

	/**
	 * ���캯��
	 */
	public RelTemplateOrgDao () {}



    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RelTemplateOrg.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RelTemplateOrg getById(Serializable key)
    {
        return (RelTemplateOrg) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RelTemplateOrg loadById(Serializable key)
    {
        return (RelTemplateOrg) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }



    /**
     * ��ѯģ��������֯��
     * @param templateId    ģ���ʶ��
     * @return
     */
//    public List findRelTemplateOrgs(Long templateId) {
//        StringBuffer hql = new StringBuffer("select new com.ft.sm.dto.RelTemplateOrgDTO(relTemOrg)");
//        hql.append(" from RelTemplateOrg relTemOrg");
//        hql.append(" where relTemOrg.").append(RelTemplateOrg.PROP_TEMPLATE_ID).append("=?");
//        hql.append(" and relTemOrg.").append(RelTemplateOrg.PROP_EXPIRE_DATE).append(" is null");
//        return this.query(hql.toString(),new Object[]{templateId});
//    }
    
    /**
     * ��ѯģ��������֯ID��
     * @param templateId    ģ���ʶ��
     * @return
     */
//    public Long[] findRelTemplateOrgIds(Long templateId) {
//        StringBuffer hql = new StringBuffer("select relTemOrg.").append(RelTemplateOrg.PROP_REL_ORG_ID);
//        hql.append(" from RelTemplateOrg relTemOrg");
//        hql.append(" where relTemOrg.").append(RelTemplateOrg.PROP_TEMPLATE_ID).append("=?");
//        hql.append(" and relTemOrg.").append(RelTemplateOrg.PROP_EXPIRE_DATE).append(" is null");
//        List result = this.query(hql.toString(),new Object[]{templateId});
//        
//        return (Long[])result.toArray(new Long[0]);
//    }
    
    /**
     * ��ѯģ��������֯��
     * @param orgIds    ��֯ID���顣
     * @return
     */
//    public List findRelTemplateOrgsByOrgIds(Long[] orgIds){
//        StringBuffer hql = new StringBuffer("from RelTemplateOrg relTemOrg");
//        hql.append(" where relTemOrg.").append(RelTemplateOrg.PROP_REL_ORG_ID).append(" in ");
//        
//        return this.queryIn(hql.toString(), orgIds);
//    } 
    
    public RelTemplateOrg findRelTemplateOrg(Long orgId,Long templateId){
        StringBuffer hql = new StringBuffer("from RelTemplateOrg relTemOrg");
        hql.append(" where relTemOrg.").append(RelTemplateOrg.PROP_REL_ORG_ID).append("=?");
        hql.append(" and relTemOrg.").append(RelTemplateOrg.PROP_TEMPLATE_ID).append("=?");
        hql.append(" and relTemOrg.").append(RelTemplateOrg.PROP_EXPIRE_DATE).append(" is null");
        
        return (RelTemplateOrg)this.getSingle(hql.toString(),new Object[]{orgId,templateId});
    }
}