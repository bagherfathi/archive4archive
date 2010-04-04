
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelTemplateOrg;

/**
 * RelTemplateOrg 实体数据访问类
 * @spring.bean
 * id="RelTemplateOrgDao"
 * @spring.property
 * ref="sessionFactory"
 * name="sessionFactory"
 */
public class RelTemplateOrgDao extends BaseDao {

	/**
	 * 构造函数
	 */
	public RelTemplateOrgDao () {}



    /**
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return RelTemplateOrg.class;
    }

    /**
     * 根据ID查找对象
     */
    public RelTemplateOrg getById(Serializable key)
    {
        return (RelTemplateOrg) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public RelTemplateOrg loadById(Serializable key)
    {
        return (RelTemplateOrg) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }



    /**
     * 查询模板适用组织。
     * @param templateId    模板标识。
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
     * 查询模板适用组织ID。
     * @param templateId    模板标识。
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
     * 查询模板适用组织。
     * @param orgIds    组织ID数组。
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