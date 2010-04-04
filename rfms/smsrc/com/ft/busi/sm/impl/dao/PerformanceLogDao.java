package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.PerformanceLog;

/**
 * PerformanceLog ʵ�����ݷ�����
 * 
 * @spring.bean id="PerformanceLogDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class PerformanceLogDao extends BaseDao {

    /**
     * ���캯��
     */
    public PerformanceLogDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return PerformanceLog.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public PerformanceLog getById(Serializable key) {
        return (PerformanceLog) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public PerformanceLog loadById(Serializable key) {
        return (PerformanceLog) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}