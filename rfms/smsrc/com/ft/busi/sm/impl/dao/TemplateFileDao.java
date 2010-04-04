 
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.Operator;
import com.ft.sm.entity.Template;
import com.ft.sm.entity.TemplateFile;

/**
 * TemplateFile 实体数据访问类
 * @spring.bean
 * id="TemplateFileDao"
 * @spring.property
 * ref="sessionFactory"
 * name="sessionFactory"
 */
public class TemplateFileDao extends BaseDao {

	/**
	 * 构造函数
	 */
	public TemplateFileDao () {}



    /**
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return TemplateFile.class;
    }

    /**
     * 根据ID查找对象
     */
    public TemplateFile getById(Serializable key)
    {
        return (TemplateFile) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public TemplateFile loadById(Serializable key)
    {
        return (TemplateFile) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
    
    /**
     * 查询模板的历史模板文件列表。
     * @param templateId
     * @return
     */
    public List findTemplateFileOfTemplate(Long templateId){
         StringBuffer hql = new StringBuffer("select new com.ft.sm.dto.TemplateFileDTO(file,op.opName)");
         hql.append(" from TemplateFile file,Operator op");
         hql.append(" where file.").append(TemplateFile.PROP_TEMPLATE_ID).append("=?");
         hql.append(" and file.").append(TemplateFile.PROP_OPERATOR_ID).append("=op.").append(Operator.PROP_OPERATOR_ID);
         hql.append(" order by file.").append(TemplateFile.PROP_FILE_VERSION);
         
         return this.query(hql.toString(),new Object[]{templateId});
    }
    
    /**
     * 获取最新版本。
     * @param templateId
     * @return
     */
    public Long findLastVersion(Long templateId){
        StringBuffer hql = new StringBuffer("select max(file.").append(TemplateFile.PROP_FILE_VERSION).append(")");
        hql.append(" from TemplateFile file");
        hql.append(" where file.").append(TemplateFile.PROP_TEMPLATE_ID).append("=?");
        Long lastVersion = (Long)this.getSingle(hql.toString(),new Object[]{templateId});
        if(lastVersion == null){
            lastVersion = new Long(0);
        }
        return lastVersion;
    }
    
    /**
     * 获取指定版本的文件。
     * @param templateId
     * @param fileVersion
     * @return
     */
    public TemplateFile findTemplateFile(Long templateId,long fileVersion){
        StringBuffer hql = new StringBuffer("from TemplateFile file");
        hql.append(" where file.").append(TemplateFile.PROP_TEMPLATE_ID).append("=?");
        hql.append(" and file.").append(TemplateFile.PROP_FILE_VERSION).append("=?");
        return (TemplateFile)this.getSingle(hql.toString(),new Object[]{templateId,new Long(fileVersion)});
    }
    
    /**
     * 获取指定模板的当前发布文件。
     * @param templateCode    模板代码
     * @return
     */
    public TemplateFile findPublishFile(String templateCode){
        StringBuffer hql = new StringBuffer("select file from TemplateFile file,Template template");
        hql.append(" where file.").append(TemplateFile.PROP_TEMPLATE_ID).append("=template.").append(Template.PROP_TEMPLATE_ID);
        hql.append(" and file.").append(TemplateFile.PROP_FILE_VERSION).append("=template.").append(Template.PROP_PUBLISH_VERSION);
        hql.append(" and template.").append(Template.PROP_TEMPLATE_CODE).append("=?");
        
        return (TemplateFile)this.getSingle(hql.toString(),new Object[]{templateCode});
    }
}