package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.DataResourceHis;

/**
 * DataResourceHis ʵ�����ݷ�����
 * 
 * @spring.bean id="DataResourceHisDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class DataResourceHisDao extends BaseDao {

    /**
     * ���캯��
     */
    public DataResourceHisDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return DataResourceHis.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public DataResourceHis getById(Serializable key) {
        return (DataResourceHis) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public DataResourceHis loadById(Serializable key) {
        return (DataResourceHis) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}