
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelTemplateOrgHis;

/**
 * RelTemplateOrgHis 实体数据访问类
 * @spring.bean
 * id="RelTemplateOrgHisDao"
 * @spring.property
 * ref="sessionFactory"
 * name="sessionFactory"
 */
public class RelTemplateOrgHisDao extends BaseDao {

	/**
	 * 构造函数
	 */
	public RelTemplateOrgHisDao () {}



    /**
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return RelTemplateOrgHis.class;
    }

    /**
     * 根据ID查找对象
     */
    public RelTemplateOrgHis getById(Serializable key)
    {
        return (RelTemplateOrgHis) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public RelTemplateOrgHis loadById(Serializable key)
    {
        return (RelTemplateOrgHis) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}