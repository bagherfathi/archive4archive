
package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.RoleHis;

/**
 * RoleHis ʵ�����ݷ�����
 * 
 * @spring.bean id="RoleHisDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class RoleHisDao extends BaseDao {

    /**
     * ���캯��
     */
    public RoleHisDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return RoleHis.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public RoleHis getById(Serializable key) {
        return (RoleHis) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * ����ID���Ҷ���
     */
    public RoleHis loadById(Serializable key) {
        return (RoleHis) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}