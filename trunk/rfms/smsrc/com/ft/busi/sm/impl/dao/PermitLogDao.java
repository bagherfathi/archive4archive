package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.PermitLog;

/**
 * PermitLog ʵ�����ݷ�����
 * 
 * @spring.bean id="PermitLogDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class PermitLogDao extends BaseDao {

    /**
     * ���캯��
     */
    public PermitLogDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return PermitLog.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public PermitLog getById(Serializable key) {
        return (PermitLog) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * ����ID���Ҷ���
     */
    public PermitLog loadById(Serializable key) {
        return (PermitLog) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}