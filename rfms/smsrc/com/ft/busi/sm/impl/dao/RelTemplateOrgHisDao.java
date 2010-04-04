
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RelTemplateOrgHis;

/**
 * RelTemplateOrgHis ʵ�����ݷ�����
 * @spring.bean
 * id="RelTemplateOrgHisDao"
 * @spring.property
 * ref="sessionFactory"
 * name="sessionFactory"
 */
public class RelTemplateOrgHisDao extends BaseDao {

	/**
	 * ���캯��
	 */
	public RelTemplateOrgHisDao () {}



    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass () {
        return RelTemplateOrgHis.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RelTemplateOrgHis getById(Serializable key)
    {
        return (RelTemplateOrgHis) this.getHibernateTemplate().get(getReferenceClass(),key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RelTemplateOrgHis loadById(Serializable key)
    {
        return (RelTemplateOrgHis) this.getHibernateTemplate().load(getReferenceClass(),key);
    }


    /**
     * �������ж���
     */
    public java.util.List findAll () {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}