package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.OperatorLog;

/**
 * OperatorLog ʵ�����ݷ�����
 * 
 * @spring.bean id="OperatorLogDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class OperatorLogDao extends BaseDao {

    /**
     * ���캯��
     */
    public OperatorLogDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return OperatorLog.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public OperatorLog getById(Serializable key) {
        return (OperatorLog) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public OperatorLog loadById(Serializable key) {
        return (OperatorLog) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}