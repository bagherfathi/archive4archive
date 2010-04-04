package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.GroupHis;

/**
 * GroupHis ʵ�����ݷ�����
 * 
 * @spring.bean id="GroupHisDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class GroupHisDao extends BaseDao {

    /**
     * ���캯��
     */
    public GroupHisDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return GroupHis.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public GroupHis getById(Serializable key) {
        return (GroupHis) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * ����ID���Ҷ���
     */
    public GroupHis loadById(Serializable key) {
        return (GroupHis) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }
}