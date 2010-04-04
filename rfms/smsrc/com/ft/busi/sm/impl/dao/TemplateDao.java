package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelTemplateOrg;
import com.ft.sm.entity.Template;

/**
 * Template 实体数据访问类
 * @spring.bean
 * id="TemplateDao"
 * @spring.property
 * ref="sessionFactory"
 * name="sessionFactory"
 */
public class TemplateDao extends BaseDao {

        /**
         * 构造函数
         */
        public TemplateDao () {}



    /**
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return Template.class;
    }

    /**
     * 根据ID查找对象
     */
    public Template getById(Serializable key)
    {
        return (Template) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public Template loadById(Serializable key)
    {
        return (Template) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
    
    /**
     * 查询指定类别中适用指定组织的模板。
     * @param categoryCode    类别代码。
     * @param orgId           组织ID。
     * @return
     */
    public List findBindTemplateOfOrg(String categoryCode,Long orgId){
        StringBuffer hql = new StringBuffer("select new com.ft.sm.dto.TemplateDTO(template)");
        hql.append(" from Template template,RelTemplateOrg rto");
        hql.append(" where template.").append(Template.PROP_CATEGORY_CODE).append("=?");
        hql.append(" and template.").append(Template.PROP_TEMPLATE_ID).append("=rto.").append(RelTemplateOrg.PROP_TEMPLATE_ID);
        hql.append(" and rto.").append(RelTemplateOrg.PROP_REL_ORG_ID).append("=?");
        hql.append(" and rto.").append(RelTemplateOrg.PROP_EXPIRE_DATE).append(" is null");
        
        return this.query(hql.toString(),new Object[]{categoryCode,orgId});
    }
    
    /**
     * 查找指定类别下所有的模板。
     * @param categoryCode　　模板类别编码。
     * @return
     */
    public List findTemplateByCategoryCode(String categoryCode){
        StringBuffer hql = new StringBuffer("select new com.ft.sm.dto.TemplateDTO(template)");
        hql.append(" from Template template");
        hql.append(" where template.").append(Template.PROP_CATEGORY_CODE).append("=?");
        hql.append(" and template.").append(Template.PROP_EXPIRE_DATE).append(" is null");
        
        return this.query(hql.toString(),new Object[]{categoryCode});
    }
    
    /**
     * 查询系统中所有的模板信息。
     * @return
     */
    public List findAllTemplates(){
        StringBuffer hql = new StringBuffer("select new com.ft.sm.dto.TemplateDTO(template)");
        hql.append(" from Template template");
        hql.append(" where template.").append(Template.PROP_EXPIRE_DATE).append(" is null");
        
        return this.query(hql.toString());
        
    }
}