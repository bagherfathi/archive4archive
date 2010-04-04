package com.ft.busi.sm.impl.dao;

import java.io.Serializable;
import java.util.List;

import com.ft.hibernate.support.BaseDao;
import com.ft.sm.entity.Attach;

/**
 * Attach ʵ�����ݷ�����
 * 
 * @spring.bean id="AttachDao"
 * @spring.property ref="sessionFactory" name="sessionFactory"
 */
public class AttachDao extends BaseDao {

    /**
     * ���캯��
     */
    public AttachDao() {
    }

    /**
     * ��ȡ��ѯʵ����
     */
    public Class getReferenceClass() {
        return Attach.class;
    }

    /**
     * ����ID���Ҷ���
     */
    public Attach getById(Serializable key) {
        return (Attach) this.getHibernateTemplate().get(getReferenceClass(),
                key);
    }

    /**
     * ����ID���Ҷ���
     */
    public Attach loadById(Serializable key) {
        return (Attach) this.getHibernateTemplate().load(getReferenceClass(),
                key);
    }

    /**
     * �������ж���
     */
    public java.util.List findAll() {
        return this.getHibernateTemplate().loadAll(getReferenceClass());
    }

    /**
     * ��ѯ������Ϣ�ĸ�����Ϣ��
     * 
     * @param infoId
     *                ������ϢID��
     * @return
     */
    public List findAttachsByInfoId(Long infoId) {
        StringBuffer hql = new StringBuffer("from Attach attach");
        hql.append(" where attach.").append(Attach.PROP_INFO_ID).append("=?");

        return this.query(hql.toString(), new Object[] { infoId });
    }

    /**
     * ɾ��������Ϣ��
     * 
     * @param attachId
     *                ������ϢID��
     */
    public void deleteAttachById(Long attachId) {
        StringBuffer hql = new StringBuffer("from Attach attach");
        hql.append(" where attach.").append(Attach.PROP_ATTACH_ID).append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { attachId });
    }

    /**
     * ɾ��������Ϣ�ĸ�����Ϣ��
     * 
     * @param infoId
     */
    public void deleteAttachByInfoId(Long infoId) {
        StringBuffer hql = new StringBuffer("from Attach attach");
        hql.append(" where attach.").append(Attach.PROP_INFO_ID).append("=?");

        this.deleteFromQuery(hql.toString(), new Object[] { infoId });

    }
}