package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelTemplateOrg;
import com.ft.sm.entity.Template;

/**
 * Template ʵ�����ݷ�����
 * @spring.bean
 * id="TemplateDao"
 * @spring.property
 * ref="sessionFactory"
 * name="sessionFactory"
 */
public class TemplateDao extends BaseDao {

        /**
         * ���캯��
         */
        public TemplateDao () {}



    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return Template.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public Template getById(Serializable key)
    {
        return (Template) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public Template loadById(Serializable key)
    {
        return (Template) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
    
    /**
     * ��ѯָ�����������ָ����֯��ģ�塣
     * @param categoryCode    �����롣
     * @param orgId           ��֯ID��
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
     * ����ָ����������е�ģ�塣
     * @param categoryCode����ģ�������롣
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
     * ��ѯϵͳ�����е�ģ����Ϣ��
     * @return
     */
    public List findAllTemplates(){
        StringBuffer hql = new StringBuffer("select new com.ft.sm.dto.TemplateDTO(template)");
        hql.append(" from Template template");
        hql.append(" where template.").append(Template.PROP_EXPIRE_DATE).append(" is null");
        
        return this.query(hql.toString());
        
    }
}