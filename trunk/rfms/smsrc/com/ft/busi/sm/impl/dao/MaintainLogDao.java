package com.ft.busi.sm.impl.dao;

import java.io.Serializable;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.MaintainLog;

/**
 * MaintainLog ʵ�����ݷ�����
 * 
 * @spring.bean id="MaintainLogDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class MaintainLogDao extends BaseDao {

    /**
     * ���캯��
     */
    public MaintainLogDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return MaintainLog.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public MaintainLog getById(Serializable key) {
        return (MaintainLog) this.getHibernateTemplate().get(
                getReferenceClass(), key);
    }

    /**
     * ����ID���Ҷ���
     */
    public MaintainLog loadById(Serializable key) {
        return (MaintainLog) this.getHibernateTemplate().load(
                getReferenceClass(), key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ����IDɾ��ά����־��
     * 
     * @param logId
     *                ά����־ID��
     */
    public void deleteLogById(Long logId) {
        StringBuffer hql = new StringBuffer("from MaintainLog log");
        hql.append(" where log.").append(MaintainLog.PROP_LOG_ID).append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { logId });
    }
}