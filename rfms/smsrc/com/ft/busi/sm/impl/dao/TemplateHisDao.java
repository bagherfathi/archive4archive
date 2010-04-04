
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.TemplateHis;

/**
 * TemplateHis ʵ�����ݷ�����
 * @spring.bean
 * id="TemplateHisDao"
 * @spring.property
 * ref="sessionFactory"
 * name="sessionFactory"
 */
public class TemplateHisDao extends BaseDao {

	/**
	 * ���캯��
	 */
	public TemplateHisDao () {}



    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return TemplateHis.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public TemplateHis getById(Serializable key)
    {
        return (TemplateHis) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public TemplateHis loadById(Serializable key)
    {
        return (TemplateHis) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}