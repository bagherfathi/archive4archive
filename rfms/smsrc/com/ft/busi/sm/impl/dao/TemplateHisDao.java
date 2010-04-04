
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.TemplateHis;

/**
 * TemplateHis 实体数据访问类
 * @spring.bean
 * id="TemplateHisDao"
 * @spring.property
 * ref="sessionFactory"
 * name="sessionFactory"
 */
public class TemplateHisDao extends BaseDao {

	/**
	 * 构造函数
	 */
	public TemplateHisDao () {}



    /**
     * 获取查询实体类
     */
    public Class getReferenceClass () {
        return TemplateHis.class;
    }

    /**
     * 根据ID查找对象
     */
    public TemplateHis getById(Serializable key)
    {
        return (TemplateHis) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * 根据ID查找对象
     */
    public TemplateHis loadById(Serializable key)
    {
        return (TemplateHis) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * 查找所有对象
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}