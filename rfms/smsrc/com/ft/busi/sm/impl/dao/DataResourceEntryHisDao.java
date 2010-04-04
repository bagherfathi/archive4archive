package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.DataResourceEntryHis;

/**
 * DataResourceEntryHis ʵ�����ݷ�����
 * 
 * @spring.bean id="DataResourceEntryHisDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class DataResourceEntryHisDao extends BaseDao {

    /**
     * ���캯��
     */
    public DataResourceEntryHisDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return DataResourceEntryHis.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public DataResourceEntryHis getById(Serializable key) {
        return (DataResourceEntryHis) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public DataResourceEntryHis loadById(Serializable key) {
        return (DataResourceEntryHis) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}