package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.LockObj;

/**
 * LockObj ʵ�����ݷ�����
 * 
 * @spring.bean id="lockObjDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class LockObjDao extends BaseDao {

    /**
     * ���캯��
     */
    public LockObjDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return LockObj.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public LockObj getById(Serializable key) {
        return (LockObj) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * ����ID���Ҷ���
     */
    public LockObj loadById(Serializable key) {
        return (LockObj) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}