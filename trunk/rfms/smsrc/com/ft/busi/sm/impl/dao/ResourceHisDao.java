
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.ResourceHis;

/**
 * ResourceHis ʵ�����ݷ�����
 * 
 * @spring.bean id="ResourceHisDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class ResourceHisDao extends BaseDao {

    /**
     * ���캯��
     */
    public ResourceHisDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return ResourceHis.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public ResourceHis getById(Serializable key) {
        return (ResourceHis) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public ResourceHis loadById(Serializable key) {
        return (ResourceHis) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}